package org.wwu.bpm.gta.creditscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class Applicant extends ServletProcessApplication implements JavaDelegate {

	public String name;
	public String lastname;
	public String passport;
	public double payrollData;
	public double creditScore;
	public double debtInformation;
	public double investmentInformation;
	public boolean recommendation;
	public Connection con;

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	// Constructor: Create new Applicant object using name, lastname and Passport ID
	public Applicant(String appName, String appLastname, String appPassport) {
		name = appName;
		lastname = appLastname;
		passport = appPassport;
		
	}

	// Get Methods

 	public String getName() {
		return name;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassport() {
		return passport;
	}

	public double getPayrollData() {
		return payrollData;
	}

	public double getCreditScore() {
		return creditScore;
	}

	public double getDebtInformation() {
		return debtInformation;
	}

	public double getInvestmentInformation() {
		return investmentInformation;
	}
	
	public boolean getRecommendation() {
		return recommendation;
	}

	// Set Methods

	public void setName(String appName) {
		name = appName;
		Connection con = connectDatabase();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("UPDATE applicants SET firstname = ? WHERE passportNumber = ?)");
			ps.setObject(0, name);
			ps.setObject(1, passport);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setLastname(String appLastname) {
		lastname = appLastname;
		Connection con = connectDatabase();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("UPDATE applicants SET lastname = ? WHERE passportNumber = ?)");
			ps.setObject(0, lastname);
			ps.setObject(1, passport);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPayrollData(double appPayrollData) {
		payrollData = appPayrollData;
		Connection con = connectDatabase();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("UPDATE gta_agency.applicants SET payrollData = ? WHERE passportNumber = ?)");
			ps.setObject(0, payrollData);
			ps.setObject(1, passport);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCreditHistory(double appCreditScore) {
		creditScore = appCreditScore;
		Connection con = connectDatabase();
		PreparedStatement ps;
		
		// get actual Time Stamp
		// 1) create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// 2) get a java.util.Date from the calendar instance.
//		    this date will represent the current instant, or "now".
		java.util.Date now = calendar.getTime();

		// 3) a java current time (now) instance
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime())
		
		try {
			ps = con.prepareStatement("UPDATE gta_agency.applicants SET creditRating = ?, lastRating = ? WHERE passportNumber = ?)");
			ps.setObject(0, creditScore);
		}
	}

	public void setDebtInformation() {

	}

	public void setInvestmentInformation() {

	}

	//enable connection to the database
	
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
