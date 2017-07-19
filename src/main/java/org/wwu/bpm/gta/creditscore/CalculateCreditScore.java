package org.wwu.bpm.gta.creditscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateCreditScore extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		Applicant a;
		a = (Applicant)execution.getVariable("Applicant");
		//Calculates the Score based on the DebtInformation, InvestmentInformation, OutstandingCredits and PayrollData
		a.setCreditScore((a.getDebtInformation()+a.getInvestmentInformation()+a.getOutstandingCredits()+a.getPayrollData())/4);
	}
	
	
 
}