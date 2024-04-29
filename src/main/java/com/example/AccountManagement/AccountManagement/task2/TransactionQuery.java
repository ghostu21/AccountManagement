package com.example.AccountManagement.AccountManagement.task2;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionQuery {

    public List<Transaction> getTransactionsByPeriod(Timestamp fromDate, Timestamp toDate) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transactions", "root", "mayank@12");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM transactions WHERE txn_at BETWEEN ? AND ?");
            pstmt.setTimestamp(1, fromDate);
            pstmt.setTimestamp(2, toDate);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String txnId = rs.getString("txn_id");
                BigDecimal txnAmount = rs.getBigDecimal("txn_amount");
                String txnType = rs.getString("txn_type");
                Timestamp txnAt = rs.getTimestamp("txn_at");
                Transaction transaction = new Transaction(txnId, txnAmount, txnType, txnAt);
                transactions.add(transaction);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    
    public static List<Transaction> getLastMonthsTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transactions", "root", "mayank@12");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM transactions WHERE YEAR(txn_at) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND MONTH(txn_at) = MONTH(CURRENT_DATE - INTERVAL 1 MONTH) AND DAY(txn_at) = 1");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String txnId = rs.getString("txn_id");
                BigDecimal txnAmount = rs.getBigDecimal("txn_amount");
                String txnType = rs.getString("txn_type");
                Timestamp txnAt = rs.getTimestamp("txn_at");
                Transaction transaction = new Transaction(txnId, txnAmount, txnType, txnAt);
                transactions.add(transaction);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
