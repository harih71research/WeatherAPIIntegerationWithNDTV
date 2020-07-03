package com.blaze.qa.pompages;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.analysis.function.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.Assertion;

import com.blaze.qa.base.TestBase;
import com.blaze.qa.util.CommonUtils;
import com.blaze.qa.util.SystemState;

public class WeatherDynamicPageObject extends TestBase {
	static final String location=SystemState.getLocation();
	@FindBy(id = "searchBox")
	WebElement locationSearchTB;

	@FindBy(xpath ="//span[text()='Pin your City']")
	@CacheLookup
	WebElement locationSearchHeader;
	
	@FindBy(xpath ="//div[@title='Bengaluru']//span//img")
	@CacheLookup
	WebElement bengaluruLocation;
	
	
	@FindBy(xpath ="//div")
	@CacheLookup
	WebElement LocationIcon;
	
	@FindBy(xpath ="//div[@id='messages']//div[@class='message']//label[@for='Bengaluru']")
	@CacheLookup
	WebElement BengaluruInLocationList;
	
	@FindBy(xpath="//div[@id='messages']//div[@class='message']")
	@CacheLookup
	WebElement LocationList;
	
	@FindBy(xpath ="//div[@class='leaflet-popup-content-wrapper']")
	@CacheLookup
	WebElement weatherPop;
	
	@FindBy(xpath ="//span[text()='Bengaluru, Karnataka']")
	@CacheLookup
	WebElement bengaluruLocationWeatherPop;
	
	
	@FindBy(xpath ="//div[@class='leaflet-popup-content-wrapper']")
	@CacheLookup
	WebElement WeatherPopup;

	@FindBys(@FindBy(xpath="//span[@class='heading']"))
	List<WebElement> weatherDatas;
	
	
	@FindBys(@FindBy(xpath="//div[@class='outerContainer']"))
	List<WebElement> PopUpLeaflet;
	// Initializing the Page Objects:
	public WeatherDynamicPageObject() {
		
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
		locationSearchTB.sendKeys(location);
		return isElementDisplayed(BengaluruInLocationList);
	}
	
	public void setLocation() {
		locationSearchTB.sendKeys(location);
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
	
	public boolean isExpectedLocationAvailable() {
		setLocation();
		return LocationList.findElement(By.xpath("//label[@for='"+location+"']")).isDisplayed();
	}
	
	public boolean isExpectedTempLeaflet() {
		boolean flag = false;
		for (WebElement webElement : PopUpLeaflet) {
			if(webElement.findElement(By.xpath("//div[text()='"+location+"']")).isDisplayed() && webElement.findElement(By.xpath("//div[@class='temperatureContainer']")).isDisplayed()) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public boolean isWeatherdetailsAvailable() {
		return weatherPop.findElement(By.xpath("//span[contains(text(),'"+location+"')]")).isDisplayed();
	
	}
	
	public boolean clickOnLocationweatherIcon() {
		WebElement findElement = LocationIcon.findElement(By.xpath("//div[@title='"+location+"']//span//img"));
		return clickOnElement(findElement);
	}
	
}
