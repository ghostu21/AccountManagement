package com.example.AccountManagement.AccountManagement.task2;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Random;

public class JsonGenerator {

    public static void main(String[] args) {

        
        int numRecords = 1000;

        
        JSONArray transactions = new JSONArray();

        
        Random random = new Random();

        
        for (int i = 0; i < numRecords; i++) {
            JSONObject transaction = new JSONObject();

            // Generate random transaction ID
            String txnId = "TXN" + i;

            // Generate random transaction amount
            BigDecimal txnAmount = BigDecimal.valueOf(random.nextDouble() * 1000);

            // Generate random transaction type
            String[] txnTypes = {"DEBIT", "CREDIT"};
            String txnType = txnTypes[random.nextInt(txnTypes.length)];

            // Generate random transaction timestamp
            long offset = Timestamp.valueOf("2020-01-01 00:00:00").getTime();
            long end = Timestamp.valueOf("2023-01-01 00:00:00").getTime();
            long diff = end - offset + 1;
            Timestamp txnAt = new Timestamp(offset + (long) (Math.random() * diff));

            transaction.put("txn_id", txnId);
            transaction.put("txn_amount", txnAmount);
            transaction.put("txn_type", txnType);
            transaction.put("txn_at", txnAt.toString());

            
            transactions.add(transaction);
        }

        
        try (FileWriter file = new FileWriter("transactions.json")) {
            file.write(transactions.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
