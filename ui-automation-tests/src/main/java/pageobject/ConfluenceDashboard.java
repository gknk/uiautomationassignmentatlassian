package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Wait;

/**
 * Page object class for Conflunce Dashboard page.
 *
 * @author Gopal
 */
public class ConfluenceDashboard {

	WebDriver driver;
	private final String CREATE_PAGE_BUTTON_ID = "create-page-button";
	private final String PAGE_TITLE_CLASS_NAME = "update-item-title";
	private final String HOME_PAGE_ICON_XPATH = "//div[contains(@class,'GlobalPrimaryActionsPrimaryItem')]";
	private final String DASHBOARD_PAGE_ID = "com-atlassian-confluence";

	public ConfluenceDashboard(final WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Clicks on the create page button(+ symbol on the left hand side of the page)
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickCreatePageButton() {
		final Wait wait = new Wait(driver, 20);
		wait.waitForElement(By.id(CREATE_PAGE_BUTTON_ID)).click();
	}

	/**
	 * Checks whether the Confluence Dashboard is displayed.
	 * 
	 * @param No parameter.
	 * @return true if found within set time or false.
	 */
	public boolean isConfluenceDashBoardDisplayed() {
		final Wait wait = new Wait(driver, 20);
		return wait.isElementDisplayed(By.id(DASHBOARD_PAGE_ID));
	}

	/**
	 * Waits till the Confluence Dashboard is loaded
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void waitForDashboardToLoad() {
		final Wait wait = new Wait(driver, 20);
		wait.waitForElement(By.id(DASHBOARD_PAGE_ID));
	}

	/**
	 * Selects an existing page based on the title of the page
	 * 
	 * @param pageTitle A string represents the title of the page to be selected.
	 * @return No return value.
	 */
	public void selectExistingPage(String pageTitle) {
		final Wait wait = new Wait(driver, 20);
		List<WebElement> pages = wait.waitForElements(By.className(PAGE_TITLE_CLASS_NAME));
		for (WebElement page : pages) {
			if (page.getText().equals(pageTitle)) {
				page.click();
				break;
			}
		}
	}

	/**
	 * Navigates to the Confluence Dashboardpage which is home or landing page
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void navigateToConfluenceDashboardPage() {
		final Wait wait = new Wait(driver, 20);
		wait.waitForElement(By.xpath(HOME_PAGE_ICON_XPATH)).click();
	}

}
