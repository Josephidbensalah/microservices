package com.josephbytes.loans.service;

import com.josephbytes.loans.dto.LoansDto;

import java.util.List;

public interface ILoanService {

    void createLoan(LoansDto loanDto);

    List<LoansDto> getAllLoans();

    LoansDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoansDto loanDto);

    boolean deleteLoan(String moneyNumber);
}
