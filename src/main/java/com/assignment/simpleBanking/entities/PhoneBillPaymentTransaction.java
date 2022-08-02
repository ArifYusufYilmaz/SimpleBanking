package com.assignment.simpleBanking.entities;

import lombok.Data;

import javax.persistence.Entity;

// TODO entity
public class PhoneBillPaymentTransaction extends Transaction{
    private String brand;
    private String phoneNumber;
    public PhoneBillPaymentTransaction(String brand, String phoneNumber, double amount){
        super(amount);
        this.brand = brand;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void generateTransaction(BankAccount bankAccount) {
        this.setBankAccount(bankAccount);
        this.getBankAccount().debit(this.getAmount());
    }
}
