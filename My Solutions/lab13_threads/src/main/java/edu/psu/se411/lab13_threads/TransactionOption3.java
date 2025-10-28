package edu.psu.se411.lab13_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Transaction class - Option 3: Use tryLock with timeout to avoid deadlock
 * This is the correct solution that prevents deadlock.
 */
public class TransactionOption3 implements Runnable {
    
    static Logger logger = LoggerFactory.getLogger(TransactionOption3.class);
    
    private CryptoAccountWithLock sender;
    private CryptoAccountWithLock receiver;
    private double amount;
    
    public TransactionOption3(CryptoAccountWithLock sender, CryptoAccountWithLock receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
    
    /**
     * Execute the transaction using tryLock with timeout to avoid deadlock.
     * If we can't acquire both locks, we release any held locks and retry.
     */
    public void execute() {
        // Don't transfer to the same account
        if (sender.getAccountId() == receiver.getAccountId()) {
            return;
        }
        
        ReentrantLock lock1 = sender.getLock();
        ReentrantLock lock2 = receiver.getLock();
        
        while (true) {
            try {
                // Try to acquire first lock with shorter timeout (10ms instead of 100ms)
                if (lock1.tryLock(10, TimeUnit.MILLISECONDS)) {
                    try {
                        // Try to acquire second lock with shorter timeout
                        if (lock2.tryLock(10, TimeUnit.MILLISECONDS)) {
                            try {
                                // Critical section: perform the transaction
                                if (sender.withdraw(amount)) {
                                    receiver.deposit(amount);
                                }
                                break; // Success! Exit the while loop
                            } finally {
                                lock2.unlock();
                            }
                        }
                        // If we couldn't get lock2, lock1 will be released in the finally block
                        // and we'll retry from the beginning
                    } finally {
                        lock1.unlock();
                    }
                }
                // If we couldn't get lock1, just retry
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while trying to acquire locks", e);
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
    
    @Override
    public void run() {
        execute();
    }
}
