package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class InferRecommendation extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		// TODO Auto-generated method stub
		//boolean recommendation is set false in default
//		Applicant app;
//		app = (Applicant) execution.getVariable("Applicant");
//		boolean creditRecommendation = false; 
//		double creditScore = app.getCreditScore();
//		
//		//if the creditScore is equal to or higher than 0.8 the recommendation is true
//		
//		if(creditScore >= 0.8){
//		creditRecommendation = true;
//	}else{
//		creditRecommendation = false; 
//		}
//	app.setRecommendation(creditRecommendation);
//	}
	}
 
}