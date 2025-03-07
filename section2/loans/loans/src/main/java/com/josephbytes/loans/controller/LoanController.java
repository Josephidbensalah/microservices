package com.josephbytes.loans.controller;

import com.josephbytes.loans.constants.LoansConstants;
import com.josephbytes.loans.dto.ErrorResponseDto;
import com.josephbytes.loans.dto.LoansDto;
import com.josephbytes.loans.dto.ResponseDTO;
import com.josephbytes.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Loan Controller", description = "APIs to manage Loans")
@RestController
@RequestMapping(value = "/api/loans", produces = "application/json")
@AllArgsConstructor
@Validated
public class LoanController {

    private final ILoanService loanService;

    @Operation(summary = "Get All Loans",
               description = "API to fetch all the loans from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "404", description = "Http Status Not Found")
    })
    @GetMapping("/")
    public ResponseEntity<List<LoansDto>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }


    @Operation(summary = "Fetch Loan",
               description = "API to fetch a loan based on the mobile number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "404", description = "Http Status Not Found")
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(
            @Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number should be 10 digits")
            @RequestParam String mobileNumber) {
        return ResponseEntity.ok(loanService.fetchLoan(mobileNumber));
    }


    @Operation(summary = "Create Loan",
               description = "API to create a new loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Http Status Created"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error")
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@Valid @RequestBody LoansDto loansDto) {
        loanService.createLoan(loansDto);
        return ResponseEntity.ok(
                new ResponseDTO(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }


    @Operation(summary = "Update Loan",
               description = "API to update an existing loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "417", description = "Http Status Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoan(@Valid @RequestBody LoansDto loansDto) {
        boolean isUpdated = loanService.updateLoan(loansDto);
        if (isUpdated) {
            return ResponseEntity.ok(
                    new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        } else {
            return ResponseEntity.ok(
                    new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
        }

    }


    @Operation(summary = "Delete Loan",
               description = "API to delete a loan based on the mobile number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Http Status OK"),
            @ApiResponse(responseCode = "417", description = "Http Status Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "Http Status Internal Server Error",
                        content = @Content(
                                schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoan(
            @Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number should be 10 digits")
            @RequestParam String mobileNumber) {
        boolean isDeleted = loanService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.ok(
                    new ResponseDTO(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));

        } else {
            return ResponseEntity.ok(
                    new ResponseDTO(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
        }
    }

}
