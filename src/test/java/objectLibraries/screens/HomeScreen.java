package objectLibraries.screens;

import objectLibraries.screenAsserts.HomeScreenAsserts;
import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HomeScreen extends BaseScreen {

	/**
	 * initialization of the page locators
	 */
	public final By LOC_IMG_BANK_LOGO = AppiumBy.accessibilityId("Bank logo");
	public final By LOC_ICO_SETTINGS_GEAR = AppiumBy.accessibilityId("Settings");
	public final By LOC_ICO_ABOUT = AppiumBy.accessibilityId("About the program");
	public final By LOC_BTN_CURRENCY_RATES = By.id("com.softteco.bsbank:id/main_screen_Exchange");


	public HomeScreenAsserts screenAsserts;

	/**
	 * Class initializer
	 * @param driver Android driver in our case
	 */
    public HomeScreen(AndroidDriver driver){
        super(driver);
		screenAsserts = new HomeScreenAsserts(this);
    }
	
	public CurrencyRatesScreen navigateToCurrencyRates() {
		click(LOC_BTN_CURRENCY_RATES);
		waitForDisplayed(By.id("com.softteco.bsbank:id/title"), 10);
		CurrencyRatesScreen currencyRatesScreen = new CurrencyRatesScreen(driver);
		assertThat(getElementText(currencyRatesScreen.LOC_TXT_TITLE), is(equalToIgnoringCase(currencyRatesScreen.TXT_SCREEN_TITLE)));
		return currencyRatesScreen;
	}
	
}
