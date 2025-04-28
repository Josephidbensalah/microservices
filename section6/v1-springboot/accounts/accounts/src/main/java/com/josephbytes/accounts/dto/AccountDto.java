package com.josephbytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Account", description = "Schema to hold Account information")
public class AccountDto {

    @Schema(description = "Account Number of the Customer", example = "1234567890")
    @NotEmpty(message = "The Account number can't be empty or null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "The Account number must be 10 digits")
    private Long accountNumber;


    @Schema(description = "Account Type of the Customer", example = "Savings")
    @NotEmpty(message = "The Account number can't be empty or null")
    private String accountType;

    @Schema(description = "Branch Address of the Customer", example = "123, Main Street, New York")
    @NotEmpty(message = "The Branch Address can't be empty or null")
    private String branchAddress;
}
