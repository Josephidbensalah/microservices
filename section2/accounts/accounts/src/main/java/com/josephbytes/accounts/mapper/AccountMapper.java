package com.josephbytes.accounts.mapper;

import com.josephbytes.accounts.dto.AccountDto;
import com.josephbytes.accounts.entity.Accounts;

public class AccountMapper {
    public static AccountDto mapToAccountDto(Accounts account, AccountDto accountDto) {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return accountDto;
    }

    public static Accounts mapToAccount(AccountDto accountDto, Accounts account) {
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }
}
