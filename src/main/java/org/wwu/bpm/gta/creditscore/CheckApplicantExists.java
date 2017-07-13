package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;


//@ProcessApplication("Credit Score Calculation App")
public class CheckApplicantExists extends ServletProcessApplication implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}