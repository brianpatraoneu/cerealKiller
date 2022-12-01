package edu.northeastern.csye6200.cerealkillers;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class Meal {
	int mealId;
	String mealName;
	String mealImg;
	
	Meal(int id, String name, String img) {
		this.mealId = id;
		this.mealName = name;
		this.mealImg = img;
	}
	
	public int getMealID() {
		return this.mealId;
	}
	
	public void setMealID(int id) {
		this.mealId = id;
	}
	
	public String getMealName() {
		return this.mealName;
	}
	
	public void setMealID(String name) {
		this.mealName = name;
	}
	
	public String getMealImg() {
		return this.mealImg;
	}
	
	public void setMealImg(String img) {
		this.mealImg = img;
	}
	
	public String toString() {
		return "The meal name is "+this.mealName+" with id "+this.mealId;
	}
}

public class App 
{
	static String MEALDB_URL = "https://www.themealdb.com/api/json/v2/9973533/filter.php?i=";
			
    public static void main( String[] args )
    {
    	StringBuilder queryString = new StringBuilder();
    	try {
    		DBConnection db = new DBConnection(); 
            ResultSet rs = db.connectAndExecute("Select * from cerealkillers.grocery;","Select");
            while (rs.next()) {
            	queryString.append(rs.getString("groceryItem")+",");
             }
            queryString.setLength(queryString.length() - 1);
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
    	MealApi api = new MealApi();
    	String response = api.getMeals(queryString.toString());
		final JSONObject obj = new JSONObject(response);
	    final JSONArray meals = obj.getJSONArray("meals");
	    Meal[] mealArr = new Meal[meals.length()];
	    JSONObject[] arr = new JSONObject[meals.length()];
	    for(int i=0;i<meals.length();i++) {
	    	arr[i] = (JSONObject) meals.get(i);
	    	mealArr[i] = new Meal(arr[i].getInt("idMeal"),arr[i].getString("strMeal"),arr[i].getString("strMealThumb"));
	    	System.out.println(mealArr[i].toString());
	    	System.out.println(api.getMeal(mealArr[i].getMealID()));
	    }
    }
}
