package org.wwu.bpm.gta.creditscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.camunda.bpm.application.impl.ServletProcessApplication;

public class CheckApplicantExists extends ServletProcessApplication {
 
	
	
	
	//if the applicant exists, get ID, date of last checking, score of last checking. 
	//need to calculate the duration between last check and current date then. 
	//duration is better to be named as "durationDay". (Because it's used in decision table)
	
	
		public static void main (String[] args) {
			String firstname = "";
			String lastname = "";
			String birthDate = "";
			
			
			Connection con = connectDatabase();
			boolean exists = checkApplicant (con, firstname, lastname, birthDate);
			
			if (exists) {
				// trigger check record
			} else {
				// trigger create new account calling a web form to insert applicant data
			}
			
		}
		
		public static Connection connectDatabase () {
			String url = ""; //enter database url
			String username = ""; //enter database username
			String password = ""; //enter database password
			
			System.out.println("Connection database...");
			
			try (Connection connection = DriverManager.getConnection(url, username, password)){
					System.out.println("Database Connected!");
					return connection;
			} catch (SQLException e) {
				throw new IllegalStateException ("Connot connect to database!", e);
			}
			
		}

		public static boolean checkApplicant(Connection con, String firstname, String lastname, String birthDate) {
			boolean exists = false;
			PreparedStatement ps;
			ResultSet rs;
			
			try {
				ps = con.prepareStatement("SELECT EXISTS(Select * FROM customer WHERE firstname = ? AND lastname = ? AND birthdate = ?" );
				ps.setObject(0, firstname);
				ps.setObject(1, lastname);
				ps.setObject(2, birthDate);
				rs = ps.executeQuery();
				if (rs == null) 
					exists = true;
				else exists = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return exists;
			
			
		}
	
	
}