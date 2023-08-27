package objectLibraries.screens;

import io.appium.java_client.android.AndroidDriver;
import objectLibraries.screenAsserts.CalculatorScreenAsserts;
import org.openqa.selenium.By;

public class CalculatorScreen extends BaseScreen {

	/**
	 * initialization of the page locators
	 */
	public final By LOC_TXT_TITLE = By.id("com.softteco.bsbank:id/title");
	public final By LOC_INFO_BUY_CUR_PLATE = By.id("com.softteco.bsbank:id/buy_good_view");
	public final By LOC_INFO_SELL_CUR_PLATE = By.id("com.softteco.bsbank:id/sell_good_view");
	public final By LOC_INFO_CUR_NAME = By.xpath("//*[@resource-id ='com.softteco.bsbank:id/currency_name_text']");
	public final By LOC_EDIT_TXT_CUR_AMOUNT = By.id("com.softteco.bsbank:id/amount_text");
	public final By LOC_TXT_CUR_SUM = By.id("com.softteco.bsbank:id/sum_text");

	public final By LOC_TXT_BUY_CONVERSION = By.id("com.softteco.bsbank:id/buyConversionText");
	public final By LOC_TXT_SELL_CONVERSION = By.id("com.softteco.bsbank:id/sellConversionText");

	/**
	 * Initialization of the visible screen element text
	 */
	public String TXT_SCREEN_TITLE = "Currency converter";


	public CalculatorScreenAsserts screenAsserts;

	/**
	 * Class initializer
	 * @param driver Android driver in our case
	 */
    public CalculatorScreen(AndroidDriver driver){
        super(driver);
		screenAsserts = new CalculatorScreenAsserts(this);
    }

	/**
	 * Method to tap on currency amount field
	 */
	public void clickCurrencyAmountField() {
		click(LOC_EDIT_TXT_CUR_AMOUNT);
		waitForSystemKeyboardIsShown(6);
	}

	/**
	 * Method to send currency amount to the text input field
	 * @return an instance of CalculatorScreen
	 */
	public CalculatorScreen provideCurrencyAmount(String currencyAmount) {
		if(!driver.isKeyboardShown()) clickCurrencyAmountField();
		inputTextToInputFieldLocated(LOC_EDIT_TXT_CUR_AMOUNT, currencyAmount);
		driver.hideKeyboard();
		return new CalculatorScreen(driver);
	}

}
