package org.wwu.bpm.gta.creditscore;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;

public class GenerateCreditRecord extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		Applicant a;
		a = (Applicant)execution.getVariable("Applicant");
		
		//CreditScore is the average of all 4 inputscore that build the basis for the Creditscore
		//CreditScore =  (a.getDebtInformation()+a.getInvestmentInformation()+a.getPayrollData()+a.getOutstandingCredits())/4;
		
	a.setInvestmentInformation((double) execution.getVariable("InternationalObligationScore"));
	a.setDebtInformation((double) execution.getVariable("InternationalDebtScore"));
	a.setOutstandingCredits((double) execution.getVariable("OutstandingDebtScore"));
	a.setPayrollData((double) execution.getVariable("PayrollScore"));
	
	}
 
}