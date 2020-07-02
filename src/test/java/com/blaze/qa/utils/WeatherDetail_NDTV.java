package com.blaze.qa.utils;

import java.util.HashMap;

import com.blaze.api.utils.WeatherApiIntegeration;
import com.blaze.qa.pompages.NdtvHome;
import com.blaze.qa.pompages.WeatherPageObject;
import com.blaze.qa.util.SystemState;

public class WeatherDetail_NDTV {

	public static boolean goToWeatherPage(NdtvHome homePage) {
		boolean flag = true;
		flag = flag && homePage.clickOnDismissed();
		flag = flag && homePage.IsSubmenuDisplayed();
		flag = flag && homePage.clickOnSubMenu();
		flag = flag && homePage.clickOnWeatherIcon();
		return flag;
	}

	public static boolean getWeatherData(WeatherPageObject weatherpage) {
		boolean flag = true;
		HashMap<String,String> listOfWeatherParameter = null;
		flag = flag && weatherpage.IsExactWebPageLanded("Weather");
		flag = flag && weatherpage.isSearchboxDisplay();
		flag = flag && weatherpage.isbengaluruLocationSelected();
		flag = flag && weatherpage.clickOnBengaluruLocation();
		if (weatherpage.isBengaluruWeatherDetailsDisplay()) {
			
			listOfWeatherParameter = weatherpage.listOfWeatherParameter();
			flag = true;
		} else {
			flag = false;
		}
		if (!listOfWeatherParameter.isEmpty()) {
			SystemState.setWeatherData(weatherpage.listOfWeatherParameter());
			flag = true;
		} else
			flag = false;
		return flag;
	}
	

	public static void getWeatherData(String API_KEY, String Location) {
		WeatherApiIntegeration.fetchWeatherData_OpenWeatherAPi(API_KEY, Location);
	}
	
	public static void comparingDatafrom_NDTV_OpenWeather() {
		
	}
	
}
