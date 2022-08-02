package com.assignment.simpleBanking.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="deposit_transactions")
@NoArgsConstructor

public class DepositTransaction extends Transaction{


    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void generateTransaction(BankAccount bankAccount) {
            this.setBankAccount(bankAccount);
            this.getBankAccount().credit(this.getAmount());

    }

}
