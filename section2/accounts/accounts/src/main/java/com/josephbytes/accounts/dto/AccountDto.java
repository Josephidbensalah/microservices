package com.josephbytes.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "The Account number can't be empty or null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "The Account number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "The Account number can't be empty or null")
    private String accountType;

    @NotEmpty(message = "The Branch Address can't be empty or null")
    private String branchAddress;
}
