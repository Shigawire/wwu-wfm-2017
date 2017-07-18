package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckOutstandingCredits extends ServletProcessApplication implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// generates random variable in value range from 0 to 10 that represents an applicants outstanding dept situation
				int OutstandingDebtScore = (int)(Math.random() * 1);	
				execution.setVariable("OutstandingDebtScore", OutstandingDebtScore);
		
	}
 
}


