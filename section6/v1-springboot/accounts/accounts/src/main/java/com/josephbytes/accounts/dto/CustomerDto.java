package com.josephbytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account information")
public class CustomerDto {

    @Schema(description = "Name of the Customer", example = "John Doe")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(description = "Email of the Customer", example = "john.doe@mail.com")
    @NotEmpty(message = "The email shouldn't be empty or null")
    @Email(message = "The email Address should have a valid value")
    private String email;


    @Schema(description = "Mobile Number of the Customer", example = "9876543210")
    @NotEmpty(message = "The mobile Number shouldn't be empty or null")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "The mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account Details of the Customer", implementation = AccountDto.class)
    private AccountDto accountDto;
}
