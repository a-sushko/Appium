package objectLibraries.screens;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CurrencyRatesScreen extends BaseScreen {

	/**
	 * initialization of the page locators
	 */
	public final By LOC_ICO_UP_ARROW = AppiumBy.accessibilityId("Up");
	public final By LOC_TXT_Title = By.id("com.softteco.bsbank:id/title");
	public final By LOC_BTN_NBRB_RATES = By.id("com.softteco.bsbank:id/currencies_screen_NBRB");

	/**
	 * Initialization of the visible screen element text
	 */
	public String TXT_SCREEN_TITLE = "Currency rates";

	/**
	 * Class initializer
	 * @param driver Android driver in our case
	 */
	public CurrencyRatesScreen(AndroidDriver driver) {
		super(driver);
	}
	NBRBRateScreen nbrbRateScreen;

	/**
	 * Method to navigate to the NBRB rates screen
	 * @return an instance of the NBRBRateScreen
	 */
	public NBRBRateScreen navigateToNBRBRates() {
		click(LOC_BTN_NBRB_RATES);
		waitForDisplayed(By.id("com.softteco.bsbank:id/title"), 10);
		nbrbRateScreen = new NBRBRateScreen(driver);
		Assert.assertEquals(getElementText(nbrbRateScreen.LOC_TXT_Title), nbrbRateScreen.TXT_SCREEN_TITLE);
		return nbrbRateScreen;
	}
	
}
