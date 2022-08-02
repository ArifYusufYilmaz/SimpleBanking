package com.assignment.simpleBanking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public abstract class Transaction {
    @Id
    @SequenceGenerator(name="Transaction", sequenceName = "TRANSACTION_ID_SEQ")
    @GeneratedValue(generator = "Transaction",strategy=GenerationType.SEQUENCE)
    private Long id;
    @CreatedDate
    private Date date;
    private double amount;
    private UUID approvalCode;
    private String type;

    @ManyToOne
    @JsonIgnore
    private BankAccount bankAccount;

    public Transaction(double amount){
        this.amount = amount;
        this.approvalCode = UUID.randomUUID();
        type = this.getClass().getSimpleName();
        this.date = new Date();
    }

    public abstract void generateTransaction(BankAccount bankAccount);

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                '}';
    }

}
