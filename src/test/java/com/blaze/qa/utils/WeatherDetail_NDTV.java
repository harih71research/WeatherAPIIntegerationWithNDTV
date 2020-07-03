package com.blaze.qa.utils;

import java.util.HashMap;

import org.testng.Assert;

import com.blaze.api.utils.WeatherApiIntegeration;
import com.blaze.qa.pompages.NdtvHome;
import com.blaze.qa.pompages.WeatherDynamicPageObject;
import com.blaze.qa.util.CommonUtils;
import com.blaze.qa.util.SystemState;

public class WeatherDetail_NDTV {

	public static boolean goToWeatherPage(NdtvHome homePage) {
		boolean flag = true;
		try {
			Thread.sleep(3000);
			// instead of thread we should use explicit wait, for time inconvience we used
			// thread.sleep
			flag = flag && homePage.clickOnDismissed();
			flag = flag && homePage.IsSubmenuDisplayed();
			flag = flag && homePage.clickOnSubMenu();
			flag = flag && homePage.clickOnWeatherIcon();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean getWeatherData(WeatherDynamicPageObject weatherpage) {
		boolean flag = true;
		HashMap<String, String> listOfWeatherParameter = null;
		flag = flag && weatherpage.IsExactWebPageLanded("Weather");
		flag = flag && weatherpage.isSearchboxDisplay();
		flag = flag && weatherpage.isbengaluruLocationSelected();
		try {
			Thread.sleep(5000);
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean getWeatherDataAfterAssert(WeatherDynamicPageObject weatherpage) {
		boolean flag = true;
		HashMap<String, String> listOfWeatherParameter = null;
		Assert.assertEquals(weatherpage.isBengaluruWeatherDetailsDisplay(), true);
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

	public static void getWeatherData() {
		WeatherApiIntegeration.fetchWeatherData_OpenWeatherAPi(SystemState.getAPIKEY(), SystemState.getLocation());
	}

	public static boolean comparingDatafrom_NDTV_OpenWeatherVariance(double Tempmax_mini, double humMaxMin,
			double windMaxMin) {
		boolean flag = false;
		HashMap<String, Object> apiWeatherData = SystemState.getAPIWeatherData();
		HashMap<String, String> webWeatherData = SystemState.getWebWeatherData();
		double webTemp = Double.parseDouble(webWeatherData.get("Degrees"));
		double webHum = Double.parseDouble(webWeatherData.get("Humidity"));
		double webWind = Double.parseDouble(webWeatherData.get("Wind"));
		double apiTemp = Double.parseDouble(apiWeatherData.get("Temperature").toString());
		double apiHum = Double.parseDouble(apiWeatherData.get("Humidity").toString());
		double apiWind = Double.parseDouble(apiWeatherData.get("Wind Speed").toString());
		double tempVar = CommonUtils.findvariancefromGivenData(webTemp, apiTemp);
		double humVar = CommonUtils.findvariancefromGivenData(webHum, apiHum);
		double windVar = CommonUtils.findvariancefromGivenData(webWind, apiWind);
		if (tempVar <= Tempmax_mini || tempVar >= Tempmax_mini) {
			System.out.println();
			flag = true;
		} else
			flag = false;

		if (humVar <= humMaxMin || humVar >= humMaxMin) {
			flag = true;

		} else
			flag = false;
		if (windVar <= windMaxMin || windVar >= windMaxMin) {
			flag = true;

		} else
			flag = false;
		return flag;

	}

	public static boolean comparingDatafrom_NDTV_OpenWeatherDifference(double Tempmax_mini, double humMaxMin,
			double windMaxMin) {
		boolean flag = false;
		HashMap<String, Object> apiWeatherData = SystemState.getAPIWeatherData();
		HashMap<String, String> webWeatherData = SystemState.getWebWeatherData();
		double Temp = Math.abs(Double.parseDouble(webWeatherData.get("Degrees"))
				- Double.parseDouble(apiWeatherData.get("Temperature").toString()));
		double Hum = Math.abs(Double.parseDouble(webWeatherData.get("Humidity"))
				- Double.parseDouble(apiWeatherData.get("Humidity").toString()));
		double Wind = Math.abs(Double.parseDouble(webWeatherData.get("Wind"))
				- Double.parseDouble(apiWeatherData.get("Wind Speed").toString()));
		if (Temp <= Tempmax_mini || Temp >= Tempmax_mini) {
			flag = true;
			Assert.assertEquals(flag, true);
		} else {
			flag = false;
			Assert.assertEquals(flag, true);

		}
		if (Hum <= humMaxMin || Hum >= humMaxMin) {
			flag = true;
			Assert.assertEquals(flag, true);

		} else {
			flag = false;
			Assert.assertEquals(flag, true);
		}
		if (Wind <= windMaxMin || Wind >= windMaxMin) {
			flag = true;
			Assert.assertEquals(flag, true);

		} else {
			flag = false;
		Assert.assertEquals(flag, true);
		}

		return flag;

	}

	public static boolean AssertWithVarianceData(double Tempmax_mini, double humMaxMin,
			double windMaxMin) {
		if(SystemState.getAsset().equals("Variance")) {
			return comparingDatafrom_NDTV_OpenWeatherVariance(Tempmax_mini, humMaxMin, windMaxMin);
		}else if(SystemState.getAsset().equals("Difference")) {
			return comparingDatafrom_NDTV_OpenWeatherDifference(Tempmax_mini, humMaxMin, windMaxMin);
		}else {
			return false;
		}
	
	}
	
}
