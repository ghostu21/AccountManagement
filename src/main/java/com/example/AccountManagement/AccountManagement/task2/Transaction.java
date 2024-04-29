package com.example.AccountManagement.AccountManagement.task2;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Transaction {

    private String txnId;
    private BigDecimal txnAmount;
    private String txnType;
    private Timestamp txnAt;

    public Transaction(String txnId, BigDecimal txnAmount, String txnType, Timestamp txnAt) {
        this.txnId = txnId;
        this.txnAmount = txnAmount;
        this.txnType = txnType;
        this.txnAt = txnAt;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public Timestamp getTxnAt() {
        return txnAt;
    }

    public void setTxnAt(Timestamp txnAt) {
        this.txnAt = txnAt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "txnId='" + txnId + '\'' +
                ", txnAmount=" + txnAmount +
                ", txnType='" + txnType + '\'' +
                ", txnAt=" + txnAt +
                '}';
    }
}
