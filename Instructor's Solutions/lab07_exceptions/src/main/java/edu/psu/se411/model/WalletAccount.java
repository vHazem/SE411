package edu.psu.se411.model;

import edu.psu.se411.exceptions.InsufficientFundsException;

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

	
}
