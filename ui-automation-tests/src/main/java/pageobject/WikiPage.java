package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Wait;

/**
 * Page object class for created page.
 *
 * @author Gopal
 */
public class WikiPage {

	WebDriver driver;
	private final String PAGE_TITLE_ID = "title-text";
	private final String RESTRICTION_BUTTON_ID = "content-metadata-page-restrictions";

	public WikiPage(final WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Returns the title of the page.
	 * 
	 * @param No parameter.
	 * @return Title of the page.
	 */
	public String getPageTitle() {
		final Wait wait = new Wait(driver, 20);
		String pageTitle;
		WebElement elem = wait.waitForElement(By.id(PAGE_TITLE_ID));
		try {
			pageTitle = elem.getText();
		} catch (StaleElementReferenceException ex) {
			elem = wait.waitForElement(By.id(PAGE_TITLE_ID));
			pageTitle = elem.getText();
		}
		return pageTitle;
	}

	/**
	 * Returns restriction title message.
	 * 
	 * @param No parameter.
	 * @return restriction title message.
	 */
	public String getRestrictionTitltMsg() {
		final Wait wait = new Wait(driver, 20);
		WebElement element = wait.waitForElement(By.id(RESTRICTION_BUTTON_ID));
		new Wait().waitForSpecifiedMilliseconds(2000);
		return element.getAttribute("original-title");
	}

	/**
	 * Clicks on the restriction button(lock symbol on the top of the page)
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickRestrictionButton() {
		final Wait wait = new Wait(driver, 20);
		wait.waitForElement(By.id(RESTRICTION_BUTTON_ID)).click();
	}
}
