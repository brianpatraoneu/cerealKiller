package edu.northeastern.csye6200.cerealkillers;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.themealdb.com/api/json/v2/9973533/filter.php?i=chicken_breast,garlic"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(response.body());
		final JSONObject obj = new JSONObject(response.body());
	    final JSONArray meals = obj.getJSONArray("meals");
	    Meal[] mealArr = new Meal[meals.length()];
	    JSONObject[] arr = new JSONObject[meals.length()];
	    for(int i=0;i<meals.length();i++) {
	    	arr[i] = (JSONObject) meals.get(i);
	    	mealArr[i] = new Meal(arr[i].getInt("idMeal"),arr[i].getString("strMeal"),arr[i].getString("strMealThumb"));
//	    	System.out.println(mealArr[i].toString());
	    }
    }
}
