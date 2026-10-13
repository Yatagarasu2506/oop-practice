package payroll;

public class SalariedEmployee extends Employee {
    private final long monthlySalary;

    public SalariedEmployee(String name, long monthlySalary) {
        super(name);
        if (monthlySalary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than zero, got " + monthlySalary);
        }
        this.monthlySalary = monthlySalary;
    }

    public long getMonthlySalary() {
        return monthlySalary;
    }

    public long calculateMonthlyPay() {
        return monthlySalary;
    }

}
