package edu.psu.se411;

import edu.psu.se411.exception.InsufficientFundsException;

public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Withdrawal failed: Insufficient funds.");
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Remaining balance: $" + balance);
    }

    public double getBalance() {
        return balance;
    }
}
