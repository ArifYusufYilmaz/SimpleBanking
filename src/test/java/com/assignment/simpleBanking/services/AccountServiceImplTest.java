package com.assignment.simpleBanking.services;

import com.assignment.simpleBanking.dtos.AccountResponseDto;
import com.assignment.simpleBanking.dtos.AccountWithAmountRequestDto;
import com.assignment.simpleBanking.entities.BankAccount;
import com.assignment.simpleBanking.entities.Transaction;
import com.assignment.simpleBanking.entities.WithdrawalTransaction;
import com.assignment.simpleBanking.exceptions.ErrorMessage;
import com.assignment.simpleBanking.exceptions.InsufficientBalanceException;
import com.assignment.simpleBanking.repositories.BankAccountDao;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private BankAccountDao bankAccountDao;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Test
    void shouldGetAccount() {
        BankAccount bankAccount = Mockito.mock(BankAccount.class);
        Mockito.when(bankAccountDao.findByAccountNumber("12345")).thenReturn(Optional.of(bankAccount));
        assertEquals(bankAccount, accountServiceImpl.getAccount("12345"));
    }

    @Test
    void credit() {

    }
    @Test
    void shouldDebit(){
    }
    @Test
    void shouldThrowInsufficientBalanceExceptionWhileDebitting() {
        BankAccount bankAccount = Mockito.mock(BankAccount.class);
        Mockito.when(bankAccountDao.findByAccountNumber("12345")).thenReturn(Optional.of(bankAccount));
        Mockito.doReturn(100.0).when(bankAccount).getBalance();
        AccountWithAmountRequestDto accountWithAmountRequestDto = Mockito.mock(AccountWithAmountRequestDto.class);
        Mockito.doReturn(200.0).when(accountWithAmountRequestDto).getAmount();
        InsufficientBalanceException insufficientBalanceException =
                                                                    assertThrows(InsufficientBalanceException.class,
                            ()-> accountServiceImpl.debit("12345",accountWithAmountRequestDto));
        assertEquals(ErrorMessage.BALANCE_CANNOT_BE_INSUFFICIENT, insufficientBalanceException.getBaseErrorMessage());
    }

    @Test
    void shouldCreateBankAccount() {
       Mockito.when(bankAccountDao.findByAccountNumber("12345")).thenReturn(Optional.ofNullable(null));
        BankAccount bankAccount = Mockito.mock(BankAccount.class);
        Mockito.when(bankAccount.getOwner()).thenReturn("Jim");
        assertEquals(bankAccount.getOwner(), accountServiceImpl.createBankAccountOrGetExistOne("12345").getOwner());
    }
    @Test
    void shouldGetExistBankAccount() {
        BankAccount bankAccount = Mockito.mock(BankAccount.class);
        Mockito.when(bankAccountDao.findByAccountNumber("12345")).thenReturn(Optional.of(bankAccount));
        assertEquals(bankAccount, accountServiceImpl.createBankAccountOrGetExistOne("12345"));
    }
}