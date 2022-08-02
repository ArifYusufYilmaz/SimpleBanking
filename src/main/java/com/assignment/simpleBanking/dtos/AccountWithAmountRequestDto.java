package com.assignment.simpleBanking.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

public class AccountWithAmountRequestDto {
    private double amount;
}
