package edu.psu.se411.lab08_logging;

import edu.psu.se411.lab08_logging.exceptions.InsufficientFundsException;
import edu.psu.se411.lab08_logging.model.WalletAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("Application starts");

        WalletAccount account = new WalletAccount(1000);
        logger.debug("Wallet account created with balance: 1000");

        try {
            logger.debug("Attempting to withdraw 1500");
            account.withdraw(1500);
        } catch (InsufficientFundsException e) {
            logger.warn("InsufficientFundsException object created: {}", e.getMessage());
            logger.error("Exception thrown during withdrawal", e);
            System.out.println("Exception caught: " + e.getMessage());
        }

        try {
            logger.debug("Attempting to deposit -100");
            account.deposit(-100);
        } catch (IllegalArgumentException e) {
            logger.warn("IllegalArgumentException object created: {}", e.getMessage());
            logger.error("Exception thrown during deposit", e);
            System.out.println("Exception caught: " + e.getMessage());
        }

        logger.info("Application ends");
    }
}
