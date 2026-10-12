package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    private static long nextId = 1;

    private final long id;
    private final String owner;
    private long balance;

    private final List<Transaction> history = new ArrayList<>();

    public Account(String owner) {
        this.id = nextId;
        if ((owner == null || owner.isBlank())) {
            throw new IllegalArgumentException("Owner must not be null or blank");
        }
        this.owner = owner;
        nextId++;
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
        validateAmount(amount);
        balance += amount;
        history.add(new Transaction(TransactionType.DEPOSIT, amount, balance));
    }

    public void withdraw(long amount) throws InsufficientFundsException {
        validateAmount(amount);
        if (balance < amount) {
            throw new InsufficientFundsException(("Requested " + amount + ", available " + balance));
        }
        balance -= amount;
        history.add(new Transaction(TransactionType.WITHDRAWAL, amount, balance));
    }

    public List<Transaction> getHistory() {
        return List.copyOf(history);
    }

    private void validateAmount(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero, got " + amount);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account other = (Account) o;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{id=%d, owner='%s', balance=%d}".formatted(id, owner, balance);
    }
}
