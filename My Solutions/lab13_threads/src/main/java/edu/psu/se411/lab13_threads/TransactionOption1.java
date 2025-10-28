package edu.psu.se411.lab13_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transaction class - Option 1: Synchronized execute() method
 * This implementation uses synchronized method to prevent race conditions.
 */
public class TransactionOption1 implements Runnable {
    
    static Logger logger = LoggerFactory.getLogger(TransactionOption1.class);
    
    private CryptoAccount sender;
    private CryptoAccount receiver;
    private double amount;
    
    public TransactionOption1(CryptoAccount sender, CryptoAccount receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    
    /**
     * Execute the transaction with synchronized method.
     * This synchronizes on the Transaction object itself.
     */
    public synchronized void execute() {
        // Don't transfer to the same account
        if (sender.getAccountId() == receiver.getAccountId()) {
            return;
        }
        
        // Try to withdraw from sender
        if (sender.withdraw(amount)) {
            // If successful, deposit to receiver
            receiver.deposit(amount);
        }
    }
    
    @Override
    public void run() {
        execute();
    }
}
