package com.josephbytes.accounts.service.impl;

import com.josephbytes.accounts.constants.AccountConstants;
import com.josephbytes.accounts.dto.AccountDto;
import com.josephbytes.accounts.dto.CustomerDto;
import com.josephbytes.accounts.entity.Accounts;
import com.josephbytes.accounts.entity.Customer;
import com.josephbytes.accounts.exception.CustomerAlreadyExistsException;
import com.josephbytes.accounts.exception.ResourceNotFoundException;
import com.josephbytes.accounts.mapper.AccountMapper;
import com.josephbytes.accounts.mapper.CustomerMapper;
import com.josephbytes.accounts.repository.AccountRepository;
import com.josephbytes.accounts.repository.CustomerRepository;
import com.josephbytes.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
                    throw new CustomerAlreadyExistsException("Customer with mobile number already exists  " + c.getMobileNumber());
                });

        Customer savedCustomer =  customerRepository.save(customer);

        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCustomerId(customer.getCustomerId());
        return newAccount;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(c -> CustomerMapper.mapToCustomerDto(c, new CustomerDto()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
               // .map(c -> CustomerMapper.mapToCustomerDto(c, new CustomerDto()))
                .orElseThrow(() -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));

        Accounts account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString()));

        CustomerDto customerDto =  CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto != null) {
           // check if the Account exist
            Accounts account = accountRepository.findById(accountDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account","accountId",accountDto.getAccountNumber().toString()));
            AccountMapper.mapToAccount(accountDto, account);
            accountRepository.save(account);

            // check if the Customer exist
            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer","customerId",customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        boolean isDeleted = false;
        // check if Account exists within the given mobile number
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNUmber",mobileNumber)
        );

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        isDeleted = true;
        return isDeleted;
    }


}
