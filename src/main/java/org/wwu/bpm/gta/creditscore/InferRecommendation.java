package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class InferRecommendation extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		String passportNumber = (String) execution.getVariable("passportNumber");
		
		Applicant applicant = new Applicant(passportNumber);
		applicant.loadFromDatabase();
		
		double creditRating = applicant.creditRating;

		//if the credit score is > 0.8
		int creditRecommendation = 0;
		
		if (creditRating >= 0.8) {
			creditRecommendation = 1;
		}
		
		execution.setVariable("creditRecommendation", creditRecommendation);
	}
 
}