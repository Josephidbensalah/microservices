package com.josephbytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse", description = "Schema to hold Error Response information")
@Data @AllArgsConstructor
public class ErrorResponseDto {

    @Schema(description = "API Path that caused the error", example = "/api/v1/customers")
    private String apiPath;

    @Schema(description = "HTTP Status Code of the Response")
    private HttpStatus errorCode;

    @Schema(description = "Error Message of the Response")
    private String errorMessage;

    @Schema(description = "Time of the Error")
    private LocalDateTime errorTime;
}
