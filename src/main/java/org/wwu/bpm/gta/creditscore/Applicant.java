package org.wwu.bpm.gta.creditscore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
	public double outstandingCredits;
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

	/*
	 *  Get Methods(non-Javadoc)
	 * @see org.camunda.bpm.application.AbstractProcessApplication#getName()
	 */

	public String getName() {
		if (name == "") {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;
			
			try {
				ps = con.prepareStatement("SELECT name FROM gta_agency.applicants WHERE passport = ?)");
				ps.setObject(0, passport);
				rs = ps.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
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

	public double getOutstandingCredits() {
		return outstandingCredits;
	}
	
	/*
	 *  Set Methods
	 */

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

	public void setCreditScore(double appCreditScore) {
		creditScore = appCreditScore;
		Connection con = connectDatabase();
		PreparedStatement ps;

		Date today = new Date();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-YYYY");
		String date = DATE_FORMAT.format(today);

		try {
			ps = con.prepareStatement(
					"UPDATE gta_agency.applicants SET creditRating = ?, lastRating = ? WHERE passportNumber = ?)");
			ps.setObject(0, creditScore);
			ps.setObject(1, date);
			ps.setObject(2, passport);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDebtInformation(double appDebtInformation) {
		debtInformation = appDebtInformation;
		Connection con = connectDatabase();
		PreparedStatement ps;

		try {
			ps = con.prepareStatement("UPDATE gta_agency.applicants SET debtInformation = ? WHERE passwordNumber = ?)");
			ps.setObject(0, debtInformation);
			ps.setObject(1, passport);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setInvestmentInformation(double appInvestmentInformation) {
		investmentInformation = appInvestmentInformation;
		Connection con = connectDatabase();
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(
					"UPDATE gta_agency.applicants SET investmentInformation = ? WHERE passportNumber = ?)");
			ps.setObject(0, investmentInformation);
			ps.setObject(1, passport);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setRecommendation(boolean appRecommendation) {
		int recValue = 0;
		Connection con = connectDatabase();
		PreparedStatement ps;

		try {
			ps = con.prepareStatement("UPDATE gta_agency.applicants SET recommendation = ? WHERE passportNumber = ?)");
			ps.setObject(0, recValue);
			ps.setObject(1, passport);
			
			
			// if Recommended to give credit the recommendation value is set to 1 otherwise it remains 0
			if (appRecommendation)
				recValue = 1;
			else recValue = 0;

			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setOutstandingCredits (double appOutstandingCredits) {
		outstandingCredits = appOutstandingCredits;
		Connection con = connectDatabase();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("UPDATE gta_agency.applicants SET outstandingCredits = ? WHERE passportNumber = ?)");
			ps.setObject(0, outstandingCredits);
			ps.setObject(1, passport);
			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// enable connection to the database

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
