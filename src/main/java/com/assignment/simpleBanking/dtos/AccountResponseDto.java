package com.assignment.simpleBanking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
public class AccountResponseDto {
    //private String status; //
    private UUID approvalCode;

    public AccountResponseDto(UUID approvalCode) {
        this.approvalCode = approvalCode;
    }
}
