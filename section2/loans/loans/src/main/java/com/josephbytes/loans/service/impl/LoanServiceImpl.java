package com.josephbytes.loans.service.impl;

import com.josephbytes.loans.dto.LoansDto;
import com.josephbytes.loans.entity.Loans;
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
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        return LoansMapper.mapToLoanDto(loan, new LoansDto());

    }

    @Override
    public boolean updateLoan(LoansDto loanDto) {
        // update loan
        boolean isUpdated = false;
        if (loanDto != null){
            Loans loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber())
                    .orElseThrow(() -> new RuntimeException("Loan not found"));

            LoansMapper.mapToLoan(loanDto, loan);
            loanRepository.save(loan);
            isUpdated = true;
        }
        return isUpdated;
    }


    @Override
    public boolean deleteLoan(String loanNumber) {
        boolean isDeleted = false;
        Loans loan = loanRepository.findByLoanNumber(loanNumber)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loanRepository.delete(loan);
        isDeleted = true;
        return isDeleted;
    }
}
