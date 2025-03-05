package com.josephbytes.loans.controller;

import com.josephbytes.loans.constants.LoansConstants;
import com.josephbytes.loans.dto.ErrorResponseDto;
import com.josephbytes.loans.dto.LoansDto;
import com.josephbytes.loans.dto.ResponseDTO;
import com.josephbytes.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@AllArgsConstructor
public class LoanController {

    private final ILoanService loanService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "404", description = "Http Status Not Found")
    })
    @GetMapping("/")
    public ResponseEntity<List<LoansDto>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "404", description = "Http Status Not Found")
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam String loanNumber) {
        return ResponseEntity.ok(loanService.fetchLoan(loanNumber));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Http Status Created"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error")
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestBody LoansDto loansDto) {
        loanService.createLoan(loansDto);
        return ResponseEntity.ok(
                new ResponseDTO(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "417", description = "Http Status Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoan(@RequestBody LoansDto loansDto) {
        boolean isUpdated = loanService.updateLoan(loansDto);
        if (isUpdated) {
            return ResponseEntity.ok(
                    new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.ok(
                    new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "417", description = "Http Status Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoan(@RequestParam String loanNumber) {
        boolean isDeleted = loanService.deleteLoan(loanNumber);
        if (isDeleted) {
            return ResponseEntity.ok(
                    new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));

        } else {
            return ResponseEntity.ok(
                    new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }

}
