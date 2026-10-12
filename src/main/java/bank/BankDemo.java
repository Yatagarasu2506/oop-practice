package bank;

public class BankDemo {
    public static void main(String[] args) {
        Account firstAccount = new Account("Eduard");
        Account secondAccount = new Account("Dylan");
        System.out.println(firstAccount + "\n" + secondAccount);

        long depositExample = 50_000;
        System.out.printf("Deposit %d into the first account%n", depositExample);
        firstAccount.deposit(depositExample);

        long withdrawalExample = 30_000;
        System.out.printf("Trying to withdraw %d%n", withdrawalExample);

        try {
            firstAccount.withdraw(30_000);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Balance after withdrawal: " + firstAccount.getBalance());

        System.out.println("Trying to withdraw more than the account balance: ");

        try {
            firstAccount.withdraw(60_000);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Balance after: " + firstAccount.getBalance());
        System.out.println("Transaction history: " + '\n' + firstAccount.getHistory());

        //Trying to modify fixed-size List (copyOf)
        System.out.println("Trying to modify history outside Account class: ");
        try {
            firstAccount.getHistory().add(new Transaction(TransactionType.DEPOSIT, 100_000, 200_000));
        } catch (UnsupportedOperationException e) {
            System.out.println("Tried to modify fixed-size List");
        }
    }
}