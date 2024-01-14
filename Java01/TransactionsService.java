
import java.util.UUID;

public class TransactionsService {
    static UsersList usersList = new UsersArrayList();

    public static void AddUser(User user) {
        usersList.addUser(user);
    }

    public static void getUsersInsights() {
        System.out.println("Users insights:");
        System.out.println("Users count: " + usersList.getUsersCount());
        int totalBalance = 0;
        int totalTransactions = 0;
        for (int i = 0; i < usersList.getUsersCount(); i++) {
            User user = null;
            try {
                user = usersList.getUserByIndex(i);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            totalBalance += user.getBalance();
            totalTransactions += user.transactionsList.getTransactionsCount();
        }
        System.out.println("Users Total Balance: " + totalBalance);
        System.out.println("Number of issued Transactions: " + Math.ceil(totalTransactions / 2.0));
    }

    public static void getUsersList()
    {
        System.out.println("Users list:");
        for (int i = 0; i < usersList.getUsersCount(); i++)
        {
            User user = null;
            try {
                user = usersList.getUserByIndex(i);
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(user.Stringify());
        }
    }

    public static void getUserTransactionsList(int id) throws UserNotFoundException
    {
        User user = usersList.getUserById(id);
        System.out.println("Transactions list for user " + user.getName() + ":");
        Transaction[] transactions = user.transactionsList.toArray();
        for (int i = 0; i < user.transactionsList.getTransactionsCount(); i++)
        {
            System.out.println(transactions[i].Stringify());
        }
    }

    public TransactionsList getUnpairedTransactions()
    {
        TransactionsList unpaired = new TransactionsLinkedList();
        for (int i = 0; i < usersList.getUsersCount(); i++)
        {
            Transaction[] _tlst = null;
            try {
                _tlst = usersList.getUserByIndex(i).transactionsList.toArray();
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            for (Transaction t : _tlst)
            {
                try
                {
                    t.receiver.transactionsList.get(t.pairid);
                }
                catch (TransactionNotFoundException e)
                {
                    unpaired.addTransaction(t);
                    System.out.println(t.Stringify());
                }
            }
        }
        return (unpaired);
    }

    public static void removeTransaction(int userId, UUID transactionId) throws UserNotFoundException, TransactionNotFoundException {
        User user = usersList.getUserById(userId);
        user.transactionsList.removeTransactionById(transactionId);
    }

    private static Boolean checkTransactionPossible(User sender, int amount)
    {return (sender.getBalance() >= amount);}

    public static void IssueTransaction(User sender, User receiver, int amount) throws IllegalTransactionException {
        if (sender == receiver)
            throw new IllegalTransactionException("Sender and receiver are the same user");
        if (amount <= 0)
            throw new IllegalTransactionException("Amount must be positive");
        if (!checkTransactionPossible(sender, amount))
            throw new IllegalTransactionException("Not enough founds in sender's account");
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        Transaction transaction = new Transaction(sender, receiver, amount, Transaction.category.DEBIT);
        Transaction receipt = new Transaction(receiver, sender, amount, Transaction.category.CREDIT, transaction.getId());
        sender.transactionsList.addTransaction(transaction);
        receiver.transactionsList.addTransaction(receipt);
    }

    public static void IssueTransaction(int senderId, int receiverId, int amount) throws IllegalTransactionException {
        try {
            User sender = usersList.getUserById(senderId);
            User receiver = usersList.getUserById(receiverId);
            IssueTransaction(sender, receiver, amount);
        } catch (UserNotFoundException e) {
            throw new IllegalTransactionException(e.getMessage());
        }
    }
}
