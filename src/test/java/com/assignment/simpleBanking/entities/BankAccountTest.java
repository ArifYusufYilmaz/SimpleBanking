package com.assignment.simpleBanking.entities;

import com.assignment.simpleBanking.exceptions.InsufficientBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
class BankAccountTest {

    @Test
    void post() {
    }

    @Test
    void testCreateAccountAndSetBalance0() {
        BankAccount bankAccount = new BankAccount("Yusuf Yilmaz", "14542");
        assertTrue(bankAccount.getOwner().equals("Yusuf Yilmaz"));
        assertTrue(bankAccount.getAccountNumber().equals("14542"));
        assertTrue(bankAccount.getBalance() == 0);
    }
    @Test
    void testDebit() {
            BankAccount bankAccount = new BankAccount("Yusuf Yilmaz", "9834");
            bankAccount.credit(100.0);
            assertTrue(bankAccount.getBalance() == 100);
            bankAccount.debit(30.0);
            assertTrue(bankAccount.getBalance() == 70);
    }
    @Test
    void testDebitException() {
        Assertions.assertThrows(InsufficientBalanceException.class, ()->{
            BankAccount bankAccount = new BankAccount("Yusuf Yilmaz", "9834");
            bankAccount.credit(100.0);
            bankAccount.debit(300.0);
        });
    }
    @Test
    void testTransactions(){
        //Create bank account
        BankAccount bankAccount = new BankAccount("Arif Yusuf", "1234");
        assertTrue(bankAccount.getTransactions().size() == 0);
        // Deposit Transaction
        DepositTransaction depositTransaction = new DepositTransaction(100);
        assertTrue(depositTransaction.getDate() != null);
        bankAccount.post(depositTransaction);
        assertTrue(bankAccount.getBalance() == 100);
        assertTrue(bankAccount.getTransactions().size() == 1);
        //Withdrawal Transaction
        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction(45);
        assertTrue(withdrawalTransaction.getDate() != null);
        bankAccount.post(withdrawalTransaction);
        assertTrue(bankAccount.getBalance() == 55);
        assertTrue(bankAccount.getTransactions().size() == 2);
        //PhoneBillPaymentTransaction
        PhoneBillPaymentTransaction phoneBillPaymentTransaction = new PhoneBillPaymentTransaction("Vodafone","",20);
        assertTrue(phoneBillPaymentTransaction.getDate() != null);
        bankAccount.post(phoneBillPaymentTransaction);
        assertTrue(bankAccount.getBalance() == 35);
        assertTrue(bankAccount.getTransactions().size() == 3);
    }
}













