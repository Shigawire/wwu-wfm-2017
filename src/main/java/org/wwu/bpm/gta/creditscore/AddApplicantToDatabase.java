package org.wwu.bpm.gta.creditscore;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class AddApplicantToDatabase implements TaskListener {	 

	  @Override
	  public void notify(DelegateTask delegateTask) {
		  
		  System.out.println(delegateTask.getVariables());
		  
		  String firstName = (String) delegateTask.getVariable("ApplicantFirstName");
		  String lastName = (String) delegateTask.getVariable("ApplicantLastName");
		  
		  String passportNumber = (String) delegateTask.getVariable("PassportNumber");
		  
		  passportNumber = (String) delegateTask.getVariable("passportNumber");
		  
		  Applicant applicant = new Applicant(passportNumber);
		  applicant.createInDatabase(firstName, lastName);
	  }		  
}
