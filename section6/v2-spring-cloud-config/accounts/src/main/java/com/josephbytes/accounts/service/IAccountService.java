package com.josephbytes.accounts.service;

import com.josephbytes.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IAccountService {
    void createAccount(CustomerDto customerDto);
    List<CustomerDto> getAllCustomers();

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
