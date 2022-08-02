package com.assignment.simpleBanking.controllers;

import com.assignment.simpleBanking.dtos.AccountResponseDto;
import com.assignment.simpleBanking.dtos.AccountWithAmountRequestDto;
import com.assignment.simpleBanking.entities.BankAccount;
import com.assignment.simpleBanking.entities.DepositTransaction;
import com.assignment.simpleBanking.entities.Transaction;
import com.assignment.simpleBanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// account/v1
@RestController
@RequestMapping("/account/v1")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    // /credit/{accountNumber} post
    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<AccountResponseDto> credit(@PathVariable String accountNumber, @RequestBody AccountWithAmountRequestDto accountWithAmountRequestDto){

        AccountResponseDto accountResponseDto = accountService.credit(accountNumber, accountWithAmountRequestDto);
        return new ResponseEntity<>(accountResponseDto,HttpStatus.OK);
    }
    // /debit/{accountNumber} post
    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<AccountResponseDto> debit(@PathVariable String accountNumber, @RequestBody AccountWithAmountRequestDto accountWithAmountRequestDto){
        AccountResponseDto accountResponseDto = accountService.debit(accountNumber, accountWithAmountRequestDto);
        return new ResponseEntity<>(accountResponseDto,HttpStatus.OK);
    }

    // /{accountNumber} get
    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber){
        BankAccount bankAccount = accountService.getAccount(accountNumber);
        return new ResponseEntity<>(bankAccount,HttpStatus.OK);
    }
}
