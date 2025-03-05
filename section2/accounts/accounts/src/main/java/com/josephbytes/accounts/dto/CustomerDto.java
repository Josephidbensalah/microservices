package com.josephbytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotEmpty(message = "The email shouldn't be empty or null")
    @Email(message = "The email Address should have a valid value")
    private String email;

    @NotEmpty(message = "The mobile Number shouldn't be empty or null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "The mobile number must be 10 digits")
    private String mobileNumber;

    private AccountDto accountDto;
}
