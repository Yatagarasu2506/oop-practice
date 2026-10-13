package payroll;

public class CommissionEmployee extends SalariedEmployee {
    private final int commissionPercent;
    private long salesAmount;

    public CommissionEmployee(String name, long monthSalary, int commissionPercent) {
        super(name, monthSalary);
        if (commissionPercent < 1 || commissionPercent > 50) {
            throw new IllegalArgumentException("Commission percent must between 1 and 50 (inclusive), got " + commissionPercent);
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
