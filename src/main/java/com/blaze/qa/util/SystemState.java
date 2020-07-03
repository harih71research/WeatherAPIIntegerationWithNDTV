package com.blaze.qa.util;

import java.util.HashMap;

public class SystemState {
	private static HashMap<String,String> weatherDataFromWeb;
	private static HashMap<String,Object> weatherDataFromAPI;
	private static String APIKEY;
	private static String Location;
	private static String Assert;

	public static void setWeatherData(HashMap<String,String> data) {
		weatherDataFromWeb=data;
	}
	public static HashMap<String,String> getWebWeatherData(){
		return weatherDataFromWeb;
	}
	
	public static void setWeatherDataFromAPI(HashMap<String,Object> data) {
		weatherDataFromAPI=data;
	}
	public static HashMap<String,Object> getAPIWeatherData(){
		return weatherDataFromAPI;
	}
	
	public static void setAPIKEY (String KEY) {
		APIKEY=KEY;
	}
	public static String getAPIKEY(){
		return APIKEY;
	}
	
	public static void setLocation (String Loc) {
		Location=Loc;
	}
	public static String getLocation(){
		return Location;
	}
	
	
	public static void setAsset(String Asset) {
		Assert=Asset;
	}
	public static String getAsset(){
		return Assert;
	}
}
