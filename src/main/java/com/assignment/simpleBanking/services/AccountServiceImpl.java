package com.assignment.simpleBanking.services;

import com.assignment.simpleBanking.dtos.AccountResponseDto;
import com.assignment.simpleBanking.dtos.AccountWithAmountRequestDto;
import com.assignment.simpleBanking.entities.BankAccount;
import com.assignment.simpleBanking.entities.DepositTransaction;
import com.assignment.simpleBanking.entities.Transaction;
import com.assignment.simpleBanking.entities.WithdrawalTransaction;
import com.assignment.simpleBanking.exceptions.ErrorMessage;
import com.assignment.simpleBanking.exceptions.InsufficientBalanceException;
import com.assignment.simpleBanking.repositories.BankAccountDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final BankAccountDao bankAccountDao;


    public BankAccount getAccount(String accountNumber){
        Optional<BankAccount> bankAccount = bankAccountDao.findByAccountNumber(accountNumber);
        if(bankAccount.isPresent()){
            return bankAccount.get();
        }
        return null; // TODO throw
    }

    @Override
    public AccountResponseDto credit(String accountNumber, AccountWithAmountRequestDto accountWithAmountRequestDto) {
            BankAccount bankAccountToSave = createBankAccountOrGetExistOne(accountNumber);
            double amount = accountWithAmountRequestDto.getAmount();
            Transaction transaction = new DepositTransaction(amount);   //TODO
            bankAccountToSave.post(transaction);
            bankAccountDao.save(bankAccountToSave);
            return new AccountResponseDto(transaction.getApprovalCode());
    }


    @Override
    public AccountResponseDto debit(String accountNumber, AccountWithAmountRequestDto accountWithAmountRequestDto) {
        BankAccount bankAccountToSave = createBankAccountOrGetExistOne(accountNumber);
        double amount = accountWithAmountRequestDto.getAmount();
        if(bankAccountToSave.getBalance() < amount){
            throw new InsufficientBalanceException(ErrorMessage.BALANCE_CANNOT_BE_INSUFFICIENT);
        }
        Transaction transaction = new WithdrawalTransaction(amount);    // TODO
        bankAccountToSave.post(transaction);
        bankAccountDao.save(bankAccountToSave);
        return new AccountResponseDto(transaction.getApprovalCode());
    }

    public BankAccount createBankAccountOrGetExistOne(String accountNumber){
        Optional<BankAccount> bankAccount = bankAccountDao.findByAccountNumber(accountNumber);
        if(!bankAccount.isPresent()){
            System.out.println("Created new bank account..");
           return new BankAccount("Jim",accountNumber);
        }
        return bankAccount.get();
    }

}
