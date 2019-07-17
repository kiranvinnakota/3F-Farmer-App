package com.calibrage.a3ffarmerapp.Model;

public class PaymentHistoryModel {
    private String ledgerItem;
    private String dateOfPayment;
    private String amount;
    private String bankAccountNumber;
    private String bankHolderName;
    private String bankName;

    public PaymentHistoryModel( String dateOfPayment, String amount, String bankAccountNumber, String bankHolderName, String bankName) {
        this.ledgerItem = ledgerItem;
        this.dateOfPayment = dateOfPayment;
        this.amount = amount;
        this.bankAccountNumber = bankAccountNumber;
        this.bankHolderName = bankHolderName;
        this.bankName = bankName;
    }

    public String getLedgerItem() {
        return ledgerItem;
    }

    public void setLedgerItem(String ledgerItem) {
        this.ledgerItem = ledgerItem;
    }

    public String getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankHolderName() {
        return bankHolderName;
    }

    public void setBankHolderName(String bankHolderName) {
        this.bankHolderName = bankHolderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
