package com.cg.app.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.CurrentAccount;
import com.cg.app.dao.CurrentAccountDAO;
import com.cg.app.dao.CurrentAccountDAOImpl;
import com.cg.app.factory.AccountFactory;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

public class CurrentAccountServiceImpl implements CurrentAccountService {
	private AccountFactory factory;
	private CurrentAccountDAO currentAccountDAO;

	public CurrentAccountServiceImpl() {
		factory = AccountFactory.getInstance();
		currentAccountDAO = new CurrentAccountDAOImpl();
	}

	
	public CurrentAccount createNewAccount(String accountHolderName, double accountBalance, double creditLimit)
			throws ClassNotFoundException, SQLException {
		CurrentAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, creditLimit);
		currentAccountDAO.createNewAccount(account);
		return null;
	}

	public CurrentAccount updateAccount(CurrentAccount account) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public CurrentAccount getAccountById(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public CurrentAccount deleteAccount(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CurrentAccount> getAllCurrentAccount() throws ClassNotFoundException, SQLException {
		return currentAccountDAO.getAllCurrentAccount();
	}

	
	public void fundTransfer(CurrentAccount sender, CurrentAccount receiver, double amount)
			throws ClassNotFoundException, SQLException, InsufficientFundsException, InvalidInputException {
		withdraw(sender, amount);
		deposit(receiver, amount);
	}

	
	public void deposit(CurrentAccount account, double amount)
			throws ClassNotFoundException, SQLException, InvalidInputException {
		if (amount > 0) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			currentAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		} else {
			throw new InvalidInputException("Invalid Input Amount!");
		}
	}

	
	public void withdraw(CurrentAccount account, double amount) throws ClassNotFoundException, SQLException, InsufficientFundsException{
		double currentBalance=account.getBankAccount().getAccountBalance();

		if(amount>(account.getBankAccount().getAccountBalance()+account.getCreditLimit())){
			throw new InsufficientFundsException("Insufficient Funds");
		}else if(amount<0){
			throw new InvalidInputException("Invalid Amount");
		}else
			currentBalance-=amount;
	}
}
