package payout;

public class LimitedPaymentGateway implements PaymentGateway {
    private final long limit;

    public LimitedPaymentGateway(long limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than zero, got " + limit);
        }
        this.limit = limit;
    }

    public long getLimit() {
        return limit;
    }

    @Override
    public boolean transfer(long employeeId, long amount) {
        return amount <= limit;
    }
}
