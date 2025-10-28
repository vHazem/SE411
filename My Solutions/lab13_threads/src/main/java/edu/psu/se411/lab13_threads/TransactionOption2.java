package edu.psu.se411.lab13_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Transaction class - Option 2: Lock both accounts before transaction
 * WARNING: This approach can cause DEADLOCK!
 */
public class TransactionOption2 implements Runnable {
    
    static Logger logger = LoggerFactory.getLogger(TransactionOption2.class);
    
    private CryptoAccountWithLock sender;
    private CryptoAccountWithLock receiver;
    private double amount;
    
    public TransactionOption2(CryptoAccountWithLock sender, CryptoAccountWithLock receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    
    /**
     * Execute the transaction by locking both accounts.
     * This can cause deadlock if two threads lock accounts in opposite order.
     */
    public void execute() {
        // Don't transfer to the same account
        if (sender.getAccountId() == receiver.getAccountId()) {
            return;
        }
        
        // Lock sender account
        sender.getLock().lock();
        try {
            // Lock receiver account
            receiver.getLock().lock();
            try {
                // Critical section: perform the transaction
                if (sender.withdraw(amount)) {
                    receiver.deposit(amount);
                }
            } finally {
                receiver.getLock().unlock();
            }
        } finally {
            sender.getLock().unlock();
        }
    }
    
    @Override
    public void run() {
        execute();
    }
}
