package utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utility class for various timeouts.
 *
 * @author Gopal
 */
public class Wait {

	private WebDriver driver;
	/**
	 * TIMEOUT - Maximum time in seconds where driver will wait for ceratin
	 * condition to occur
	 */
	private int TIMEOUT;
	/**
	 * DEFAULT_INTERVAL - default sleep interval during webdriver timeouts
	 */
	private final long DEFAULT_INTERVAL = 300;

	public Wait(final WebDriver driver, final int timeOutInSeconds) {
		this.driver = driver;
		TIMEOUT = timeOutInSeconds;
	}

	public Wait() {

	}

	/**
	 * Waits till the element appears in the html. Makes use of the
	 * NoSuchElementException
	 * 
	 * @param elementLocator locator for the element to search for.
	 * @return WebElement specified by the locator.
	 */
	public WebElement waitForElement(final By elementLocator) {
		final long end = System.currentTimeMillis() + TIMEOUT * 1000;
		WebElement element = null;
		while (System.currentTimeMillis() < end) {
			try {
				element = driver.findElement(elementLocator);
				if (element.isDisplayed()) {
					break;
				}
			} catch (final NoSuchElementException e) {
				sleep();
			} catch (final StaleElementReferenceException s) {
				break;
			}
		}
		return element;
	}

	/**
	 * Waits till the element appears in the html. Makes use of the
	 * NoSuchElementException
	 * 
	 * @param elementLocator locator for the element to search for.
	 * @return true if found within set time or false.
	 */
	public boolean isElementDisplayed(final By elementLocator) {
		final long end = System.currentTimeMillis() + TIMEOUT * 1000;
		WebElement element = null;
		while (System.currentTimeMillis() < end) {
			try {
				element = driver.findElement(elementLocator);
				if (element.isDisplayed()) {
					return true;
				}
			} catch (final NoSuchElementException e) {
				sleep();
			} catch (final StaleElementReferenceException s) {
				break;
			}
		}
		return false;
	}

	/**
	 * Waits till the elements appears in the html. Makes use of the
	 * NoSuchElementException
	 * 
	 * @param elementLocator locator for the elements to search for.
	 * @return List of WebElements if found within set time or empty.
	 */
	public List<WebElement> waitForElements(final By elementLocator) {
		final long end = System.currentTimeMillis() + TIMEOUT * 1000;
		List<WebElement> elements = new ArrayList<WebElement>();
		while (System.currentTimeMillis() < end) {
			try {
				elements = driver.findElements(elementLocator);
				if (elements.size() > 0) {
					break;
				}
			} catch (final NoSuchElementException e) {
				sleep();
			}
		}
		return elements;
	}

	/**
	 * Sleeps for the default interval.
	 */
	private void sleep() {
		try {
			Thread.sleep(DEFAULT_INTERVAL);
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Explicitly waits for the specified time.
	 * 
	 * @param milliSeconds A long represents the time to wait in milliseconds.
	 * @return No return value.
	 */
	public void waitForSpecifiedMilliseconds(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
