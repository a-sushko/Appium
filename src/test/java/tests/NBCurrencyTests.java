package tests;

import org.testng.annotations.Test;

public class NBCurrencyTests extends BaseTest {

	@Test
	public void checkSwissFrankExchangeRate() {
		
		String expectedCHFRank = getCurrencyRateFromResponse("CHF");

		homeScreen.navigateToCurrencyRates()
				.navigateToNBRBRates()
				.screenAsserts.validateTheSwissFrankRate(expectedCHFRank);

	}

}
