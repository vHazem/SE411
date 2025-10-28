package edu.psu.se411.lab13_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Transaction implements Runnable {
    
    static Logger logger = LoggerFactory.getLogger(Transaction.class);
    
    private CryptoAccount sender;
    private CryptoAccount receiver;
    private double amount;
    
    public Transaction(CryptoAccount sender, CryptoAccount receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    
    /**
     * Execute the transaction by withdrawing from sender and depositing to receiver.
     */
    public void execute() {
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
