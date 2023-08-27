package objectLibraries.screens;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class BaseScreen {
	protected AndroidDriver driver;
	public WebDriverWait wait;

	/**
	 * Class initializer
	 * @param driver Android driver in our case
	 */
	public BaseScreen(AndroidDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	/**
	 * Method performs a click to the element located by locator
	 * @param locator provide the locator of target element
	 */
	public void click(By locator) {
		WebElement el = driver.findElement(locator);
		Assert.assertTrue(el.isDisplayed(), "Element with locator - " + locator.toString() + " is not exist");
		el.click();
	}
	
	/**
	 * Method performs a long click to the element located by locator
	 * @param locator provide the locator of target element
	 */
	public void longclick(By locator) {
		WebElement el = driver.findElement(locator);
		Assert.assertTrue(el.isDisplayed(), "Element with locator - " + locator.toString() + " is not exist");

		((JavascriptExecutor) driver).executeScript("mobile:longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) el).getId(), "duration", 3000));
	}
	
	/**
	 * Method to extract the text from target element 
	 * @param locator provide the locator of target element
	 * @return an extracted text in type of String
	 */
	public String getElementText (By locator) {
		return driver.findElement(locator).getText();
	}


	/**
	 * Method to extract the text from target element
	 * @param element provide the target Web element
	 * @return an extracted text in type of String
	 */
	public String getElementText (WebElement element) {
		return element.getText();
	}

	/**
	 * Method to Assert is element displayed or not
	 * @param locator provide the locator of target element
	 */
	public void assertDisplayed (By locator) {
		WebElement el = driver.findElement(locator);
		Assert.assertTrue(el.isDisplayed(), "Element with locator - " + locator.toString() + " is not displayed");
	}
	
	/**
	 * Method to explicit wait element displayed or not
	 *
	 * @param locator provide the locator of target element
	 * @param timeout provide timeout in seconds
	 */
	public void waitForDisplayed (By locator, Integer timeout) {
		wait.withTimeout(Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Method to explicit wait screen keyboard is shown
	 * @param timeout provide timeout in seconds
	 */
	public void waitForSystemKeyboardIsShown (Integer timeout) {
		boolean keyboardShown = false;
		for (int i = 0; i < timeout*2; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted with message:" + e.getMessage());
			}
			if(driver.isKeyboardShown()) {
				keyboardShown = true;
				break;
			}
		}

		assertThat(keyboardShown, equalTo(true));
	}

	/**
	 * Method to Assert is element updated or not
	 *
	 * @param locator provide the locator of target element
	 * @param timeout provide timeout in seconds
	 */
	public void waitForElementContains (By locator, String expectedString, Integer timeout) {
		wait.withTimeout(Duration.ofSeconds(timeout)).until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedString));
	}

	/**
	 * Method to Assert is element updated or not
	 * @param locator provide the locator of target input text element
	 * @param text text to input
	 */
	public void inputTextToInputFieldLocated (By locator,String text) {
		WebElement ele = driver.findElement(locator);
		ele.sendKeys(text);
		assertThat(ele.getText(), is(equalTo(text)));
	}


	public WebElement getElementLocated(By locator){
		return driver.findElement(locator);
	}

	public WebElement getElementChildLocated(By parentLocator, By childLocator){
		return getElementLocated(parentLocator).findElement(childLocator);
	}

}
