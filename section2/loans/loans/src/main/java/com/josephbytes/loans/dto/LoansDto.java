package com.josephbytes.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Schema(name = "Loans", description = "Schema to hold Loans information")
@Data
public class LoansDto {

    @NotEmpty(message = "Mobile Number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number should be 10 digits")
    @Schema(description = "Loan Number of the Customer", example = "1234567890")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Loan Number should be 10 digits")
    @Schema(description = "Loan Number of the Customer", example = "1234567890")
    private String loanNumber;

    @NotEmpty(message = "Loan Type cannot be empty")
    @Schema(description = "Loan Type of the Customer", example = "Home Loan")
    private String loanType;

    @Positive(message = "Total Loan Amount cannot be negative or zero")
    @Schema(description = "Total Loan Amount of the Customer", example = "1000000")
    private Double totalLoan;

    @PositiveOrZero(message = "Amount Paid cannot be negative")
    @Schema(description = "Amount Paid by the Customer of the Loan", example = "10")
    private Double amountPaid;

    @PositiveOrZero(message = "Outstanding Amount cannot be negative")
    @Schema(description = "Outstanding Amount of the Loan", example = "500000")
    private Double outstandingAmount;


}
