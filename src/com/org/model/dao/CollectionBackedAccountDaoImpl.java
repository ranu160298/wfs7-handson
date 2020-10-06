package com.org.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.org.exception.AccountNotFoundException;
import com.org.exception.InsufficientBalanceException;
import com.org.model.beans.Account;

public class CollectionBackedAccountDaoImpl implements AccountDao {

	private static List<Account> database = new ArrayList<>();
	@Override
	public Account createAccount(Account account) {
		database.add(account);
		return account;
	}



	@Override
	public Account getAccount(int accountNumber) throws AccountNotFoundException {
		for(Account item:database){
			if(accountNumber==item.getAccountNumber()){
				return item;
			}
			else{
				throw new AccountNotFoundException("Account not found");
			}
			
		}
		return null;
	}

	@Override
	public List<Account> getAccounts() {
		return database;
	}


	@Override
	public void deleteAccount(int accountNumber) {
		// TODO Auto-generated method stub
		for(Account sa: database) {
			if (sa.getAccountNumber() == accountNumber) {
				this.database.remove(sa);
			}
		}
		
	}
	

	@Override
	public Account debit(int accountNumber, double amount) throws InsufficientBalanceException, AccountNotFoundException {
		Account account = this.getAccount(accountNumber);
		
		if (account != null) {
			double currentAccountBalance = account.getBalance();
			if (currentAccountBalance >= amount) {
				currentAccountBalance = currentAccountBalance - amount;
				account.setBalance(currentAccountBalance);
				return account;
			} else if(currentAccountBalance < amount) {
				throw new InsufficientBalanceException("Do not have sufficient balance");
				
			}
		}
		else
		{
			throw new AccountNotFoundException("Account not found");
		}
		return null;
		
		
	}



	@Override
	public Account credit(int accountNumber, double amount) throws AccountNotFoundException {
		Account account = this.getAccount(accountNumber);
		if (account != null) {
			double currentAccountBalance = account.getBalance();
			account.setBalance(currentAccountBalance + amount);
			return account;
		}
		else{
			throw new AccountNotFoundException("Do not have sufficient balance");
		}
		
	}

}
