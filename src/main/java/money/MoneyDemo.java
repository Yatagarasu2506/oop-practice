package money;

import java.util.*;

public class MoneyDemo {
    public static void main(String[] args) {
        System.out.println("=== Currencies ===");
        for (Currency c : Currency.values()) {
            System.out.printf("%s: %s, %d fraction digits%n", c.name(), c.getDisplayName(), c.getFractionDigits());
        }

        System.out.println("=== Formatting ===");
        Money salary = Money.of(800_000, Currency.PLN);
        System.out.println(salary);
        System.out.println(Money.of(5, Currency.EUR));
        System.out.println(Money.of(1_500, Currency.JPY));
        System.out.println(Money.zero(Currency.PLN));

        System.out.println("=== Arithmetic ===");
        Money bonus = Money.of(50_000, Currency.PLN);
        Money tax = Money.of(96_000, Currency.PLN);
        Money hourlyRate = Money.of(5_025, Currency.PLN);
        System.out.println(salary.plus(bonus));
        System.out.println(salary.minus(tax));
        System.out.println(hourlyRate.multiply(170));

        System.out.println("=== Immutability ===");
        salary.plus(bonus);
        System.out.println(salary);

        System.out.println("=== Equality ===");
        Money a = Money.of(10_000, Currency.PLN), b = Money.of(10_000, Currency.PLN), c = Money.of(10_000, Currency.PLN);
        System.out.println("a == b: " + (a == b));
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("a.equals(c)" + a.equals(c));
        System.out.println("same hashCode: " + (a.hashCode() == b.hashCode()));
        Set<Money> set = new HashSet<>();
        set.add(a);
        set.add(b);
        System.out.println(set.size());

        System.out.println("=== Sorting ===");
        List<Money> money = new ArrayList<>();
        money.add(Money.of(1_200_050, Currency.PLN));
        money.add(Money.of(150_000, Currency.PLN));
        money.add(Money.of(800_000, Currency.PLN));
        Collections.sort(money);
        System.out.println(money);
        System.out.println("max: " + Collections.max(money));

        System.out.println("=== Errors ===");
        try {
            Money.of(-1, Currency.PLN);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            Money.of(100, null);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        try {
            salary.plus(c);
        } catch (CurrencyMismatchException e) {
            System.out.println(e.getMessage());
        }

        try {
            bonus.minus(salary);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            hourlyRate.multiply(-2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            salary.compareTo(c);
        } catch (CurrencyMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}
