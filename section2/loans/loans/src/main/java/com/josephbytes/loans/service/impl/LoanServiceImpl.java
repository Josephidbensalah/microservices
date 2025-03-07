package com.josephbytes.loans.service.impl;

import com.josephbytes.loans.dto.LoansDto;
import com.josephbytes.loans.entity.Loans;
import com.josephbytes.loans.exception.LoanAlreadyExistsException;
import com.josephbytes.loans.exception.LoanNotFoundException;
import com.josephbytes.loans.mapper.LoansMapper;
import com.josephbytes.loans.repository.LoanRepository;
import com.josephbytes.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
    private final LoanRepository loanRepository;

    @Override
    public void createLoan(LoansDto loanDto) {
        // check if loan already exists
        loanRepository.findByMobileNumber(loanDto.getMobileNumber())
                .ifPresent(l -> {
                    throw new LoanAlreadyExistsException("Loan already exists with given mobile number " + loanDto.getMobileNumber());
                });
        // create loan
        Loans loan = LoansMapper.mapToLoan(loanDto, new Loans());
        loanRepository.save(loan);
    }

    @Override
    public List<LoansDto> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(l -> LoansMapper.mapToLoanDto(l, new LoansDto()))
                .toList();
    }

    @Override
    public LoansDto fetchLoan(String loanNumber) {
        Loans loan = loanRepository.findByLoanNumber(loanNumber)
                .orElseThrow(() -> new LoanNotFoundException("Loans", "loanNumber", loanNumber));

        return LoansMapper.mapToLoanDto(loan, new LoansDto());

    }

    @Override
    public boolean updateLoan(LoansDto loanDto) {
        // update loan
        boolean isUpdated = false;
        if (loanDto != null){
            Loans loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber())
                    .orElseThrow(() -> new LoanNotFoundException("Loans", "loanNumber", loanDto.getLoanNumber()));

            LoansMapper.mapToLoan(loanDto, loan);
            loanRepository.save(loan);
            isUpdated = true;
        }
        return isUpdated;
    }


    @Override
    public boolean deleteLoan(String mobileNumber) {
        boolean isDeleted = false;
        Loans loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new LoanNotFoundException("Loans", "mobileNumber", mobileNumber));

        loanRepository.delete(loan);
        isDeleted = true;
        return isDeleted;
    }
}
