package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckPayrollInformation extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// generates random variable in value range from 0 to 1 that represents the ability to pay, based on applicants payroll
			double PayrollScore = (double)(Math.random() * 1);	
			execution.setVariable("PayrollScore", PayrollScore);
				
	}
 
}