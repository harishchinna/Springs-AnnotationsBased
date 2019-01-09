package com.cg.app.service;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SavingsAccountLoggers {
	Logger logger = Logger.getLogger(SavingsAccountLoggers.class.getName());

	@Before("execution(* com.cg.app.service.SavingsAccountServiceImpl.withdraw(..))")
	public void log() {
		logger.info("Before - Starting withdraw method");
	}

	@After("execution(* com.cg.app.service.SavingsAccountServiceImpl.withdraw(..))")
	public void log1() {
		logger.info("After - withdraw successfully");
	}
	@AfterThrowing(pointcut=("execution(* com.cg.app.service.*.*(..))"),throwing="exe")
	public void log2(Exception exe)
	{
		logger.info("Exception is:"+exe.toString());
	}
}
