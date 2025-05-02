package com.josephbytes.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
@Schema(name = "Response", description = "Schema to hold Response information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    @Schema(description = "Status Code of the Response")
    private String statusCode;

    @Schema(description = "Status Message of the Response")
    private String statusMsg;

}
