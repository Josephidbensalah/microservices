package com.josephbytes.loans.mapper;

import com.josephbytes.loans.dto.LoansDto;
import com.josephbytes.loans.entity.Loans;

public class LoansMapper {

    public static LoansDto mapToLoanDto(Loans loan, LoansDto loanDto) {
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setOutstandingAmount(loan.getTotalLoan() - loan.getAmountPaid());


        return loanDto;
    }

    public static Loans mapToLoan(LoansDto loanDto, Loans loan) {
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loanDto.getTotalLoan() - loanDto.getAmountPaid());
        return loan;
    }
}
