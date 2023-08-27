package objectLibraries.screens;

import org.openqa.selenium.By;
import io.appium.java_client.android.AndroidDriver;

public class CurrencyRatesScreen extends BaseScreen {

	/**
	 * initialization of the page locators
	 */
	public final By LOC_TXT_TITLE = By.id("com.softteco.bsbank:id/title");
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
		nbrbRateScreen = new NBRBRateScreen(driver);
		click(LOC_BTN_NBRB_RATES);
		waitForElementContains(nbrbRateScreen.LOC_TXT_TITLE, nbrbRateScreen.TXT_SCREEN_TITLE, 10);
		return nbrbRateScreen;
	}
	
}
