package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class GenerateCreditRecord extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
/*		
 * I guess, this is part of CalculateCreditScore
 * 
 * int CreditScore = 0;
		
		//CreditScore is the average of all 4 inputscore that build the basis for the Creditscore
		CreditScore = ((int) execution.getVariable("InternationalDebtScore") 
					+ (int) execution.getVariable("InternationalObligationScore")
					+ (int) execution.getVariable("OutstandingDebtScore")
					+ (int) execution.getVariable("PayrollScore"))/4;
		
		execution.setVariable("CreditScore", CreditScore); 
		*/

	int intObl;
	int intDebt;
	int outDebt;
	int payroll;
	intObl  = (int) execution.getVariable("InternationalObligationScore");
	intDebt = (int)	execution.getVariable("InternationalDebtScore");
	outDebt = (int)	execution.getVariable("OutstandingDebtScore");
	payroll = (int)	execution.getVariable("PayrollScore");
		
	/*
	 * Database connection
	 * add columns to table regarding each variable
	 * add values of current creditRecord to each column for the right person
	 * can be accessed afterwards, if it is asked for creditRecord before 90 days are over
	 */

	}
 
}