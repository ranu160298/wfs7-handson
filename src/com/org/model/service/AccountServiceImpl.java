package com.org.model.service;

import java.util.List;
import java.util.stream.Collectors;

import com.org.exception.AccountNotFoundException;
import com.org.exception.InsufficientBalanceException;
import com.org.model.beans.Account;
import com.org.model.dao.AccountDao;
import com.org.model.util.ObjectFactory;

public class AccountServiceImpl implements AccountService {

	
	private AccountDao accountDao = null;
	
	public AccountServiceImpl() {
		accountDao = ObjectFactory.getAccountDaoInstance();
	}
	
	
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.createAccount(account);
	}

	@Override
	public double getBalance(int accountNumber) throws AccountNotFoundException {

		Account a=accountDao.getAccount(accountNumber);		
		return a.getBalance();
	}

	@Override
	public void transfer(int sourceAccount, int destinationAccount, double amount) throws InsufficientBalanceException, AccountNotFoundException {
		
		Account sa=accountDao.debit(sourceAccount,amount);
		Account da=accountDao.credit(destinationAccount, amount);
		
	}

	
	@Override
	public List<Account> getAccountsSortedByName() {
		List<Account> accounts = accountDao.getAccounts();
		List<Account> sortedAccount = accounts.stream()
		.sorted((account1, account2) -> account1.getCustomerName().compareTo(account2.getCustomerName()))
		.collect(Collectors.toList());
		
		return sortedAccount;
	}

	@Override
	public List<Account> getAccountsSortedByAccountNumber() {
		// TODO Auto-generated method stub
		List<Account> accounts = accountDao.getAccounts();
		List<Account> sortedAccount = accounts.stream()
		.sorted((account1, account2) -> account1.getAccountNumber()-(account2.getAccountNumber()))
		.collect(Collectors.toList());
		
		return sortedAccount;
		
	}

}
