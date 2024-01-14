import java.util.Scanner;

public class Program {

    private static void printMainMenu() {
        System.out.println("1. Create user");
        System.out.println("2. View Bank Insights");
        System.out.println("3. View Users List");
        System.out.println("4. Perform a Transaction");
        System.out.println("4. View a User's Transactions List");
        System.out.println("5. remove a user's transaction");
        System.out.println("6. check transfers validity");
        System.out.println("6. Exit");
    }
    private static void createUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user name:");
        String name = scanner.nextLine();
        System.out.println("Enter user balance:");
        int balance = scanner.nextInt();
        System.err.println("Creating user " + name + " with balance " + balance);
        User user = new User(name, balance);
        TransactionsService.AddUser(user);
        System.out.println("User created successfully.");
    }

    private static void makeTransaction()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sender id:");
        int senderId = scanner.nextInt();
        System.out.println("Enter receiver id:");
        int receiverId = scanner.nextInt();
        System.out.println("Enter amount:");
        int amount = scanner.nextInt();
        try{
            TransactionsService.IssueTransaction(senderId, receiverId, amount);
        } catch (IllegalTransactionException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        System.err.println("CIH BANK v0.1");
        System.err.println("Initializing...");
        System.err.println("Initialization complete.");
        System.err.println("Starting...");
        printMainMenu();
        while (true) {
            System.out.print("Enter your choice: ");
            scanner.reset();
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                TransactionsService.getUsersInsights();
                    break;
                case 3:
                TransactionsService.getUsersList();
                    break;
                case 4:
                    makeTransaction();
                    break;
                case 7:
                    System.err.println("Ciao...");
                    System.exit(0);
                    break;
                    default:
                    System.err.println("Invalid choice.");
                    break;
            }
        }
    }
}
