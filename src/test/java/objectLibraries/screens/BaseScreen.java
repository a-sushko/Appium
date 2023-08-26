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
	 * Method to Assert is element displayed or not
	 * @param locator provide the locator of target element
	 */
	public void assertDisplayed (By locator) {
		WebElement el = driver.findElement(locator);
		Assert.assertTrue(el.isDisplayed(), "Element with locator - " + locator.toString() + " is not displayed");
	}
	
	/**
	 * Method to explicit wait element displayed or not
	 * @param locator provide the locator of target element
	 * @param timeout provide timeout in seconds
	 */
	public WebElement waitForDisplayed (By locator, Integer timeout) {
		return wait.withTimeout(Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * Method to Assert is element displayed or not
	 * @param locator provide the locator of target element
	 * @param timeout provide timeout in seconds
	 */
	public boolean waitForElementUpdate (By locator, Integer timeout) {
		WebElement ele = driver.findElement(locator);
		return wait.withTimeout(Duration.ofSeconds(timeout)).until(ExpectedConditions.stalenessOf(ele));
	}
	
	
		
//	public void click(AppiumBy locator) {
//		WebElement el = driver.findElement(locator);
//		Assert.assertTrue(el.isDisplayed(), "Element with locator - " + locator.toString() + " is not exist");
//		el.click();
//	}
}
