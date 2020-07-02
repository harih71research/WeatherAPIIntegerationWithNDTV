package com.blaze.qa.testNgFiles;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.blaze.qa.base.TestBase;
import com.blaze.qa.pompages.NdtvHome;
import com.blaze.qa.pompages.WeatherPageObject;
import com.blaze.qa.util.SystemState;
import com.blaze.qa.util.TestUtil;
import com.blaze.qa.utils.WeatherDetail_NDTV;

public class WeatherDatavarification extends TestBase{
	boolean flag = true;
	TestUtil testUtil;
	NdtvHome homePage;
	WeatherPageObject weatherpage;
	public WeatherDatavarification() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		flag = flag && initialization();
		if(flag) {
		testUtil = new TestUtil();
		homePage = new NdtvHome();
		weatherpage = new WeatherPageObject();
		}else {
			flag = false;
		}
	}
	
	@Test(priority=1)
	public void VerifyWeatherDatas(){
		
		flag = flag && WeatherDetail_NDTV.goToWeatherPage(homePage);
		flag = flag	&& WeatherDetail_NDTV.getWeatherData(weatherpage);
		System.out.println(SystemState.getWebWeatherData());
	}
	
}
