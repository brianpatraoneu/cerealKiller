package edu.northeastern.csye6200.cerealkillers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	static String URL = "jdbc:mysql://localhost/?useCursorFetch=true";
	static String USER = "root";
	static String PASS = "password";
	
	public ResultSet connectAndExecute(String query,String type) {
		ResultSet rs = null;
		try {
			Connection conn = DriverManager.getConnection(URL,USER,PASS);
			Statement stmt = conn.createStatement();
			if(type == "Select") {
				rs = stmt.executeQuery(query);
			} else {
				int result = stmt.executeUpdate(query);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
}
