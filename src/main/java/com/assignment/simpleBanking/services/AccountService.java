package com.assignment.simpleBanking.services;

import com.assignment.simpleBanking.dtos.AccountResponseDto;
import com.assignment.simpleBanking.dtos.AccountWithAmountRequestDto;
import com.assignment.simpleBanking.entities.BankAccount;

public interface AccountService {
    public BankAccount getAccount(String accountNumber);

    AccountResponseDto credit(String accountNumber, AccountWithAmountRequestDto accountWithAmountRequestDto);

    AccountResponseDto debit(String accountNumber, AccountWithAmountRequestDto accountWithAmountRequestDto);
}
