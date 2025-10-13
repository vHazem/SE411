package edu.psu.se411;

import edu.psu.se411.exceptions.InsufficientFundsException;
import edu.psu.se411.model.WalletAccount;

public class EX3 {

	public static void main(String[] args) {
		WalletAccount account = new WalletAccount(1000);
        try {
            account.withdraw(1500);
        } catch (InsufficientFundsException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
	}

}
