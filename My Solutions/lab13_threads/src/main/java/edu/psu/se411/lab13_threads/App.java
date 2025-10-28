package edu.psu.se411.lab13_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Build and run using Maven goal: clean package exec:java
 */
public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		System.out.println("========================================");
		System.out.println("Exercise 1: Without Synchronization");
		System.out.println("========================================");
		testWithoutSynchronization();
		
		System.out.println("\n========================================");
		System.out.println("Exercise 2a: Option 1 - Synchronized Method");
		System.out.println("========================================");
		testOption1();
		
		System.out.println("\n========================================");
		System.out.println("Exercise 2b: Option 2 - Lock Both Accounts (May Deadlock)");
		System.out.println("========================================");
		testOption2();
		
		System.out.println("\n========================================");
		System.out.println("Exercise 2c: Option 3 - TryLock with Timeout (Correct Solution)");
		System.out.println("========================================");
		testOption3();
         
        logger.info("Application is closing...");
	}
	
	/**
	 * Exercise 1: Test without synchronization - demonstrates the problem
	 */
	private static void testWithoutSynchronization() {
		// Initialize 10 accounts with 1000 riyals each
        List<CryptoAccount> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            accounts.add(new CryptoAccount(i, 1000.0));
        }
        
        // Calculate and print initial total balance
        double initialTotal = calculateTotalBalance(accounts);
        System.out.println("Initial total balance: " + initialTotal + " riyals");
        
        // Create 10,000 threads for random transactions
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            CryptoAccount randomA = accounts.get((int)(Math.random() * 10));
            CryptoAccount randomB = accounts.get((int)(Math.random() * 10));
            Double randomAmount = (double)(Math.random() * 10 + 1);
            Transaction t = new Transaction(randomA, randomB, randomAmount);
            Thread thread = new Thread(t);
            threads.add(thread);
            thread.start();
        }
        
        // Wait for all threads to complete
        System.out.println("Waiting for all transactions to complete...");
        waitForThreads(threads);
        
        // Calculate and print final total balance
        double finalTotal = calculateTotalBalance(accounts);
        System.out.println("Final total balance: " + finalTotal + " riyals");
        System.out.println("Expected balance: " + initialTotal + " riyals");
        System.out.println("Difference (money lost/gained): " + (finalTotal - initialTotal) + " riyals");
        
        if (Math.abs(finalTotal - initialTotal) < 0.01) {
            System.out.println("✓ Balance is correct (unlikely without synchronization)");
        } else {
            System.out.println("✗ Balance is INCORRECT - Race condition occurred!");
        }
	}
	
	/**
	 * Option 1: Test with synchronized execute() method
	 */
	private static void testOption1() {
		// Initialize 10 accounts with 1000 riyals each
        List<CryptoAccount> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            accounts.add(new CryptoAccount(i, 1000.0));
        }
        
        double initialTotal = calculateTotalBalance(accounts);
        System.out.println("Initial total balance: " + initialTotal + " riyals");
        
        // Create 10,000 threads for random transactions
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            CryptoAccount randomA = accounts.get((int)(Math.random() * 10));
            CryptoAccount randomB = accounts.get((int)(Math.random() * 10));
            Double randomAmount = (double)(Math.random() * 10 + 1);
            TransactionOption1 t = new TransactionOption1(randomA, randomB, randomAmount);
            Thread thread = new Thread(t);
            threads.add(thread);
            thread.start();
        }
        
        System.out.println("Waiting for all transactions to complete...");
        waitForThreads(threads);
        
        double finalTotal = calculateTotalBalance(accounts);
        System.out.println("Final total balance: " + finalTotal + " riyals");
        System.out.println("Expected balance: " + initialTotal + " riyals");
        System.out.println("Difference: " + (finalTotal - initialTotal) + " riyals");
        
        if (Math.abs(finalTotal - initialTotal) < 0.01) {
            System.out.println("✓ Balance is correct");
        } else {
            System.out.println("✗ Balance is INCORRECT - Synchronization failed!");
        }
        
        System.out.println("\nObservation: Synchronized method DOES NOT solve the problem!");
        System.out.println("Why? Because each Transaction object has its own lock.");
        System.out.println("Multiple transactions can still access the same account simultaneously.");
	}
	
	/**
	 * Option 2: Test with locks on both accounts (can deadlock)
	 */
	private static void testOption2() {
		// Initialize 10 accounts with 1000 riyals each
        List<CryptoAccountWithLock> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            accounts.add(new CryptoAccountWithLock(i, 1000.0));
        }
        
        double initialTotal = calculateTotalBalanceWithLock(accounts);
        System.out.println("Initial total balance: " + initialTotal + " riyals");
        
        // Create 10,000 threads for random transactions
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            CryptoAccountWithLock randomA = accounts.get((int)(Math.random() * 10));
            CryptoAccountWithLock randomB = accounts.get((int)(Math.random() * 10));
            Double randomAmount = (double)(Math.random() * 10 + 1);
            TransactionOption2 t = new TransactionOption2(randomA, randomB, randomAmount);
            Thread thread = new Thread(t);
            threads.add(thread);
            thread.start();
        }
        
        System.out.println("Waiting for all transactions to complete...");
        System.out.println("WARNING: This may hang due to DEADLOCK!");
        waitForThreadsWithTimeout(threads, 10000); // 10 second timeout
        
        double finalTotal = calculateTotalBalanceWithLock(accounts);
        System.out.println("Final total balance: " + finalTotal + " riyals");
        System.out.println("Expected balance: " + initialTotal + " riyals");
        System.out.println("Difference: " + (finalTotal - initialTotal) + " riyals");
        
        if (Math.abs(finalTotal - initialTotal) < 0.01) {
            System.out.println("✓ Balance is correct");
        } else {
            System.out.println("✗ Balance is INCORRECT");
        }
        
        System.out.println("\nWhat could go wrong?");
        System.out.println("- DEADLOCK: Thread 1 locks Account A, Thread 2 locks Account B");
        System.out.println("  Then Thread 1 waits for Account B, Thread 2 waits for Account A");
        System.out.println("  Both threads wait forever!");
	}
	
	/**
	 * Option 3: Test with tryLock and timeout (correct solution)
	 */
	private static void testOption3() {
		// Initialize 10 accounts with 1000 riyals each
        List<CryptoAccountWithLock> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            accounts.add(new CryptoAccountWithLock(i, 1000.0));
        }
        
        double initialTotal = calculateTotalBalanceWithLock(accounts);
        System.out.println("Initial total balance: " + initialTotal + " riyals");
        
        // Create 10,000 threads for random transactions
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            CryptoAccountWithLock randomA = accounts.get((int)(Math.random() * 10));
            CryptoAccountWithLock randomB = accounts.get((int)(Math.random() * 10));
            Double randomAmount = (double)(Math.random() * 10 + 1);
            TransactionOption3 t = new TransactionOption3(randomA, randomB, randomAmount);
            Thread thread = new Thread(t);
            threads.add(thread);
            thread.start();
        }
        
        System.out.println("Waiting for all transactions to complete...");
        waitForThreads(threads);
        
        double finalTotal = calculateTotalBalanceWithLock(accounts);
        System.out.println("Final total balance: " + finalTotal + " riyals");
        System.out.println("Expected balance: " + initialTotal + " riyals");
        System.out.println("Difference: " + (finalTotal - initialTotal) + " riyals");
        
        if (Math.abs(finalTotal - initialTotal) < 0.01) {
            System.out.println("✓ Balance is correct - Problem solved!");
        } else {
            System.out.println("✗ Balance is INCORRECT");
        }
        
        System.out.println("\nThis solution prevents deadlock by:");
        System.out.println("1. Using tryLock with timeout instead of blocking lock");
        System.out.println("2. If second lock fails, releasing first lock and retrying");
        System.out.println("3. Eventually both locks are acquired and transaction completes");
	}
	
	/**
	 * Calculate the total balance across all accounts in the system.
	 */
	private static double calculateTotalBalance(List<CryptoAccount> accounts) {
	    double total = 0.0;
	    for (CryptoAccount account : accounts) {
	        total += account.getBalance();
	    }
	    return total;
	}
	
	/**
	 * Calculate the total balance for accounts with locks.
	 */
	private static double calculateTotalBalanceWithLock(List<CryptoAccountWithLock> accounts) {
	    double total = 0.0;
	    for (CryptoAccountWithLock account : accounts) {
	        total += account.getBalance();
	    }
	    return total;
	}
	
	/**
	 * Wait for all threads to complete.
	 */
	private static void waitForThreads(List<Thread> threads) {
		for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while waiting", e);
            }
        }
	}
	
	/**
	 * Wait for threads with a timeout (to detect deadlock).
	 */
	private static void waitForThreadsWithTimeout(List<Thread> threads, long timeoutMs) {
		long startTime = System.currentTimeMillis();
		for (Thread thread : threads) {
            try {
            	long elapsed = System.currentTimeMillis() - startTime;
            	long remaining = timeoutMs - elapsed;
            	if (remaining <= 0) {
            		System.out.println("TIMEOUT! Some threads may be deadlocked.");
            		return;
            	}
                thread.join(remaining);
                if (thread.isAlive()) {
                	System.out.println("TIMEOUT! Some threads may be deadlocked.");
                	return;
                }
            } catch (InterruptedException e) {
                logger.error("Thread interrupted while waiting", e);
            }
        }
	}

}
