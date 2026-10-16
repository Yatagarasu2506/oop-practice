package payout;

public record PayoutReport(int paidCount, int failedCount, int skippedCount, long paidTotal) {

}
