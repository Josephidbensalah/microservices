package com.josephbytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Schema(name = "Response", description = "Schema to hold Response information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

    @Schema(description = "Status Code of the Response")
    private String statusCode;

    @Schema(description = "Status Message of the Response")
    private String statusMsg;
}
