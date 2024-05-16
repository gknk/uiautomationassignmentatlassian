package pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Wait;

/**
 * Page object class for restriction dialog.
 *
 * @author Gopal
 */
public class RestrictionDialog {

	WebDriver driver;
	private final String RESTRICTION_DROP_DOWN_CLASS_NAME = "select2-container";
	private final String APPLY_BUTTON_ID = "page-restrictions-dialog-save-button";
	private final String DIALOG_OPTION_CLASS_NAME = "restrictions-dialog-option";
	private final String OPTION_TITLE_CLASS_NAME = "title";

	public RestrictionDialog(final WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Fill up restriction dialog. Selects restriction from the drop-down and clicks
	 * on Apply button
	 * 
	 * @param restrictionValrestrictionValue A string represents the value for the
	 *                                       option to be selected.
	 * @return No return value.
	 */
	public void applyRestriction(String restriction) {
		selectRestriction(restriction);
		clickApplyButton();
	}

	/**
	 * Selects restriction from the drop-down. Iterates through all the options
	 * available in the drop-down and selects with the given restriction
	 * 
	 * @param restriction A string for restriction to be selected.
	 * @return No return value.
	 */
	public void selectRestriction(String restriction) {
		Wait wait = new Wait(driver, 20);
		wait.waitForElement(By.className(RESTRICTION_DROP_DOWN_CLASS_NAME)).click();
		List<WebElement> dialogOptions = wait.waitForElements(By.className(DIALOG_OPTION_CLASS_NAME));
		for (WebElement dialogOption : dialogOptions) {
			WebElement optionTitle = dialogOption.findElement(By.className(OPTION_TITLE_CLASS_NAME));
			if (optionTitle.getText().equals(restriction)) {
				optionTitle.click();
				break;
			}
		}
	}

	/**
	 * Clicks on the Apply button
	 * 
	 * @param No parameter.
	 * @return No return value.
	 */
	public void clickApplyButton() {
		Wait wait = new Wait(driver, 20);
		wait.waitForElement(By.id(APPLY_BUTTON_ID)).click();
	}

	/**
	 * Checks whether the restriction dialog is displayed.
	 * 
	 * @param No parameter.
	 * @return true if found within set time or false.
	 */
	public boolean isDialogDisplayed() {
		final Wait wait = new Wait(driver, 30);
		return wait.isElementDisplayed(By.className(RESTRICTION_DROP_DOWN_CLASS_NAME));
	}

}
