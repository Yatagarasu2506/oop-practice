package bank;

public record Transaction(TransactionType type, long amount, long balance) {
}
