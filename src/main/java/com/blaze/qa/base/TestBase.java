package com.blaze.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.blaze.qa.util.TestUtil;
import com.blaze.qa.util.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/Properties/ProjectSpecific.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean initialization() {
		String browserName = prop.getProperty("browser");
		if (browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browserName.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
//		driver .get("https://www.ndtv.com");
		if (driver != null)
			return true;
		else
			return false;
	}

	public boolean IsDriverEngaged() {
		if (driver != null)
			return true;
		else
			return false;
	}

	public boolean clickOnElement(WebElement ele) {
		if (IsDriverEngaged()) {
			if (ele.isDisplayed()) {
				ele.click();
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean IsExactWebPageLanded(String TitleName) {

		String currentTitle = driver.getTitle();
		if (currentTitle.contains(TitleName))
			return true;
		else
			return false;
	}

	public boolean setvalueOnTextBox(WebElement ele, String SearchContent) {
		boolean flag = true;

		ele.sendKeys(SearchContent);
		String text = ele.getText();
		System.out.println(text);

		return flag;
	}

	public boolean isElementDisplayed(WebElement ele) {
		return ele.isDisplayed();
	}
	
	
}
