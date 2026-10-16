package payout;

public interface PaymentGateway {
    boolean transfer(long employeeId, long amount);
}
