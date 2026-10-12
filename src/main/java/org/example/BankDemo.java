package org.example;
import bank.Account;
import bank.InsufficientFundsException;
import bank.Transaction;
import bank.TransactionType;


public class BankDemo {
    public static void main(String[] args){
        Account firstAccount = new Account("Eduard");
        Account secondAccount = new Account("Dylan");
        System.out.println(firstAccount.toString() + '\n' + secondAccount.toString());

        firstAccount.deposit(50_000);

        try {
            firstAccount.withdraw(30_000);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(firstAccount.getBalance());

        try {
            firstAccount.withdraw(60_000);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(firstAccount.getBalance());
        System.out.println(firstAccount.getHistory());

        //Trying to modify fixed-size List (copyOf)
        firstAccount.getHistory().add(new Transaction(TransactionType.DEPOSIT, 100_000, 200_000));
    }
}