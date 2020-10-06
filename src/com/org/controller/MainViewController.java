package com.org.controller;

import java.util.List;
import java.util.Scanner;


import com.org.exception.AccountNotFoundException;
import com.org.exception.InsufficientBalanceException;
import com.org.model.beans.Account;
import com.org.model.dao.AccountDao;
import com.org.model.service.AccountService;
import com.org.model.util.ObjectFactory;

public class MainViewController {

	public static void main(String[] args) throws InsufficientBalanceException, AccountNotFoundException {
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		AccountService service = ObjectFactory.getAccountServiceInstance();
		AccountDao dao=ObjectFactory.getAccountDaoInstance();
		do {
			System.out.println("1: Create Account 2: Check Balance");
			System.out.println("3: Transfer Amount 4: Sort Accounts by name 5: Sort Accounts by account number 0: Exit");
			option = scanner.nextInt();
			List<Account> list = null;
			switch(option) {
			case 1: 
				System.out.println("Enter name");
				Account account = new Account(scanner.next());
				Account createdAccount = service.createAccount(account);
				System.out.println(createdAccount);
				break;
			case 2: 
				System.out.println("Enter account number");
				int accNumber=scanner.nextInt();
				service.getBalance(accNumber-1);
				
				break; 
			case 3: 
				System.out.println("enter to and from account number money has to be deposited ");
				int from=scanner.nextInt();
				int to=scanner.nextInt();
				double amt=scanner.nextDouble();
				service.transfer(from,to, amt);
				break; 
			
			case 4: 
				list = service.getAccountsSortedByName(); // HttpSession -> setAttribute("key", list) -> ${ }
				list.forEach(acc -> System.out.println(acc));
				break;
			case 5:
				list = service.getAccountsSortedByAccountNumber(); // HttpSession -> setAttribute("key", list) -> ${ }
				list.forEach(acc -> System.out.println(acc));
				break;
				
			}
		} while(option != 0);
		
		scanner.close();
	}

}

