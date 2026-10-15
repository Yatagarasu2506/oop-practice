package payroll;

public class CommissionEmployee extends SalariedEmployee {
    private static final int COMMISSION_PERCENT_MIN = 1;
    private static final int COMMISSION_PERCENT_MAX = 50;
    private final int commissionPercent;
    private long salesAmount;

    public CommissionEmployee(String name, long monthlySalary, int commissionPercent) {
        super(name, monthlySalary);
        if (commissionPercent < COMMISSION_PERCENT_MIN || commissionPercent > COMMISSION_PERCENT_MAX) {
            throw new IllegalArgumentException("Commission percent must be between 1 and 50 (inclusive), got " + commissionPercent);
        }
        this.commissionPercent = commissionPercent;
    }

    public int getCommissionPercent() {
        return commissionPercent;
    }

    public long getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(long salesAmount) {
        if (salesAmount < 0) {
            throw new IllegalArgumentException("Sales amount must be zero or greater, got " + salesAmount);
        }
        this.salesAmount = salesAmount;
    }

    @Override
    public long calculateMonthlyPay() {
        long commissionFromSales = salesAmount * commissionPercent / 100;
        return super.calculateMonthlyPay() + commissionFromSales;
    }
}
