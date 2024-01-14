
import java.util.UUID;

class TransactionNode {

    Transaction content;
    TransactionNode prev;
    TransactionNode next;

    public TransactionNode(TransactionNode node, Transaction content)
    {this.content = content;this.prev = node;}
    public TransactionNode(Transaction content)
    {this.content = content;}

    public void set_prev(TransactionNode node)
    {this.prev = node;}
    public void set_next(TransactionNode node)
    {this.next = node;}
}


public class TransactionsLinkedList implements TransactionsList {
    TransactionNode head;
    TransactionNode tail;
    int _lenght = 0;
    public TransactionsLinkedList(){}

    public int getTransactionsCount(){return (_lenght);}
    public void addTransaction(Transaction tr)
    {
        this.tail = new TransactionNode(tail, tr);
        if (_lenght == 0)
            this.head = this.tail;
        _lenght++;
    }
    public Transaction[] toArray()
    {
        Transaction[] ret = new Transaction[this._lenght];

        TransactionNode traverser = head;
        for (int i = 0; i < this._lenght && traverser != null; i++)
        {
            ret[i] = traverser.content;
            traverser = traverser.next;
        }
        return (ret);
    }
    void detach_node (TransactionNode node)
    {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        if (node == head)
            head = node.next;
        if (node == tail)
            tail = node.prev;
    }
    public void removeTransactionById(UUID id) throws TransactionNotFoundException
    {
        for (TransactionNode traverser = head; traverser != null; traverser = traverser.next)
        {
            if (traverser.content.getId() == id)
            {
               detach_node(traverser);
                _lenght--;
                return;
            }
        }
        throw new TransactionNotFoundException("Transaction with id " + id + " not found");
    }
    public Transaction get(UUID id) throws TransactionNotFoundException
    {
        for (TransactionNode traverser = head; traverser != null; traverser = traverser.next)
        {
            if (traverser.content.getId() == id)
                return (traverser.content);
        }
        throw new TransactionNotFoundException("Transaction with id " + id + " not found");
    }
}

