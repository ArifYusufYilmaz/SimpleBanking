package com.assignment.simpleBanking;

import com.assignment.simpleBanking.entities.BankAccount;
import com.assignment.simpleBanking.entities.DepositTransaction;
import com.assignment.simpleBanking.entities.PhoneBillPaymentTransaction;
import com.assignment.simpleBanking.entities.WithdrawalTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleBankingApplication.class, args);

		BankAccount account = new BankAccount("Jim", "12345");
		account.post(new DepositTransaction(1000));
		account.post(new WithdrawalTransaction(200));
		account.post(new PhoneBillPaymentTransaction("Vodafone", "5423345566", 96.50));


		System.out.println("balance : "+ account.getBalance());
		System.out.println("transaction size :  "+account.getTransactions().size());
	}

}
