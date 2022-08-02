package com.assignment.simpleBanking.entities;

import com.assignment.simpleBanking.exceptions.ErrorMessage;
import com.assignment.simpleBanking.exceptions.InsufficientBalanceException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="bank_accounts")
@Data

public class BankAccount {

    @Id
    @SequenceGenerator(name="BankAccount", sequenceName = "BANK_ACCOUNT_ID_SEQ")
    @GeneratedValue(generator = "BankAccount",strategy=GenerationType.SEQUENCE)
    Long id;
    @Column(name="owner")
    private String owner;
    @Column(name="account_Number")
    private String accountNumber;
    @Column(name="balance")
    private double balance;
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public BankAccount(String owner, String accountNumber){
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.transactions = new ArrayList<>();
    }

    public BankAccount() {
        this.transactions = new ArrayList<>();
    }

    public void post(Transaction transaction){
        transactions.add(transaction);
        transaction.generateTransaction(this);
    }



    public void credit(Double amount){    //TODO add the supplied amount to the balance
        balance = balance + amount;
    }
    public void debit(Double amount){ //TODO substract the supplied amount from the balance
        if(balance < amount){
            throw new InsufficientBalanceException(ErrorMessage.BALANCE_CANNOT_BE_INSUFFICIENT);
        }
        balance = balance - amount;
    }
}
