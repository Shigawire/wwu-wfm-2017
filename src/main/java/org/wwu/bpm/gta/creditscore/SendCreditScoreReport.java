package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCreditScoreReport extends ServletProcessApplication implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Applicant app;
		app = (Applicant) execution.getVariable("Applicant");
		if(app.getCreditRecommendation = false){
			System.out.println("The applicant with the name " + app.getName() + " " + app.getLastname() + " with "
					+ "the passport-ID: " + app.getPassport() + " should be declined due to a creditscore of "
					+ app.getCreditScore() + " which is not trustworthy!");
		}else if(app.getCreditRecommendation = true){
			System.out.println("The applicant with the name " + app.getName() + " " + app.getLastname() + " with "
					+ "the passport-ID: " + app.getPassport() + " is recommended to be able to get a credit"
							+ " due to a creditscore of "
					+ app.getCreditScore() + " which is trustworthy!");
		}else {
			System.out.println("Something went wrong!");
		}
	}

}
