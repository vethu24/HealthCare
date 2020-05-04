package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class payment {
	
	public static Connection getConnection(){
		   Connection connection=null;
		   System.out.println("Connection called");
		  try {
		    Class.forName("com.mysql.jdbc.Driver");
		    connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/helthcare","root", "");
		  } catch (ClassNotFoundException e) {
		    e.printStackTrace();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		    return connection;
		  }
	
	public String readPayment(){
		
		String output = "";
		try{
			
			Connection con = getConnection();
			if (con == null){
					return "Error while connecting to the database for reading."; 
				}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Card HolderName</th>"
					+ "<th>Card Type</th><th>Card Number</th>"
					+ "<th>CVV</th><th>Expire Month</th><th>Expire Year</th>"
					+ "<th>Total</th><th>Pay Date</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()){
				String cardpayID =  rs.getString("cardpayID");
				String holderName = rs.getString("holderName");
				String ctype = rs.getString("ctype");
				String cardNo = rs.getString("cardNo");
				String cvv= rs.getString("cvv");
				String expMonth = rs.getString("expMonth");
				String expYear = rs.getString("expYear");
				String total = rs.getString("total");
				String payDate = rs.getString("payDate");
				// Add into the html table
				
				
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + cardpayID + "'>" + holderName + "</td>";
				
			
				output += "<td>" + ctype + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + expMonth + "</td>";
				output += "<td>" + expYear + "</td>";
				output += "<td>" + total + "</td>";
				output += "<td>" + payDate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-cardpayid='"+cardpayID+"'></td></tr>";
				
				//output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\" btnUpdate btn btn-secondary\"></td> <td><form method=\"post\" action=\"payment.jsp\"> "
				//		+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\"> <input name=\"hidItemIDDelete\" type=\"hidden\" value=\"" + cardpayID + "\">" + "</form></td></tr>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}catch (Exception e){
			output = "Error while view the payment Details.";
			System.err.println(e.getMessage());
		}
		
	return output;
}

	
	public String addCard(String holderName,String ctype,String cardNo, String cvv, String expMonth,String expYear,String total )
	{
		String output = "";
		try{
			Connection con = getConnection();
			
			if (con == null){
					return "Error while connecting to the database for inserting."; 
				}
	
			// create a prepared statement
			String query = " insert into payment (`holderName`,`ctype`,`cardNo`,`cvv`,`expMonth`,`expYear`,`total`)" + " values (?,?,?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, holderName);
			preparedStmt.setString(2, ctype);
			preparedStmt.setString(3, cardNo);
			preparedStmt.setString(4, cvv);
			preparedStmt.setString(5, expMonth);
			preparedStmt.setString(6, expYear);
			preparedStmt.setString(7, total);
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			
			
		}catch (Exception e){
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}";
					System.err.println(e.getMessage());
			}
		return output;
	}
	
	public String updatePayment(String cardpayID,String holderName,String ctype,String cardNo, String cvv, String expMonth,String expYear,String total){
		
		String output = "";
		try{
			Connection con = getConnection();
			if (con == null){
				return "Error while connecting to the database for updating.";
			}
		// create a prepared statement
		String query = "UPDATE payment SET holderName=?, ctype=?, cardNo=?, cvv=?, expMonth=?, expYear=?,total=? WHERE cardpayID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		
		preparedStmt.setString(1, holderName);
		preparedStmt.setString(2, ctype);
		preparedStmt.setString(3, cardNo);
		preparedStmt.setString(4, cvv);
		preparedStmt.setString(5, expMonth);
		preparedStmt.setString(6, expYear);
		preparedStmt.setString(7, total);
		
		preparedStmt.setInt(8, Integer.parseInt(cardpayID));
		// execute the statement
		preparedStmt.execute();
		con.close();
		String newItems = readPayment();
		output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	}catch (Exception e){
		output = "{\"status\":\"error\", \"data\":\"Error while updating the payment.\"}";
				System.err.println(e.getMessage());

	
	}
		return output;

	}

	public String deletePayment(String cardpayID) {
		String output = "";
		try {
				Connection con = getConnection();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
			// create a prepared statement
			String query = "delete from payment where cardpayID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cardpayID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readPayment();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}
}
