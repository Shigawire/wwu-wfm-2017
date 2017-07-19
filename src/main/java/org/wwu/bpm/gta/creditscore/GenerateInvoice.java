package org.wwu.bpm.gta.creditscore;

import java.util.logging.Logger;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class GenerateInvoice extends ServletProcessApplication  implements JavaDelegate {
	
	double CREDIT_SCORE_CHECK_PRICE = 0.75;
	private final static Logger LOGGER = Logger.getLogger("GTA");
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		int investigationVolume = (int) execution.getVariable("investigationVolume");
		double netPrice = investigationVolume * CREDIT_SCORE_CHECK_PRICE;
		
		LOGGER.info("YOLO, received volume:" + investigationVolume);
		
	}
 
}