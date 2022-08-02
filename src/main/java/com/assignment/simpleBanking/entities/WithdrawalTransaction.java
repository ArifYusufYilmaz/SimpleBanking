package com.assignment.simpleBanking.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="withdrawal_transactions")
@NoArgsConstructor

public class WithdrawalTransaction extends Transaction{

    public WithdrawalTransaction(double amount) {
        super(amount);
    }

    @Override
    public void generateTransaction(BankAccount bankAccount) {
            this.setBankAccount(bankAccount);
            this.getBankAccount().debit(this.getAmount());
    }
}
