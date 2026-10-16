package payout;

import payroll.Employee;
import payroll.Payroll;

import java.util.List;
import java.util.Objects;

public class PayoutService {
    private final Payroll payroll;
    private final PaymentGateway gateway;
    private final Notifier notifier;

    public PayoutService(Payroll payroll, PaymentGateway gateway, Notifier notifier) {
        this.payroll = Objects.requireNonNull(payroll, "Payroll must not be null");
        this.gateway = Objects.requireNonNull(gateway, "Gateway must not be null");
        this.notifier = Objects.requireNonNull(notifier, "Notifier must not be null");
    }

    public PayoutReport payAll() {
        int paidCount = 0, failedCount = 0, skippedCount = 0;
        long paidTotal = 0;
        List<Employee> employees = payroll.getEmployees();

        for (Employee employee : employees) {
            long pay = employee.calculateMonthlyPay();
            String name = employee.getName();
            if (pay == 0) {
                notifier.send("Skipped %s: nothing to pay".formatted(name));
                skippedCount++;
            } else {
                boolean succesfull = gateway.transfer(employee.getId(), pay);
                if (succesfull) {
                    notifier.send("Paid %d to %s".formatted(pay, name));
                    paidCount++;
                    paidTotal += pay;
                } else {
                    notifier.send("Failed to pay %d to %s".formatted(pay, name));
                    failedCount++;
                }
            }
        }
        return new PayoutReport(paidCount, failedCount, skippedCount, paidTotal);
    }
}
