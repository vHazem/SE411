package edu.psu.se411;

import edu.psu.se411.exception.InsufficientFundsException;

public class WalletApp {
    public static void main(String[] args) {
        Wallet wallet = new Wallet(200.0);

        try {
            wallet.withdraw(300.0); 
        } catch (InsufficientFundsException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
