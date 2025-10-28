package edu.psu.se411.lab13_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.locks.ReentrantLock;

/**
 * CryptoAccount with ReentrantLock for Option 2 and Option 3.
 */
public class CryptoAccountWithLock {
    
    static Logger logger = LoggerFactory.getLogger(CryptoAccountWithLock.class);
    
    private int accountId;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();
    
    public CryptoAccountWithLock(int accountId, double initialBalance) {
        this.accountId = accountId;
        this.balance = initialBalance;
    }
    
    public int getAccountId() {
        return accountId;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public ReentrantLock getLock() {
        return lock;
    }
    
    /**
     * Withdraw money from this account (should be called within a lock).
     */
    public boolean withdraw(double amount) {
        if (balance >= amount) {
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
     * Deposit money into this account (should be called within a lock).
     */
    public void deposit(double amount) {
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
