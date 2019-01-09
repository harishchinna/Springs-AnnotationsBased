package com.cg.app.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.app.account.SavingsAccount;
import com.cg.app.dao.SavingsAccountDAO;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.factory.AccountFactory;

@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl() {
		factory = AccountFactory.getInstance();

	}

	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary)
			throws ClassNotFoundException, SQLException {
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	public void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {

		double currentBalance = account.getBankAccount().getAccountBalance();
		currentBalance += amount;
		savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		// savingsAccountDAO.commit();

	}

	public void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException {
		double currentBalance = account.getBankAccount().getAccountBalance();

		currentBalance -= amount;
		savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
		// savingsAccountDAO.commit();
	}

	@Transactional(rollbackForClassName = { "Throwable" })
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount)
			throws ClassNotFoundException, SQLException {

		deposit(receiver, amount);
		withdraw(sender, amount);

	}

	public boolean updateAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return savingsAccountDAO.updateAccount(account);
	}

	public SavingsAccount getAccountById(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.getAccountById(accountNumber);
	}

	public SavingsAccount deleteAccount(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.deleteAccount(accountNumber);

	}

	public double checkAccountBalance(int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException {
		return savingsAccountDAO.checkAccountBalance(accountNumber);

	}

	public SavingsAccount searchAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.searchAccount(accountNumber);
	}

	public List<SavingsAccount> searchAccountByHolderName(String holderName)
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.searchAccountByHolderName(holderName);
	}

	public List<SavingsAccount> sortByAccountHolderName() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountHolderName();
	}

	public List<SavingsAccount> sortByAccountHolderNameInDescendingOrder() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountHolderNameInDescendingOrder();
	}

	public List<SavingsAccount> sortByAccountBalance() throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByAccountBalance();
	}

	public List<SavingsAccount> sortByBalanceRange(int minimumBalance, int maximumBalance)
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByBalanceRange(minimumBalance, maximumBalance);
	}

	public List<SavingsAccount> sortByBalanceRangeInDescendingOrder(int minimumBalanceValue, int maximumBalanceValue)
			throws ClassNotFoundException, SQLException {
		return savingsAccountDAO.sortByBalanceRangeInDescendingOrder(minimumBalanceValue, maximumBalanceValue);
	}

}
