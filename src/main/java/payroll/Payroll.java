package payroll;

import java.util.ArrayList;
import java.util.List;

public class Payroll {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee must not be null!");
        }
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return List.copyOf(employees);
    }

    public long calculateTotalPayroll() {
        if (employees.isEmpty()) {
            return 0;
        }

        long temp = 0;
        for (Employee employee : employees) {
            temp += employee.calculateMonthlyPay();
        }
        return temp;
    }

    public Employee findHighestPaid() {
        if (employees.isEmpty()) {
            throw new IllegalStateException("No employees");
        }
        long max = 0;
        Employee highestPaid = null;
        for (Employee employee : employees) {
            long monthlyPay = employee.calculateMonthlyPay();

            if (monthlyPay > max) {
                max = monthlyPay;
                highestPaid = employee;
            }
        }
        return highestPaid;
    }
}
