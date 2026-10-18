package money;

public enum Currency {
    PLN("Polish zloty", 2), EUR("Euro", 2), JPY("Japanese yen", 0);

    private final String displayName;
    private final int fractionDigits;

    Currency(String displayName, int fractionDigits) {
        this.displayName = displayName;
        this.fractionDigits = fractionDigits;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getFractionDigits() {
        return fractionDigits;
    }

    public long getMinorUnitsPerMajor() {
        long result = 1;
        for (int i = 0; i < fractionDigits; i++) {
            result *= 10;
        }
        return result;
    }
}
