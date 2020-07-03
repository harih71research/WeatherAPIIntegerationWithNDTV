package com.blaze.qa.testNgFiles;

import static org.testng.Assert.assertEquals;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.blaze.qa.base.TestBase;
import com.blaze.qa.pompages.NdtvHome;
import com.blaze.qa.pompages.WeatherDynamicPageObject;
import com.blaze.qa.util.TestUtil;
import com.blaze.qa.utils.WeatherDetail_NDTV;

public class WeatherDatavarification extends TestBase{
	boolean flag = true;
	TestUtil testUtil;
	NdtvHome homePage;
	WeatherDynamicPageObject weatherpage;
	public WeatherDatavarification() {
		super();
	}

	@BeforeSuite
	public void setUp() {
		Reporter.log("Browser initialization");
		flag = flag && initialization();
		Reporter.log("Browser sucessfully launched",flag);
		if(flag) {
		testUtil = new TestUtil();
		homePage = new NdtvHome();
		weatherpage = new WeatherDynamicPageObject();
		}else {
			flag = false;
		}
	}
	
	@Test(priority=1)
	public void GotoWeatherPage(){
		Reporter.log("Preparing for weather Page launching");
		flag = flag && WeatherDetail_NDTV.goToWeatherPage(homePage);
		flag = flag && weatherpage.IsExactWebPageLanded("Weather");
		Reporter.log("waether page verified", flag);
	}
	
	@Test(priority=2)
	public void VerifySelectedCityinSearchBox(){
		// first verify location in search box
		boolean expectedLocationAvailable = weatherpage.isExpectedLocationAvailable();
		assertEquals(expectedLocationAvailable, true);
		Reporter.log("Expected location available in search box" ,expectedLocationAvailable);
	}
	
	@Test(priority=3)
	public void VerifySelectCityWithTemp(){
		// verify temp avialable for given location
		boolean expectedTempLeaflet = weatherpage.isExpectedTempLeaflet();
		assertEquals(expectedTempLeaflet, true);
		Reporter.log("Expected location temperature available in Popup" ,expectedTempLeaflet);
	}
	
	
	@Test(priority=5)
	public void verifyPopupLocationAvailabel(){
		//verify location weahter details availabel

	 boolean clickOnLocationweatherIcon = weatherpage.clickOnLocationweatherIcon();
		assertEquals(clickOnLocationweatherIcon, true);
	}
	@Test(priority=6)
	public void getWeahterDetailsinWeb() {
		// get weather datas in web
		boolean weatherDataAfterAssert = WeatherDetail_NDTV.getWeatherDataAfterAssert(weatherpage);
		assertEquals(weatherDataAfterAssert, true);
	}
	
	@Test(priority=7)
	public void getWeahterDetailsinAPI() {
		//get api data from openweahter
		WeatherDetail_NDTV.getWeatherData();
	}
	
	@Test(priority=8)
	public void VerifyDatasAreMatch() {
		// verify data with Web and APi
		boolean comparingDatafrom_NDTV_OpenWeather = WeatherDetail_NDTV.AssertWithVarianceData(2, 15, 2);
		assertEquals(comparingDatafrom_NDTV_OpenWeather, true);
	}
	
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
