package objectLibraries.screenAsserts;

import objectLibraries.screens.CalculatorScreen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CalculatorScreenAsserts {

    CalculatorScreen calculatorScreen;

    /**
     * Class initializer
     * @param calculatorScreen an instance of the CalculatorScreen class
     */
    public CalculatorScreenAsserts(CalculatorScreen calculatorScreen) {
        this.calculatorScreen = calculatorScreen;
    }

    /**
     * Method to check that expected currency has installed as buy currency by default
     * @param expectedCurrencyName the expected currency name
     * @return an instance of the calculator page page
     */
    public CalculatorScreen checkInitialBuyCurrency(String expectedCurrencyName) {
        assertThat(calculatorScreen.getElementChildLocated(
                calculatorScreen.LOC_INFO_BUY_CUR_PLATE,
                calculatorScreen.LOC_INFO_CUR_NAME).getText(),
                is(equalToIgnoringCase(expectedCurrencyName)));
        return calculatorScreen;
    }

    /**
     * Method to check that expected currency has installed as sell currency by default
     * @param expectedCurrencyName the expected currency name
     * @return an instance of the calculator page
     */
    public CalculatorScreen checkInitialSellCurrency(String expectedCurrencyName) {
        assertThat(calculatorScreen.getElementText(
                calculatorScreen.getElementChildLocated(calculatorScreen.LOC_INFO_SELL_CUR_PLATE, calculatorScreen.LOC_INFO_CUR_NAME)),
                is(equalToIgnoringCase(expectedCurrencyName)));
        return calculatorScreen;
    }

    /**
     * Method to check that edit text field contains expected text/sum
     * @param expectedText the expected text located within the edit text field
     * @return an instance of the calculator page
     */
    public CalculatorScreen checkTextInEditTextField(String expectedText) {
        assertThat(calculatorScreen.getElementText(calculatorScreen.LOC_EDIT_TXT_CUR_AMOUNT),
                is(equalToIgnoringCase(expectedText)));
        return calculatorScreen;
    }

    /**
     * Method to check that expected currency sum is displayed
     * @param expectedSum the expected sum
     * @return an instance of the calculator page
     */
    public CalculatorScreen checkCurrencySum(String expectedSum) {
        assertThat(calculatorScreen.getElementText(calculatorScreen.LOC_TXT_CUR_SUM),
                is(containsString(expectedSum)));
        return calculatorScreen;
    }

    /**
     * Method to check that expected buy currency rate is displayed
     * @param expectedRate the expected buy rate
     * @return an instance of the calculator page
     */
    public CalculatorScreen checkBuyConversionRate(String expectedRate) {
        assertThat(calculatorScreen.getElementText(calculatorScreen.LOC_TXT_BUY_CONVERSION),
                is(containsString("1 BYN = " + expectedRate)));
        return calculatorScreen;
    }

    /**
     * Method to check that expected buy currency rate is displayed
     * @param expectedRate the expected buy rate
     * @return an instance of the calculator page
     */
    public CalculatorScreen checkSellConversionRate(String expectedRate) {
        assertThat(calculatorScreen.getElementText(calculatorScreen.LOC_TXT_SELL_CONVERSION),
                is(containsString(" = " + expectedRate + " BYN")));
        return calculatorScreen;
    }


}
