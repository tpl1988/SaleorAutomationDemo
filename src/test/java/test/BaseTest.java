package test;

//import java.net.MalformedURLException;
//import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
//	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
//	public static String remoteChromeUrl = Constants.REMOTE_CHROME_URL;
//
//	public void setupThread() throws MalformedURLException {
//		System.out.println("Inside Chrome");
//		ChromeOptions options = new ChromeOptions();
//		driver.set(new RemoteWebDriver(new URL(remoteChromeUrl), options));
//	}
//
//	public WebDriver getDriver() {
//		return driver.get();
//	}
//
//	public void tearDownDriver() {
//		getDriver().quit();
//	}

	protected WebDriver driver;

	@BeforeMethod
	public void setupDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(options);
	}

	@AfterMethod
	public void tearDownDriver() {
		// Quit the driver and close all associated windows
		driver.quit();
	}
}
