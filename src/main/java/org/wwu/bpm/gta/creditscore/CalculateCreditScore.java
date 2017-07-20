package org.wwu.bpm.gta.creditscore;

import java.util.Date;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateCreditScore extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String passportNumber = (String) execution.getVariable("passportNumber");
		
		Applicant applicant = new Applicant(passportNumber);
		applicant.loadFromDatabase();

		double InternationalDebtScore = (double) execution.getVariable("InternationalDebtScore");
		double InternationalObligationScore = (double) execution.getVariable("InternationalObligationScore");
		double OutstandingDebtScore = (double) execution.getVariable("OutstandingDebtScore");
		double PayrollScore = (double) execution.getVariable("PayrollScore");
		
		double creditRating =  (InternationalDebtScore + InternationalObligationScore + OutstandingDebtScore + PayrollScore)/4;
		
		applicant.creditRating = creditRating;
		applicant.lastRating = new Date();
		applicant.updateDatabase();
		
		
	}
	
	
 
}