package com.cg.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.CurrentAccount;
import com.cg.app.exception.AccountNotFoundException;

public interface CurrentAccountDAO {
	CurrentAccount createNewAccount(CurrentAccount account) throws SQLException, ClassNotFoundException;
	boolean updateAccount(CurrentAccount account) throws ClassNotFoundException, SQLException; 
	CurrentAccount getAccountByID(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;
	 CurrentAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException;
	 List<CurrentAccount> getAllCurrentAccount() throws SQLException, ClassNotFoundException;
	 void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException;
	 double checkBalance(int accountNumber) throws ClassNotFoundException, SQLException;

}
