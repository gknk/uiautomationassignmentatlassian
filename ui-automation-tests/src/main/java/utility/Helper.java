package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Utility class for various helper methods.
 *
 * @author Gopal
 */
public class Helper {

	private WebDriver driver;

	public Helper(final WebDriver driver) {
		this.driver = driver;
	}
	
	public Helper() {
		
	}

	/**
	 * Executes JavaScript using WebDriver instance
	 * 
	 * @param script A string which represents the JavaScript to be executed.
	 * @return No return value.
	 */
	public void executeJavaScript(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}
}
