package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Wait;

/**
 * Page object class for Conflunce Cloud login page.
 *
 * @author Gopal
 */
public class ConfluenceCloudLogin {

	WebDriver driver;
	private final String EMAIL_FIELD_ID = "username";
	private final String PASSWD_FIELD_ID = "password";
	private final String LOGIN_BUTTON_ID = "login-submit";

	public ConfluenceCloudLogin(final WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Login to Confluence Cloud. Enters email, password and clicks on login button
	 * 
	 * @param email  A string containing email id.
	 * @param passwd A string containing password.
	 * @return No return value.
	 */
	public void login(final String email, final String passwd) {
		final Wait wait = new Wait(driver, 20);
		WebElement emailField = wait.waitForElement(By.id(EMAIL_FIELD_ID));
		emailField.click();
		emailField.sendKeys(email);
		WebElement loginButton = driver.findElement(By.id(LOGIN_BUTTON_ID));
		loginButton.click();
		WebElement passwordField = wait.waitForElement(By.id(PASSWD_FIELD_ID));
		passwordField.click();
		passwordField.sendKeys(passwd);
		loginButton.click();
	}

}
