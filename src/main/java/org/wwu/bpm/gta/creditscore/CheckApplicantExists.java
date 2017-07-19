package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

//@ProcessApplication("Credit Score Calculation App")
public class CheckApplicantExists extends ServletProcessApplication implements JavaDelegate {

	//if the applicant exists, get ID, date of last checking, score of last checking. 
	//need to calculate the duration between last check and current date then. 
	//duration is better to be named as "durationDay". (Because it's used in decision table)
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String firstName = (String) execution.getVariable("firstName");
		String lastName = (String) execution.getVariable("lastName");
		String passportNumber = (String) execution.getVariable("passportNumber");
		
		Object applicant = new Applicant(firstName, lastName, passportNumber);
		
		execution.setVariable("applicantExists", "true");
		execution.setVariable("applicant", "none");
		execution.setVariable("durationDay", 20);
		execution.setVariable("creditScore", 10);
		execution.setVariable("applicant", 1);
	}
}