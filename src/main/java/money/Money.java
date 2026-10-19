package money;

import java.util.Objects;

public final class Money implements Comparable<Money> {
    private final long amount;
    private final Currency currency;

    private Money(long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(long amount, Currency currency) {
        Objects.requireNonNull(currency, "Currency must not be null");
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must not be negative, got " + amount);
        }
        return new Money(amount, currency);
    }

    public static Money zero(Currency currency) {
        return of(0, currency);
    }

    public long getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    private void requireSameCurrency(Money other) {
        Objects.requireNonNull(other, "Money must not be null");
        if (this.currency != other.currency) {
            throw new CurrencyMismatchException(this.currency, other.currency);
        }
    }

    public Money plus(Money other) {
        requireSameCurrency(other);
        long total = this.amount + other.amount;
        return of(total, this.currency);
    }

    public Money minus(Money other) {
        requireSameCurrency(other);
        long total = this.amount - other.amount;
        return of(total, this.currency);
    }

    public Money multiply(int factor) {
        if (factor < 0) {
            throw new IllegalArgumentException("Factor must not be negative, got " + factor);
        }
        long total = this.amount * factor;
        return of(total, this.currency);
    }

    @Override
    public int compareTo(Money other) {
        requireSameCurrency(other);
        return Long.compare(this.amount, other.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money other)) return false;
        return (this.currency == other.currency) && (this.amount == other.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        if (currency.getFractionDigits() == 0) {
            return "%d %s".formatted(amount, currency);
        }
        long wholePart = amount / currency.getMinorUnitsPerMajor();
        long fractionalPart = amount % currency.getMinorUnitsPerMajor();
        return "%d.%02d %s".formatted(wholePart, fractionalPart, currency);
    }

}
