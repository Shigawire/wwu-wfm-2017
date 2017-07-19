package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@ProcessApplication("Credit Score Calculation App")
public class CreditScoreCalculationApplication extends ServletProcessApplication implements JavaDelegate {

	int creditScore; 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
	}
  // empty implementation
}
