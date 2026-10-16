package payout;

public class ConsolePaymentGateway implements PaymentGateway {
    @Override
    public boolean transfer(long employeeId, long amount) {
        System.out.printf("Transferred %d to employee #%d%n", amount, employeeId);
        return true;
    }
}
