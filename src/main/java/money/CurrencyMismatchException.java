package money;

public class CurrencyMismatchException extends RuntimeException {
    public CurrencyMismatchException(Currency expected, Currency actual) {
        super("Currency mismatch: expected %s, got %s".formatted(expected.name(), actual.name()));
    }
}
