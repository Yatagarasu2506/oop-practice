package payroll;

public class HourlyEmployee extends Employee {
    private final int REGULAR_HOURS = 160;
    private final int MAX_HOURS = 744;

    private final long hourlyRate;
    private int hoursWorked;

    HourlyEmployee(String name, long hourlyRate) {
        super(name);
        if (hourlyRate <= 0) {
            throw new IllegalArgumentException("Hourly rate must be greater than zero, got " + hourlyRate);
        }
        this.hourlyRate = hourlyRate;
    }

    public long getHourlyRate() {
        return hourlyRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        if (hoursWorked < 0 || hoursWorked > MAX_HOURS) {
            throw new IllegalArgumentException("Hours worked must be between 0 and " + MAX_HOURS + " (inclusive), got " + hoursWorked);
        }
        this.hoursWorked = hoursWorked;
    }

    public long calculateMonthlyPay() {
        if (hoursWorked > REGULAR_HOURS) {
            long overtimePay = (hoursWorked - REGULAR_HOURS) * hourlyRate * 3 / 2;
            return REGULAR_HOURS * hourlyRate + overtimePay;
        }
        return hoursWorked * hourlyRate;
    }

}
