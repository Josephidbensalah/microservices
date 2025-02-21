package com.josephbytes.accounts.controller;

import com.josephbytes.accounts.constants.AccountConstants;
import com.josephbytes.accounts.dto.CustomerDto;
import com.josephbytes.accounts.dto.ResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        return ResponseEntity
                .status(201)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

}
