package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateInvestigationVolume extends ServletProcessApplication  implements JavaDelegate {

	double CREDIT_SCORE_CHECK_PRICE = 0.75;
	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub
		
		int investigationVolume = (int)(Math.random() * 1000);	
	}
 
}