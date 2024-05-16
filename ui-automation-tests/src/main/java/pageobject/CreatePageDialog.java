package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Wait;

/**
 * Page object class for create dialog.
 *
 * @author Gopal
 */
public class CreatePageDialog {

	WebDriver driver;
	private final String CREATE_DIALOG_ID = "create-dialog";
	private final String CREATE_BUTTON_CLASS_NAME = "create-dialog-create-button";
	private final String TEMPLATE_NAME_CLASS_NAME = "template-name";
	private final String SPACE_DROP_DOWN_CLASS_NAME = "select2-choice";
	private final String SPACE_OPTIONS_CLASS_NAME = "select2-result-label";

	public CreatePageDialog(final WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Fill up create dialog. Selects space from the drop-down, Selects template and
	 * clicks on create button
	 * 
	 * @param space        A string for space to be selected.
	 * @param templateName A string for template name to be selected.
	 * @return No return value.
	 */
	public void fillUpDialog(final String space, final String templateName) {
		selectSpace(space);
		selectTemplateName(templateName);
		clickCreateButton();
	}

	/**
	 * Checks whether the Create dialog is displayed.
	 * 
	 * @param No parameter.
	 * @return true if found within set time or false.
	 */
	public boolean isDialogDisplayed() {
		final Wait wait = new Wait(driver, 30);
		return wait.isElementDisplayed(By.id(CREATE_DIALOG_ID));
	}

	/**
	 * Selects space from the drop-down. Iterates through all the options available
	 * in the drop-down and selects with the given space
	 * 
	 * @param space A string for space to be selected.
	 * @return No return value.
	 */
	public void selectSpace(final String space) {
		final Wait wait = new Wait(driver, 30);
		driver.findElement(By.className(SPACE_DROP_DOWN_CLASS_NAME)).click();
		List<WebElement> options = wait.waitForElements(By.className(SPACE_OPTIONS_CLASS_NAME));
		for (WebElement option : options) {
			if (option.getText().equals(space)) {
				option.click();
				break;
			}
		}
	}

	/**
	 * Selects template name. Iterates through all the templates and selects the
	 * given template
	 * 
	 * @param templateName A string for template to be selected.
	 * @return No return value.
	 */
	public void selectTemplateName(final String templateName) {
		final Wait wait = new Wait(driver, 20);
		List<WebElement> templates = wait.waitForElements(By.className(TEMPLATE_NAME_CLASS_NAME));
		for (WebElement template : templates) {
			if (template.getText().startsWith(templateName)) {
				template.click();
				break;
			}
		}
	}

	/**
	 * Clicks on the create button
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickCreateButton() {
		driver.findElement(By.className(CREATE_BUTTON_CLASS_NAME)).click();
	}
}
