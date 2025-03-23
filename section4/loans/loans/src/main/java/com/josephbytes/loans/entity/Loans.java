package com.josephbytes.loans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class Loans extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="loan_id")
    private Long id;


    @Column(name = "mobile_number")
    private String mobileNumber;


    @Column(name = "loan_number")
    private String loanNumber;


    @Column(name = "loan_type")
    private String loanType;


    @Column(name = "total_loan")
    private Double totalLoan;


    @Column(name = "amount_paid")
    private Double amountPaid;


    @Column(name = "outstanding_amount")
    private Double outstandingAmount;




}

