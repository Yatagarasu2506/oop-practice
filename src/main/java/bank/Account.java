package bank;

public class Account {
    private final long id;
    private final String owner;
    private long balance = 0L;

    private static int count = 0;

    Account(String owner) {
        this.id = count;
        if ((owner == null || owner.isBlank())) {
            throw new IllegalArgumentException("Illegal argument: must not be null or blank");
        }
        this.owner = owner;
        count++;
    }

    public long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public long getBalance() {
        return balance;
    }

    public void deposit(long amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Illegal argument: must be greater than zero");
        }
        balance += amount;
    }

    public void withdraw(long amount) throws InsufficientFundsException {
        if(amount <= 0) {
            throw new IllegalArgumentException("Illegal argument: must be greater than zero");
        }
        if(balance - amount < 0) {
            throw new InsufficientFundsException(("Insufficient funds: requested " + amount + ", available " + balance));
        }

        balance -= amount;
    }



}
