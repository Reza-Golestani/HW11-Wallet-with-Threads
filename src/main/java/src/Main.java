package src;

import src.model.Wallet;
import src.service.TransactionService;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        TransactionService transactionService = new TransactionService();
        Wallet wallet = new Wallet(1, 1_000_000);

        Runnable withdrawTask = () -> {
            for (int i = 0; i < 1_000; i++) {
                transactionService.withdraw(wallet, 200);
//                System.out.println("Withdrawing 200");
            }
        };
        
        Runnable depositTask = () -> {
            for (int i = 0; i < 1_000; i++) {
                transactionService.deposit(wallet, 100);
//                System.out.println("Deposit 100");
            }
        };

        Runnable withdrawTaskTS = () -> {
            for (int i = 0; i < 1_000; i++) {
                synchronized (wallet) {
                    transactionService.withdraw(wallet, 200);
//                    System.out.println("Withdrawing 200");
                };
            }
        };
        Runnable depositTaskTS = () -> {
            for (int i = 0; i < 1_000; i++) {
                synchronized (wallet) {
                    transactionService.deposit(wallet, 100);
//                    System.out.println("Deposit 100");
                }
            }
        };

//        Thread firstThread = new Thread(withdrawTask);
//        Thread secondThread = new Thread(depositTask);
//        Thread thirdThread = new Thread(withdrawTask);

        Thread firstThread = new Thread(withdrawTaskTS);
        Thread secondThread = new Thread(depositTaskTS);
        Thread thirdThread= new Thread(withdrawTaskTS);

        firstThread.start();
        secondThread.start();
        thirdThread.start();


//        Thread.sleep(100);

        firstThread.join();
        secondThread.join();
        thirdThread.join();

        System.out.println("\nFinal balance: " + wallet.getBalance());

    }
}