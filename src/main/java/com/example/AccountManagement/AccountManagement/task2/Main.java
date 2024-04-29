package com.example.AccountManagement.AccountManagement.task2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
      
       
        List<Transaction> lastMonthsTransactions;
		try {
			lastMonthsTransactions = TransactionQuery.getLastMonthsTransactions();
			TransactionReport.generateCSVReport(lastMonthsTransactions, "last_month_transactions.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
    }
}
