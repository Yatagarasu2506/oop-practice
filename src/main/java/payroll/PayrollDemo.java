package payroll;

public class PayrollDemo {
    public static void main(String[] args) {
        SalariedEmployee salaried = new SalariedEmployee("Anna", 800_000);
        HourlyEmployee hourly = new HourlyEmployee("Piotr", 5_000);
        hourly.setHoursWorked(170);
        CommissionEmployee commission = new CommissionEmployee("Kasia", 400_000, 10);
        commission.setSalesAmount(2_000_000);

        System.out.println("First employee:\n" + salaried);
        System.out.println("Second employee:\n" + hourly);
        System.out.println("Third employee:\n" + commission);

        Payroll payroll = new Payroll();
        payroll.addEmployee(salaried);
        payroll.addEmployee(hourly);
        payroll.addEmployee(commission);


        System.out.println("Total payroll: " + payroll.calculateTotalPayroll());
        System.out.println("Highest paid employee: " + payroll.findHighestPaid().getName());

        //new Employee(); can't create an object of abstract class

        Payroll empty = new Payroll();
        try {
            empty.findHighestPaid();
        } catch (IllegalStateException e) {
            System.out.println("Result of calling findHighestPaid method on empty Payroll:\n" + e.getMessage());
        }

        try {
            hourly.setHoursWorked(800);
        } catch (IllegalArgumentException e) {
            System.out.println("Result of setting 800 hours worked for an hourly employee:\n" + e.getMessage());
        }

        try {
            new SalariedEmployee("X", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Result of creating salaried employee whose monthly salary equals 0:\n" + e.getMessage());
        }
    }
}
