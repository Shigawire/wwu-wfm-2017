package org.wwu.bpm.gta.creditscore;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@ProcessApplication("Credit Score Calculation App")
public class CheckApplicantExists extends ServletProcessApplication implements JavaDelegate {

	//if the applicant exists, get ID, date of last checking, score of last checking. 
	//need to calculate the duration between last check and current date then. 
	//duration is better to be named as "durationDay". (Because it's used in decision table)
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		String firstName = (String) execution.getVariable("firstName");
		String lastName = (String) execution.getVariable("lastName");
		String passportNumber = (String) execution.getVariable("passportNumber");
		
		System.out.println("VARIABLES");
		System.out.println(execution.getVariables());
		
		Object applicant = getApplicant(firstName, lastName, passportNumber);
		
		execution.setVariable("applicantExists", "true");
		execution.setVariable("applicant", "none");
	}
		
		public static Object getApplicant(String firstname, String lastname, String passportNumber) {
			
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;
			Object applicant = new Object();
			
			try {
				ps = con.prepareStatement("SELECT * FROM applicants WHERE firstName = ? AND lastName = ? AND passportNumber = ?" );
				ps.setObject(0, firstname);
				ps.setObject(1, lastname);
				ps.setObject(2, passportNumber);
				rs = ps.executeQuery();
				System.out.println(rs);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return applicant;
		}
		
		private static Connection connectDatabase () {
			String url = "jdbc:mysql://62.210.90.98:3306/gta_agency";
			String username = "root"; 
			String password = "password";
			
			System.out.println("Connection database...");
			
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url, username, password);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return connection;
		}

}