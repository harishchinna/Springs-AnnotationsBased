package com.cg.app.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.InsufficientFundsException;
import com.cg.app.exception.InvalidInputException;

@Aspect
@Component
public class SavingsAccountServiceImplValidations {
	@Around("execution(* com.cg.app.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		double amount = (double) param[1];
		SavingsAccount balance = (SavingsAccount) param[0];
		double currentBalance = balance.getBankAccount().getAccountBalance();

		if (amount > 0 && amount <= currentBalance) {
			pjp.proceed();
		} else
			throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");

	}

	@Around("execution(* com.cg.app.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		double amount = (double) param[1];
		if (amount > 0) {
			pjp.proceed();
		} else
			throw new InvalidInputException("Invalid Input Amount!");
	}
}
