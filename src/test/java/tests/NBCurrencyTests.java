package tests;

import org.testng.Assert;
//import org.openqa.selenium.By;
//import org.testng.Assert;
import org.testng.annotations.Test;

//import io.appium.java_client.AppiumBy;
import objectLibraries.screens.CurrencyRatesScreen;
import objectLibraries.screens.NBRBRateScreen;

public class NBCurrencyTests extends BaseTest {
	
	
	
	@Test
	public void checkSwissFrankExchangeRate() throws InterruptedException {
		
		String expectedCHFRank = ""/*getRankFromResponse("CHF")*/;

		homeScreen.navigateToCurrencyRates()
				.navigateToNBRBRates()
				.screenAsserts.compareTheSwissFrankRate(expectedCHFRank.isEmpty() ? "3.5888" : expectedCHFRank);



	}

	@Test
	public void restAssuredTest() throws InterruptedException {

				getCurrencyRateFromResponse("CHF");
	}

}
