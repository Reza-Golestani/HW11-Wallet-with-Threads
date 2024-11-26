package src.service;

import src.model.Wallet;

public class TransactionService {

    public void withdraw(Wallet wallet, int amount) {
        if (wallet.getBalance() < amount) {
            System.out.println("Insufficient funds");
        } else {
            wallet.setBalance(wallet.getBalance() - amount);
        }
    }

    public void deposit(Wallet wallet, int amount) {
        wallet.setBalance(wallet.getBalance() + amount);
    }

}
