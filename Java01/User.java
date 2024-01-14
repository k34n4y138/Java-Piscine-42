
public class User {
	int		id = UserIdsGenerator.getInstance().generateId();
	String	name;
	int		balance;
	public TransactionsList transactionsList = new TransactionsLinkedList();
	public User(String name, int balance) {
		this.name = name;
		this.balance = balance;
	}
	public int		getId(){return id;}
	public String	getName(){return name;}
	public int		getBalance(){return balance;}
	public void		setBalance(int balance){this.balance = balance;}
	public String	Stringify()
					{return "User (" + id + "): " + name + ", Balance: " + balance;}
}
