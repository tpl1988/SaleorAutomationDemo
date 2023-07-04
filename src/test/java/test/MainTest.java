package test;

import org.testng.Assert;
import org.testng.IExecutionListener;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.Constants;
import static io.restassured.RestAssured.*;

public class MainTest extends BaseTest implements IExecutionListener {
	@Test(priority = 1)
	public void loginSuccessfulTest() throws InterruptedException {
		driver.navigate().to(Constants.DASHBOARD_URL);
		driver.manage().window().maximize();
		WebElement emailField = driver.findElement(By.xpath("//input[@name='email']"));
		WebElement passwordField = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement signInButton = driver.findElement(By.xpath("//button[@type='submit']"));
		emailField.sendKeys(Keys.CONTROL + "A");
		emailField.sendKeys(Keys.DELETE);
		emailField.sendKeys(Constants.EMAIL);
		passwordField.sendKeys(Keys.CONTROL + "A");
		passwordField.sendKeys(Keys.DELETE);
		passwordField.sendKeys(Constants.PASSWORD);
//		signInButton.click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-test-id='welcome-header']")).isDisplayed());
	}

	@Test(priority = 2)
	public void authenApiTest() {
		String mutation = "{\"query\":\"mutation {\\n  tokenCreate(email: \\\"admin@example.com\\\", password: \\\"admin\\\") {\\n    token\\n    refreshToken\\n    errors {\\n      field\\n      message\\n    }\\n  }\\n}\",\"operationName\":\"\"}";

		given().log().all().header("Content-Type", "application/json").body(mutation).when().post(Constants.API_URL)
				.then().log().all().assertThat().statusCode(200);
	}
}
