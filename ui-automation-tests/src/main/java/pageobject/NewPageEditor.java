package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.Helper;
import utility.Wait;

/**
 * Page object class for editor page.
 *
 * @author Gopal
 */
public class NewPageEditor {

	WebDriver driver;
	private final String PAGE_TITLE_ID = "content-title";
	private final String PUBLISH_BUTTON_CLASS_ID = "confirm";
	private final String IFRAME_ID = "wysiwygTextarea_ifr";

	public NewPageEditor(final WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Fills up the page title, content and clicks on the publish button to publish
	 * the page
	 * 
	 * @param pageTitle A string to be entered as the title of the page
	 * @param content   A string to be entered as the content of the page
	 * @return No return value.
	 */
	public void publishPage(String pageTitle, String content) {
		enterPageTitle(pageTitle);
		enterPageContent(content);
		clickPublishButton();
	}

	/**
	 * Enters the title of the new page to be created
	 * 
	 * @param pageTitle A string to be entered as the title of the page
	 * @return No return value.
	 */
	public void enterPageTitle(String pageTitle) {
		final Wait wait = new Wait(driver, 20);
		wait.waitForElement(By.id(PAGE_TITLE_ID)).sendKeys(pageTitle);
	}

	/**
	 * Enters the content of the page. Switches to the iFrame. Executes the
	 * JavaScript to enter the texts. Then switches back to the original window
	 * 
	 * @param content A string to be entered as the content of the page
	 * @return No return value.
	 */
	public void enterPageContent(String content) {
		driver.switchTo().frame(driver.findElement(By.id(IFRAME_ID)));
		String script = String.format("document.getElementsByTagName(\"p\")[0].innerHTML=\"%s\";", content);
		Helper helper = new Helper(driver);
		helper.executeJavaScript(script);
		driver.switchTo().defaultContent();
	}

	/**
	 * Clicks on the publish button
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickPublishButton() {
		driver.findElement(By.name(PUBLISH_BUTTON_CLASS_ID)).click();
	}

}
