package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;

import objectLibraries.screens.CurrencyRatesScreen;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import objectLibraries.screens.HomeScreen;

import static io.restassured.RestAssured.given;


public class BaseTest {
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	protected HomeScreen homeScreen;
	protected CurrencyRatesScreen currencyRatesScreen;

	@BeforeClass
	public void configureAppium() throws MalformedURLException {
		
		// run appium server automatically
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:/Users/Admin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
		service.start();


        // generate capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel XL API 33");
		options.setApp(System.getProperty("user.dir") + "/src/test/java/resources/BSB_Bank_base.apk");
		options.setPlatformName("Android");
//		options.setCapability("appium:appPackage", "io.appium.android.apis");
//		options.setCapability("appium:appActivity", "io.appium.android.apis.ApiDemos");
//		options.setCapability("appium:useNewWDA", false);
//		options.setCapability("appium:noReset", true);
//		options.setCapability("appium:fullReset", false);
		  
		// create object for Android driver / iOSDriver
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		
//		put driver to the screen objects
		homeScreen = new HomeScreen(driver);
		currencyRatesScreen = new CurrencyRatesScreen(driver);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

	public String getCookie(){
		RestAssured.baseURI = "https://mobile.bsb.by/api/v1/free-zone-management/exchange-rates/rates";
        return given().log().all()
				.header("User-Agent", "PostmanRuntime/7.32.3")
				.when()
				.get("")
				.then()
				.statusCode(500)
				.extract()
				.cookie("session-cookie");
	}


	public JsonObject getResponseBody(){
		RestAssured.baseURI = "https://mobile.bsb.by/api/v1/free-zone-management/exchange-rates/rates";
//		Evaluate params for request
		long ms = Instant.now().toEpochMilli();
		String cookie = getCookie();
		JsonObject bodyJson = new JsonObject();
		bodyJson.addProperty("type", "NATIONAL_BANK_BELARUS");
		bodyJson.addProperty("period", ms);
//		Post request
		Response response = given().log().all()
				.header("Content-Type", "application/json")
				.header("User-Agent", "MyTestSuperAgent/0.0.7")
				.header("Cookies", "session-cookie="+cookie)
				.body(bodyJson)
			.when()
				.post("")
			.then().log().all()
				.statusCode(200)
				.extract()
				.response();

		return JsonParser.parseString(response.getBody().asString()).getAsJsonObject();

	}

	public String getCurrencyRateFromResponse(String targetCurrencyName) {
		String targetCurrencyRate = "";
		JsonArray ratesArray = getResponseBody().getAsJsonArray("rates");
		System.out.println("!!!!!!!!!!!!!!DEBUG!!!!!!!!!!!!!");
		for (JsonElement cur : ratesArray){
			JsonObject currency = cur.getAsJsonObject();
			System.out.println(currency);
			if (currency.get("buyCurrencyName").getAsString().equalsIgnoreCase(targetCurrencyName)) {
				targetCurrencyRate = currency.get("buyAmount").getAsString();
				break;
			}
		}
		System.out.println(targetCurrencyRate);
		return targetCurrencyRate.isEmpty() ? "0" : targetCurrencyRate;
	}

	/**
	 * Method to get expected displayed currency rate from it string value received from response
	 * @param rawCurrencyRate - string value of rate
	 * @return new rounded value (###.##)
	 */
	public String getDisplayedCurrencySellRateFromResponse(String rawCurrencyRate) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(strToFloat(rawCurrencyRate)).replace(",",".");
	}

	/**
	 * Method to get expected displayed currency rate from it string value received from response
	 * @param rawCurrencyRate - string value of rate
	 * @return new rounded value (###.##)
	 */
	public String getDisplayedCurrencyBuyRateFromResponse(String rawCurrencyRate) {
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(1/strToFloat(rawCurrencyRate)).replace(",",".");
	}

	/**
	 * Method to convert string value to float value
	 * @param value - target string
	 * @return float value of provided string
	 */
	public Float strToFloat(String value) {
		return Float.parseFloat(value);
	}

	/**
	 * Method to generate random amount of currency to change
	 * @param minValue - the lowest value of amount
	 * @param maxValue  - the highest value of amount
	 * @return float value of provided string
	 */
	public String generateRandomAmount(Float minValue, Float maxValue) {
		Random r = new Random();
		DecimalFormat df = new DecimalFormat("#0.00");
		String amount = df.format(minValue + r.nextFloat() * (maxValue - minValue)).replace(",",".");
		System.out.println("Currency amount: " + amount);
		return amount;
	}

	/**
	 * Method to count the sum of 2'nd currency
	 * @param amount - the amount of '1'st currency
	 * @param currencyName  - the currency name (USD, RUB etc.)
	 * @return float value of provided string
	 */
	public String getExpectedBuySum(String amount, String currencyName) {
		Float currencyBuyRate = 1/strToFloat(getCurrencyRateFromResponse(currencyName));
		Float currencyAmount = strToFloat(amount);
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(currencyBuyRate*currencyAmount).replace(",",".");
	}
	
	
}
