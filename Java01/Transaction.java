
import java.util.UUID;
public class Transaction {
	enum category {DEBIT, CREDIT};

	UUID id = UUID.randomUUID();
	UUID pairid;
	User sender;
	User receiver;
	int amount;
	category type;

	public			Transaction(User sender, User receiver, int amount, category type) {
						this.sender = sender;
						this.receiver = receiver;
						this.amount = amount;
						this.type = type;
					}

	public			Transaction(User sender, User receiver, int amount, category type, UUID id) {
						this.sender = sender;
						this.receiver = receiver;
						this.amount = amount;
						this.type = type;
						this.id = id;
					}
	public UUID		getId() {return id;}
	public User		getSender() {return sender;}
	public User		getReceiver() {return receiver;}
	public int		getAmount() {return amount;}
	public category getType() {return type;}

	public String	Stringify() {
						String output = sender.getName() + " -> " + receiver.getName();
						output += (type == category.DEBIT ? ", -" : ", +") + amount;
						output += type == category.DEBIT ? ", INCOME" : ", OUTCOME";
						return output + ", " + id;
					}

}
