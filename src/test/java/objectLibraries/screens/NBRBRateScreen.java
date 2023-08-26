package objectLibraries.screens;

import objectLibraries.screenAsserts.NBRBRateScreenAsserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class NBRBRateScreen extends BaseScreen{
	
	/**
	 * page elements locators
	 */
	public final By LOC_ICO_UP_ARROW = AppiumBy.accessibilityId("Up");
	public final By LOC_TXT_Title = By.id("com.softteco.bsbank:id/title");
	public final By LOC_ICO_CALC = AppiumBy.accessibilityId("Calculator");
	public final By LOC_BTN_CURRENCY_RATES = By.id("com.softteco.bsbank:id/main_screen_Exchange");

	public String TXT_SCREEN_TITLE = "National bank rates";

	public NBRBRateScreenAsserts screenAsserts;
	/**
	 * Class initializer
	 * @param driver Android driver in our case
	 */
    public NBRBRateScreen(AndroidDriver driver){
        super(driver);
		screenAsserts = new NBRBRateScreenAsserts(this);
    }

	/**
	 * Method to get currency rate from app page
	 * @param currencyPair string to provide a pair of target currency rate in format 'USD/BYN'
	 * @return target currency rate as a string
	 */
    public String getCurrencyRate(String currencyPair) {
    	scrollTillCurrencyVisible(currencyPair);
    	return getElementText(generateCurrencyRateXPath("CHF/BYN"));
    }

	/**
	 * Method to scroll to the target currency element on screen
	 * @param currencyPair string to provide a pair of target currency rate in format 'USD/BYN'
	 * @return target WebElement
	 */
    private WebElement scrollTillCurrencyVisible(String currencyPair) {
    	return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + currencyPair + "\").instance(0))"));
    }
    
    /**
	 * Method to generate XPath locator
	 * @param currencyPair string to provide a pair of target currency rate in format 'USD/BYN'
	 * @return target XPath By locator
	 */
    private By generateCurrencyRateXPath(String currencyPair) {
    	return By.xpath("//android.widget.LinearLayout[./*[contains(@text,'" + currencyPair + "')]]/../..//*[@resource-id='com.softteco.bsbank:id/buy_text']");
    }
}
