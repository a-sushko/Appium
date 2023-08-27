package tests;

import org.testng.annotations.Test;

public class CalculatorTests extends BaseTest {

	@Test
	public void checkCalculatorRatesForBynUsdAndInitialState() {
		String responseUSDRate = getCurrencyRateFromResponse("USD");
		String displayedUSDBuyRate = getDisplayedCurrencyBuyRateFromResponse(responseUSDRate);
		String displayedUSDSellRate = getDisplayedCurrencySellRateFromResponse(responseUSDRate);
		

		homeScreen.navigateToCurrencyRates()
				.navigateToNBRBRates()
				.openCalcScreen()
				.screenAsserts.checkInitialBuyCurrency("BYN")
				.screenAsserts.checkInitialSellCurrency("USD")
				.screenAsserts.checkTextInEditTextField("Amount")
				.screenAsserts.checkCurrencySum("0")
				.screenAsserts.checkBuyConversionRate(displayedUSDBuyRate)
				.screenAsserts.checkSellConversionRate(displayedUSDSellRate);
	}
	
	@Test
	public void checkCalculatorWorkResultForRandomAmountOfUSD() {

		String amount = generateRandomAmount(100.1f, 999.9f);
		String expectedBuySum = getExpectedBuySum(amount, "USD");
		
		homeScreen.navigateToCurrencyRates()
				.navigateToNBRBRates()
				.openCalcScreen()
				.provideCurrencyAmount(amount)
				.screenAsserts.checkCurrencySum(expectedBuySum);
	}


}
