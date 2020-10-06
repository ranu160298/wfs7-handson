package com.org.model.service;

import java.util.List;

import com.org.exception.AccountNotFoundException;
import com.org.exception.InsufficientBalanceException;
import com.org.model.beans.Account;


//use throws clause on transfer method : AccountNotFoundException, InsufficientBalanceException 
public interface AccountService {
	public Account createAccount(Account account);
	public double getBalance(int accountNumber) throws AccountNotFoundException;
	// call debit() and credit() on source & destination account
	public void transfer(int sourceAccount, int destinationAccount, double amount) throws InsufficientBalanceException, AccountNotFoundException;
	public List<Account> getAccountsSortedByName();
	public List<Account> getAccountsSortedByAccountNumber();
}