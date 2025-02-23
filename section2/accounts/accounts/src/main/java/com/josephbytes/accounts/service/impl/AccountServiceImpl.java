package com.josephbytes.accounts.service.impl;

import com.josephbytes.accounts.constants.AccountConstants;
import com.josephbytes.accounts.dto.CustomerDto;
import com.josephbytes.accounts.entity.Account;
import com.josephbytes.accounts.entity.Customer;
import com.josephbytes.accounts.exception.CustomerAlreadyExistsException;
import com.josephbytes.accounts.mapper.CustomerMapper;
import com.josephbytes.accounts.repository.AccountRepository;
import com.josephbytes.accounts.repository.CustomerRepository;
import com.josephbytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        // Business logic to create account
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customerRepository.findByMobileNumber(customer.getMobileNumber())
                .ifPresent(c -> {
                    throw new CustomerAlreadyExistsException("Customer with mobile number already exists + " + c.getMobileNumber());
                });
        Customer savedCustomer =  customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCustomerId(customer.getCustomerId());
        return newAccount;
    }
}
