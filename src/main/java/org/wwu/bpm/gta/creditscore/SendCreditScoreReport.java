package org.wwu.bpm.gta.creditscore;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendCreditScoreReport extends ServletProcessApplication implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String passportNumber = (String) execution.getVariable("passportNumber");
		Applicant applicant = new Applicant(passportNumber);
		
		applicant.loadFromDatabase();
		
		int recommendation = (int) execution.getVariable("creditRecommendation");
		
		String recomm = (recommendation == 1) ? "yes" : "no";
		
		System.out.println("Credit Score Recommendation Process done");
		System.out.println("Applicants Credit Score is " + applicant.creditRating);
		System.out.println("Our recommendation is: " + recomm);
		
		System.out.println("Submitting recommendation back...");
		
		HttpClient client = HttpClients.createDefault();
			
			RequestBuilder requestBuilder = RequestBuilder.get()
			.setUri("http://25.29.121.99:8000/api/creditrating")
			.addParameter("customer_id", "1")
			.addParameter("rating", applicant.creditRating.toString())
			.addParameter("validityFrom", "2007-08-09")
			.addParameter("validityTo", "2008-09-08");
				
			// execute request
			HttpUriRequest request = requestBuilder.build();
//			HttpResponse response = client.execute(request);
//			// log debug information
//			System.out.println(request.getURI());
//			System.out.println(response.getStatusLine());
			
	}

}
