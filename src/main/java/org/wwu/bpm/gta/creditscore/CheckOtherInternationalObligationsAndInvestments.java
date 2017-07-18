package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckOtherInternationalObligationsAndInvestments extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// generates random variable for international Obligations and Investments in value range from 0 to 10
				int InternationalObligationScore = (int)(Math.random() * 1);	
				execution.setVariable("InternationalObligationScore", InternationalObligationScore);
				
	}
  
}