package org.wwu.bpm.gta.creditscore;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;

public class GenerateCreditRecord extends ServletProcessApplication  implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		int CreditScore = 0;
		
		//CreditScore is the average of all 4 inputscore that build the basis for the Creditscore
		CreditScore = ((int) execution.getVariable("InternationalDebtScore") 
					+ (int) execution.getVariable("InternationalObligationScore")
					+ (int) execution.getVariable("OutstandingDebtScore")
					+ (int) execution.getVariable("PayrollScore"))/4;
		
		execution.setVariable("CreditScore", CreditScore); 
	}
	public void createEntry(DelegateExecution execution) throws Exception{
		Connection con = connectDatabase();
		PreparedStatement ps;
		
		// need to create a timestamp SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		ps = con.prepareStatement("INSERT INTO 'applicants'('firstName','lastName', 'passportNumber', 'creditRating', 'lastRating')"
												 + " VALUES('?','?','?','?','2017-04-12 11:11:11'");
		ps.setObject(0, execution.getVariable("firstName"));
		ps.setObject(1, execution.getVariable("lastName"));
		ps.setObject(2, execution.getVariable("passportNumber"));
		ps.setObject(3, execution.getVariable("CreditScore"));
		//ps.setObject(4, execution.getVariable("TIMESTAMPPPPPPPP")); 
		ps.executeQuery();
		con.close();
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