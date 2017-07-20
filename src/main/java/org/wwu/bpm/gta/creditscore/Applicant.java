package org.wwu.bpm.gta.creditscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Applicant extends ServletProcessApplication implements JavaDelegate {

	public String firstName;
	public String lastName;
	public String passportNumber;
	public Double creditRating;
	public Date lastRating;
	public Integer creditRecommendation;
	public Connection con;

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	// Constructor: Create new Applicant object using name, lastname and Passport ID
	public Applicant(String firstName, String lastName, String passportNumber) {
		
		this.passportNumber = passportNumber;
		
//		if (this.existsInDatabase()) {
//			this.loadFromDatabase();
//		} else {
//			this.createInDatabase(firstName, lastName);
//		}
//		
	
		
//		try {
//			ps = con.prepareStatement("INSERT INTO gta_agency.applicants (firstName, lastName, passportNumber) VALUES"
//					+ " (?, ?, ?) WHERE NOT EXISTS (SELECT * FROM gta_agency.applicants"
//					+ " WHERE applicants.passportNumber = ?)");
//			ps.setString(0, firstName);
//			ps.setString(1, lastName);
//			ps.setString(2, passportNumber);
//			ps.setString(3, passportNumber);
//			ps.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		try {
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}
	
	public Applicant(String passportNumber) {
		Connection con = connectDatabase();
		PreparedStatement ps;
		ResultSet rs;
		
		this.passportNumber = passportNumber;
		
	}

	public void loadFromDatabase() {
		
		Connection con = connectDatabase();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = con.prepareStatement("SELECT * from gta_agency.applicants WHERE applicants.passportNumber = ?");
			ps.setString(1, this.passportNumber);
			rs = ps.executeQuery();
			
			//assign fetched values 
			rs.next();
			this.firstName = rs.getString("firstName");
			this.lastName = rs.getString("lastName");
			this.creditRating = rs.getDouble("creditRating");
			this.lastRating = rs.getDate("lastRating");
			this.creditRecommendation = rs.getInt("creditRecommendation");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateDatabase() {
		
		Connection con = connectDatabase();
		PreparedStatement ps;
		ResultSet rs;
		
		System.out.println("Updating DB!");
		
		try {
			ps = con.prepareStatement("UPDATE gta_agency.applicants SET firstName = ?, lastName = ?, creditRating = ?, lastRating = ? WHERE passportNumber = ?;");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setDouble(3, this.creditRating);
			ps.setDate(4, new java.sql.Date(this.lastRating.getTime()));
			ps.setString(5, this.passportNumber);
			ps.execute();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void createInDatabase(String firstName, String lastName) {
		
		Connection con = connectDatabase();
		PreparedStatement ps;
		ResultSet rs;
		
		System.out.println("INSERT INTO");
		
		try {
			ps = con.prepareStatement("INSERT INTO gta_agency.applicants (firstName, lastName, passportNumber) VALUES (?, ?, ?);");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, this.passportNumber);
			ps.execute();			
			
			
			//now assign values to this instance.
			
			this.firstName = firstName;
			this.lastName = lastName;
			this.creditRating = null;
			this.lastRating = null;
			this.creditRecommendation = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean existsInDatabase() {
		
		Connection con = connectDatabase();
		PreparedStatement ps;
		ResultSet rs;
		
		boolean exists = false;
		
		try {
			ps = con.prepareStatement("SELECT EXISTS(SELECT 1 FROM gta_agency.applicants WHERE applicants.passportNumber = ?)");
			ps.setString(1, this.passportNumber);
			rs = ps.executeQuery();
			
			//if there is an entry (i.e. we could iterate through it), the applicant exists.
			rs.next();
			exists = rs.getInt(1) == 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}
	
	
	private static Connection connectDatabase() {
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
