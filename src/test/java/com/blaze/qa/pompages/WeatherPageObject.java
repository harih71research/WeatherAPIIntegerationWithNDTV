package com.blaze.qa.pompages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.blaze.qa.base.TestBase;
import com.blaze.qa.util.CommonUtils;

public class WeatherPageObject extends TestBase {
	
	@FindBy(id = "searchBox")
	WebElement locationSearchTB;

	@FindBy(xpath ="//span[text()='Pin your City']")
	@CacheLookup
	WebElement locationSearchHeader;
	
	@FindBy(xpath ="//div[@title='Bengaluru']//span//img")
	@CacheLookup
	WebElement bengaluruLocation;
	
	
	@FindBy(xpath ="//div[@id='messages']//div[@class='message']//label[@for='Bengaluru']")
	@CacheLookup
	WebElement BengaluruInLocationList;
	
	@FindBy(xpath ="//div[@class='leaflet-popup-content-wrapper']")
	@CacheLookup
	WebElement weatherPop;
	
	@FindBy(xpath ="//span[text()='Bengaluru, Karnataka']")
	@CacheLookup
	WebElement bengaluruLocationWeatherPop;
	

	@FindBys(@FindBy(xpath="//span[@class='heading']"))
	List<WebElement> weatherDatas;
	

	// Initializing the Page Objects:
	public WeatherPageObject() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean isSearchboxDisplay() {
		if(locationSearchHeader.isDisplayed()) {
			if(locationSearchTB.isDisplayed())
				return true;
			else
				return false;
		}else return false;
	}
	
	public boolean isbengaluruLocationSelected() {
		locationSearchTB.sendKeys("Bengaluru");
		return isElementDisplayed(BengaluruInLocationList);
	}
	
	public boolean clickOnBengaluruLocation() {
		return clickOnElement(bengaluruLocation);
	}
	
	public boolean isWeatherPopUp() {
		return isElementDisplayed(weatherPop);
	}
	
	public boolean isBengaluruWeatherDetailsDisplay() {
		return isElementDisplayed(bengaluruLocationWeatherPop);
	}
	
	public HashMap<String, String>  listOfWeatherParameter() {
		HashMap<String, String> data= new HashMap<String, String>();
		for (WebElement weatherdata : weatherDatas) {
			HashMap<String, String> arrayToMap = CommonUtils.ArrayToMap(weatherdata.getText());
			data.putAll(arrayToMap);
		}
		return data;
	}
	
	
	
	
}
