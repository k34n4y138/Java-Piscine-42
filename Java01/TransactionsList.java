
import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void removeTransactionById(UUID id) throws TransactionNotFoundException;
    public Transaction[] toArray();
    public int getTransactionsCount();
    public Transaction get(UUID id) throws TransactionNotFoundException;
}
