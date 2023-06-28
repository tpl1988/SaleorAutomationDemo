package test;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Constants;

public class BaseTest {
	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	public static String remoteChromeUrl = Constants.REMOTE_CHROME_URL;

	public void setupThread() throws MalformedURLException {
		System.out.println("Inside Chrome");
		ChromeOptions options = new ChromeOptions();
		driver.set(new RemoteWebDriver(new URL(remoteChromeUrl), options));
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void tearDownDriver() {
		getDriver().quit();
	}
}