package com.cg.app.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cg.app.account.SavingsAccount;

public class SavingsAccountDAOMapper implements RowMapper<SavingsAccount> {

	@Override
	public SavingsAccount mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		int accountNumber = resultSet.getInt(1);
		String accountHolderName = resultSet.getString("account_hn");
		double accountBalance = resultSet.getDouble(3);
		boolean salary = resultSet.getInt("salary") == 1 ? true : false;
		resultSet.getDouble(5);
		resultSet.getString(6);
		SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance, salary);

		return savingsAccount;
	}

}
