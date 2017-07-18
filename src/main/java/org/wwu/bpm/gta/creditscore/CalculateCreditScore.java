package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateCreditScore extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		int CreditScore = 0;
		
		//CreditScore is the average of all 4 inputscore that build the basis for the Creditscore
		CreditScore = ((int) execution.getVariable("InternationalDebtScore") 
					+ (int) execution.getVariable("InternationalObligationScore")
					+ (int) execution.getVariable("OutstandingDebtScore")
					+ (int) execution.getVariable("PayrollScore"))/4;
		
		execution.setVariable("CreditScore", CreditScore);
	}
 
}