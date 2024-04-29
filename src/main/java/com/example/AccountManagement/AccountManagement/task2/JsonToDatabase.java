package com.example.AccountManagement.AccountManagement.task2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JsonToDatabase {

    public static void main(String[] args) {
        try {
            // Load JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("transactions.json"));

            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction", "root", "mayank@12");

            
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                String txnId = (String) jsonObject.get("txn_id");
                double txnAmount = (double) jsonObject.get("txn_amount");
                String txnType = (String) jsonObject.get("txn_type");
                String txnAt = (String) jsonObject.get("txn_at");

                
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO transactions (txn_id, txn_amount, txn_type, txn_at) VALUES (?, ?, ?, ?)");
                pstmt.setString(1, txnId);
                pstmt.setDouble(2, txnAmount);
                pstmt.setString(3, txnType);
                pstmt.setString(4, txnAt);

                
                pstmt.executeUpdate();
            }

            
            conn.close();
            System.out.println("Transactions inserted into the database successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
