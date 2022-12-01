package edu.northeastern.csye6200.cerealkillers;

import java.sql.ResultSet;

public class saveController {
	
	public void clickMe() {
		DBConnection db = new DBConnection(); 
	    ResultSet rs = db.connectAndExecute("Insert into cerealkillers.grocery (groceryItem,quantity,expiry) values ('potato',20,'2020-02-02')","Insert");
	}
}
