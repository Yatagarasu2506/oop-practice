package payout;

import org.w3c.dom.ls.LSOutput;
import payroll.CommissionEmployee;
import payroll.HourlyEmployee;
import payroll.Payroll;
import payroll.SalariedEmployee;

public class PayoutDemo {
    public static void main(String[] args) {
        SalariedEmployee anna = new SalariedEmployee("Anna", 800_000);

        HourlyEmployee piotr = new HourlyEmployee("Piotr", 5_000);
        piotr.setHoursWorked(170);

        CommissionEmployee kasia = new CommissionEmployee("Kasia", 400_000, 10);
        kasia.setSalesAmount(2_000_000);

        HourlyEmployee tomek = new HourlyEmployee("Tomek", 4_000);

        Payroll payroll = new Payroll();
        payroll.addEmployee(anna);
        payroll.addEmployee(piotr);
        payroll.addEmployee(kasia);
        payroll.addEmployee(tomek);

        Notifier consoleNotifier = new ConsoleNotifier();
        PaymentGateway consolePaymentGateway = new ConsolePaymentGateway();

        PayoutService consolePayout = new PayoutService(payroll, consolePaymentGateway, consoleNotifier);
        System.out.println("=== Scenario 1 ===");
        System.out.println(consolePayout.payAll());

        PaymentGateway limitedPaymentGateway = new LimitedPaymentGateway(850_000);
        InMemoryNotifier inMemoryNotifier = new InMemoryNotifier();

        PayoutService limitedPayout = new PayoutService(payroll, limitedPaymentGateway, inMemoryNotifier);
        System.out.println("=== Scenario 2 ===\n" + limitedPayout.payAll());
        for (String message : inMemoryNotifier.getMessages()) {
            System.out.println(message);
        }

        System.out.println("=== Scenario 3 ===");
        try {
            new PayoutService(payroll, null, consoleNotifier);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        try {
            new LimitedPaymentGateway(0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Payroll emptyPayroll = new Payroll();
        PayoutService emptyPayout = new PayoutService(emptyPayroll, consolePaymentGateway, consoleNotifier);
        System.out.println(emptyPayout.payAll());
    }
}
