package payroll;

public class PayrollDemo {
    public static void main(String[] args) {
        SalariedEmployee firstEmployee = new SalariedEmployee("Anna", 800_000);
        HourlyEmployee secondEmployee = new HourlyEmployee("Piotr", 5_000);
        secondEmployee.setHoursWorked(170);
        CommissionEmployee thirdEmployee = new CommissionEmployee("Kasia", 400_000, 10);
        thirdEmployee.setSalesAmount(2_000_000);

        System.out.println("First employee:\n" + firstEmployee);
        System.out.println("Second employee:\n" + secondEmployee);
        System.out.println("Third employee:\n" + thirdEmployee);

        Payroll payroll = new Payroll();
        payroll.addEmployee(firstEmployee);
        payroll.addEmployee(secondEmployee);
        payroll.addEmployee(thirdEmployee);


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
            secondEmployee.setHoursWorked(800);
        } catch (IllegalArgumentException e) {
            System.out.println("Result of setting 800 hours worked to hourly employee:\n" + e.getMessage());
        }

        try {
            new SalariedEmployee("X", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Result of creating salaried employee who's monthly salary equals 0:\n" + e.getMessage());
        }
    }
}
