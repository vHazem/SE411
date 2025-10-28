package edu.psu.se411.lab13_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CryptoAccount class representing a digital account with a balance.
 * This implementation does NOT use synchronization (for Exercise 1).
 */
public class CryptoAccount {
    
    static Logger logger = LoggerFactory.getLogger(CryptoAccount.class);
    
    private int accountId;
    private double balance;
    
    public CryptoAccount(int accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public double getBalance() {
        return balance;
    }
    
    /**
     * Withdraw money from this account.
     * Returns true if successful, false if insufficient funds.
     */
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            // Simulate some processing time to increase likelihood of race condition
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                logger.error("Thread interrupted during withdrawal", e);
            }
            balance -= amount;
            return true;
        }
        return false;
    }
    
    /**
     * Deposit money into this account.
     */
    public void deposit(double amount) {
        // Simulate some processing time to increase likelihood of race condition
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted during deposit", e);
        }
        balance += amount;
    }
    
    @Override
    public String toString() {
        return String.format("Account[%d] Balance: %.2f", accountId, balance);
    }
}
