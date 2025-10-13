package edu.spu.se411.lab08_logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.spu.se411.lab08_logging.exceptions.InsufficientFundsException;
import edu.spu.se411.lab08_logging.model.WalletAccount;

public class App {
	
	static Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		
		logger.info("Application is starting...");
		
		WalletAccount account = new WalletAccount(1000);
        try {
            account.withdraw(1500);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        try {
            account.deposit(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
	}

}
