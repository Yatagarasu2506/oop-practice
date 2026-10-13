package payroll;

public abstract class Employee {
    private static long nextId = 1;

    private final long id;
    private final String name;

    protected Employee(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name must not be null or blank");
        }
        this.id = nextId;
        this.name = name;
        nextId++;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract long calculateMonthlyPay();

    @Override
    public String toString() {
        return "%s{id='%d', name='%s', pay='%d'}".formatted(getClass().getSimpleName(), id, name, calculateMonthlyPay());
    }
}
