package edu.spu.se411.lab08_logging.model;

import edu.spu.se411.lab08_logging.exceptions.InsufficientFundsException;

public class WalletAccount {

	private double balance;

    public WalletAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Your balance is " + balance);
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        }
    }
    
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit negative number: " + amount);
        } else {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        }
    }

	
}
