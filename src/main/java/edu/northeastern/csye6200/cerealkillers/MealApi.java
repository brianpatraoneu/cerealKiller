package edu.northeastern.csye6200.cerealkillers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MealApi {
	static String MEALDB_URL = "https://www.themealdb.com/api/json/v2/9973533/filter.php?i=";
	static String SPECIFIC_MEAL_URL = "https://www.themealdb.com/api/json/v2/9973533/lookup.php?i=";
	
	public String getMeals(String query) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(MEALDB_URL+query))
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
		return response.body();
	}
	
	public String getMeal(int id) {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(SPECIFIC_MEAL_URL+Integer.toString(id)))
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
		return response.body();
	}
}
