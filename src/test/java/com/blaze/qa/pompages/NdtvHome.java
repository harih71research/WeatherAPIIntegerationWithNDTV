package com.blaze.qa.pompages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blaze.qa.base.TestBase;

public class NdtvHome extends TestBase {

	@FindBy(id = "h_sub_menu")
	WebElement subMenuDrawer;

	@FindBy(xpath =  "//a[text()='WEATHER']")
	@CacheLookup
	WebElement weatherLinkIcon;
	
	@FindBy(xpath = "//a[@class='notnow']")
	@CacheLookup
	WebElement popupDismissed;
	// Initializing the Page Objects:
	public NdtvHome() {
		PageFactory.initElements(driver, this);
	}

	
	public boolean IsSubmenuDisplayed() {
		return subMenuDrawer.isDisplayed();
	}

	public boolean isWeatherIconDisplayed() {
		return weatherLinkIcon.isDisplayed();
	}

	public boolean clickOnSubMenu() {
		return clickOnElement(subMenuDrawer);
	}
	
	public boolean clickOnWeatherIcon() {
		return clickOnElement(weatherLinkIcon);
	}

	public boolean clickOnDismissed() {
		return clickOnElement(popupDismissed);
	}
	public boolean isdismissedPresent() {
		return popupDismissed.isDisplayed();
	}
}
