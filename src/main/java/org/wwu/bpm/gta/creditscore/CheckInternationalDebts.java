package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckInternationalDebts extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// generates random variable InternationalDebtScore in value range from 0 to 10
		int InternationalDebtScore = (int)(Math.random() * 10);	
		execution.setVariable("InternationalDebtScore", InternationalDebtScore);
		
	}
 
}