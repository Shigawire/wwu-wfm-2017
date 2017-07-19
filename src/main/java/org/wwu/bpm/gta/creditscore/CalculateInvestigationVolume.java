package org.wwu.bpm.gta.creditscore;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculateInvestigationVolume implements JavaDelegate {

	private final static Logger LOGGER = Logger.getLogger("GTA");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {		
		int investigationVolume = (int)(Math.random() * 1000);	
		execution.setVariable("investigationVolume", investigationVolume);
	}

}