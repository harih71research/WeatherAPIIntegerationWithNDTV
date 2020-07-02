package com.blaze.qa.util;

import java.util.HashMap;

public class SystemState {
	private static HashMap<String,String> weatherDataFromWeb;
	
	public static void setWeatherData(HashMap<String,String> data) {
		weatherDataFromWeb=data;
	}
	public static HashMap<String,String> getWebWeatherData(){
		return weatherDataFromWeb;
	}
	
}
