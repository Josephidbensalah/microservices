package com.josephbytes.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(name = "Loans", description = "Schema to hold Loans information")
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class LoansDto {
    @Schema(description = "Loan Number of the Customer", example = "1234567890")
    private String mobileNumber;

    @Schema(description = "Loan Number of the Customer", example = "1234567890")
    private String loanNumber;

    @Schema(description = "Loan Type of the Customer", example = "Home Loan")
    private String loanType;

    @Schema(description = "Total Loan Amount of the Customer", example = "1000000")
    private Double totalLoan;

    @Schema(description = "Amount Paid by the Customer of the Loan", example = "10")
    private Double amountPaid;

    @Schema(description = "Outstanding Amount of the Loan", example = "500000")
    private Double outstandingAmount;


}
