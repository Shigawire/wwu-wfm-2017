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

	public String name;
	public String lastname;
	public String passport;
	public double payrollData;
	public double creditScore;
	public double debtInformation;
	public double investmentInformation;
	public boolean recommendation;
	public double outstandingCredits;
	public String date;
	public Connection con;

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	// Constructor: Create new Applicant object using name, lastname and Passport ID
	public Applicant(String appName, String appLastname, String appPassport) {

		// I know not best practice but no Idea how to do it else
		// The following inserts the given Strings to the database if applicant is not stored already
		Connection con = connectDatabase();
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement("INSERT INTO gta_agency.applicants (firstName, lastName, passportNumber) VALUES"
					+ " (?, ?, ?) WHERE NOT EXISTS (SELECT * FROM gta_agency.applicants"
					+ " WHERE applicants.passportNumber = ?)");
			ps.setString(0, appName);
			ps.setString(1, appLastname);
			ps.setString(2, appPassport);
			ps.setString(3, appPassport);
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

	/*
	 * Get Methods(non-Javadoc)
	 * 
	 * @see org.camunda.bpm.application.AbstractProcessApplication#getName()
	 */

	public String getName() {
		if (name == "") {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;

			try {
				ps = con.prepareStatement("SELECT firstName FROM gta_agency.applicants WHERE passport = ?)");
				ps.setObject(0, passport);
				rs = ps.executeQuery();
				name = rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return name;
	}

	public String getLastname() {
		if (lastname == "") {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;

			try {
				ps = con.prepareStatement("SELECT lastName FROM gta_agency.applicants WHERE passportNumber = ?)");
				ps.setString(0, passport);
				rs = ps.executeQuery();
				lastname = rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return name;
	}

	public String getPassport() {
		return passport;
	}

	public double getPayrollData() {
		if (payrollData == 0) {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;

			try {
				ps = con.prepareStatement("SELECT payrollData FROM gta_agency.applicants WHERE passportNumber = ?)");
				ps.setString(0, passport);
				rs = ps.executeQuery();
				payrollData = rs.getDouble(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return payrollData;
	}

	public double getCreditScore() {
		if (creditScore == 0) {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;

			try {
				ps = con.prepareStatement("SELECT creditRating FROM gta_agency.applicants WHERE passportNumber = ?)");
				ps.setString(0, passport);
				rs = ps.executeQuery();
				creditScore = rs.getDouble(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return creditScore;
	}

	public double getDebtInformation() {
		if (debtInformation == 0) {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;

			try {
				ps = con.prepareStatement("SELECT deptInformation FROM gta_agency.applicants WHERE passportNumber = ?)");
				ps.setString(0, passport);
				rs = ps.executeQuery();
				debtInformation = rs.getDouble(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return debtInformation;
	}

	public double getInvestmentInformation() {
		if (investmentInformation == 0) {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;

			try {
				ps = con.prepareStatement("SELECT name FROM gta_agency.applicants WHERE passportNumber = ?)");
				ps.setString(0, passport);
				rs = ps.executeQuery();
				investmentInformation = rs.getDouble(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return investmentInformation;
	}

	public boolean getRecommendation() {
		Connection con = connectDatabase();
		PreparedStatement ps;
		ResultSet rs;
		int rec = 0;

		try {
			ps = con.prepareStatement("SELECT name FROM gta_agency.applicants WHERE passportNumber = ?)");
			ps.setString(0, passport);
			rs = ps.executeQuery();
			rec = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (rec == 1)
			recommendation = true;

		else
			recommendation = false;

		return recommendation;
	}

	public double getOutstandingCredits() {
		if (outstandingCredits == 0) {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;

			try {
				ps = con.prepareStatement("SELECT outstandingCredits FROM gta_agency.applicants WHERE passportNumber = ?)");
				ps.setString(0, passport);
				rs = ps.executeQuery();
				outstandingCredits = rs.getDouble(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return outstandingCredits;
	}

	public String getDate() {
		if (date == "") {
			Connection con = connectDatabase();
			PreparedStatement ps;
			ResultSet rs;

			try {
				ps = con.prepareStatement("SELECT lastRating FROM gta_agency.applicants WHERE passportNumber = ?)");
				ps.setString(0, passport);
				rs = ps.executeQuery();
				date = rs.getString(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return date;
	}
	/*
	 * Set Methods
	 */

	public void setName(String appName) {
		name = appName;
		Connection con = connectDatabase();
		PreparedStatement ps;

		try {
			ps = con.prepareStatement("UPDATE applicants SET firstname = ? WHERE passportNumber = ?)");
			ps.setString(0, name);
			ps.setString(1, passport);
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
			ps.setString(0, lastname);
			ps.setString(1, passport);
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
			ps.setDouble(0, payrollData);
			ps.setString(1, passport);
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
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD");
		String date = DATE_FORMAT.format(today);

		try {
			ps = con.prepareStatement(
					"UPDATE gta_agency.applicants SET creditRating = ?, lastRating = ? WHERE passportNumber = ?)");
			ps.setDouble(0, creditScore);
			ps.setString(1, date);
			ps.setString(2, passport);
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

			// if Recommended to give credit the recommendation value is set to 1 otherwise
			// it remains 0
			if (appRecommendation)
				recValue = 1;
			else
				recValue = 0;

			ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void setOutstandingCredits(double appOutstandingCredits) {
		outstandingCredits = appOutstandingCredits;
		Connection con = connectDatabase();
		PreparedStatement ps;

		try {
			ps = con.prepareStatement(
					"UPDATE gta_agency.applicants SET outstandingCredits = ? WHERE passportNumber = ?)");
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
