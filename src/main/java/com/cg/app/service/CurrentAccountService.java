package com.cg.app.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.CurrentAccount;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

public interface CurrentAccountService {
	CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double creditLimit) throws ClassNotFoundException, SQLException;

	CurrentAccount updateAccount(CurrentAccount account);

	CurrentAccount getAccountById(int accountNumber);

	CurrentAccount deleteAccount(int accountNumber);
	
	List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException;

	void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount) throws ClassNotFoundException, SQLException, InsufficientFundsException, InvalidInputException;
	void deposit(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException, InvalidInputException;
	void withdraw(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException, InsufficientFundsException, InvalidInputException;
	
}
