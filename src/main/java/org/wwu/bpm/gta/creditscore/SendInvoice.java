package org.wwu.bpm.gta.creditscore;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;


public class SendInvoice extends ServletProcessApplication implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		HttpClient client = HttpClients.createDefault();
		
		RequestBuilder requestBuilder = RequestBuilder.get()
		.setUri("http://25.29.121.99:8000/api/createinvoice")
		.addParameter("issuer", "GTA")
		.addParameter("amount", "5000")
		.addParameter("date", "1992-10-02")
		.addParameter("payment_status", "paid")
		.addParameter("remark","ok");

		// execute request
		HttpUriRequest request = requestBuilder.build();
		HttpResponse response = client.execute(request);
		// log debug information
		System.out.println(request.getURI());
		System.out.println(response.getStatusLine());
		
	}
}
