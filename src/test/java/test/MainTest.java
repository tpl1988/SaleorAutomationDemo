package test;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.IExecutionListener;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import utils.Constants;

public class MainTest extends BaseTest implements IExecutionListener {

	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException {
		setupThread();
	}

	@Test(priority = 1, groups = {"UI"})
	public void loginSuccessfulTest() throws InterruptedException {
		WebDriver driver = getDriver();
		driver.navigate().to(Constants.DASHBOARD_URL);
		driver.manage().window().maximize();
		Thread.sleep(3000);

		try {
			WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
			WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
			WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
			emailField.sendKeys(Keys.CONTROL + "A");
			emailField.sendKeys(Keys.DELETE);
			emailField.sendKeys(Constants.EMAIL);
			passwordField.sendKeys(Keys.CONTROL + "A");
			passwordField.sendKeys(Keys.DELETE);
			passwordField.clear();
			passwordField.sendKeys(Constants.PASSWORD);
			signInButton.click();
			Thread.sleep(3000);
			Assert.assertTrue(driver.findElement(By.xpath("//div[@data-test-id='welcome-header']")).isDisplayed());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 2, groups = {"API"})
	public void authenApiTest() {
		String mutation = "{\"query\":\"mutation {\\n  tokenCreate(email: \\\"admin@example.com\\\", password: \\\"admin\\\") {\\n    token\\n    refreshToken\\n    errors {\\n      field\\n      message\\n    }\\n  }\\n}\",\"operationName\":\"\"}";

		given().log().all().header("Content-Type", "application/json").body(mutation).when().post(Constants.API_URL)
				.then().log().all().assertThat().statusCode(200);
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			tearDownDriver();
		}
	}
}
