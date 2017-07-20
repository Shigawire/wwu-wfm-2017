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
		
		String passportNumber = (String) execution.getVariable("passportNumber");
		
		Applicant applicant = new Applicant(passportNumber);
		
		boolean applicantExists = applicant.existsInDatabase();
		
		execution.setVariable("applicantExists", applicantExists);
		
		if (applicantExists) {
			applicant.loadFromDatabase();
			execution.setVariable("creditRating", applicant.creditRating);
			execution.setVariable("lastRating", applicant.lastRating);
			
			//how many days since the last rating 
			double daysAgo = (System.currentTimeMillis() - applicant.lastRating.getTime()) / (24 * 60 * 60 * 1000d);

			execution.setVariable("daysAgo", daysAgo);
		} else {
			//this should be the manual user task 
			String firstName = (String) execution.getVariable("firstName");
			String lastName = (String) execution.getVariable("lastName");
			
			applicant.createInDatabase(firstName, lastName);
		}
		
		execution.setVariable("applicantPassport", passportNumber);
	}
}