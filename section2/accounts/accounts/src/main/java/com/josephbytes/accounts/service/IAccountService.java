package com.josephbytes.accounts.service;

import com.josephbytes.accounts.dto.CustomerDto;

public interface IAccountService {
    void createAccount(CustomerDto customerDto);
}
