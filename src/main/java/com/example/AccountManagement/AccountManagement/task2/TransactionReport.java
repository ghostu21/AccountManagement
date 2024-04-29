package com.example.AccountManagement.AccountManagement.task2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TransactionReport {

    public static void generateCSVReport(List<Transaction> transactions, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.append("Transaction ID,Transaction Amount,Transaction Type,Transaction At\n");
            for (Transaction transaction : transactions) {
                writer.append(String.format("%s,%.2f,%s,%s\n", transaction.getTxnId(), transaction.getTxnAmount(), transaction.getTxnType(), transaction.getTxnAt()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
