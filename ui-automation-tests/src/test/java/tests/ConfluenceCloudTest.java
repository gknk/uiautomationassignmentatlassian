package tests;

import java.util.Properties;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageobject.ConfluenceCloudLogin;
import pageobject.ConfluenceDashboard;
import pageobject.CreatePageDialog;
import pageobject.NewPageEditor;
import pageobject.RestrictionDialog;
import pageobject.WikiPage;
import uicommon.UITestConfiguration;

/**
 * Test class for Conflunce Cloud Application.
 *
 * @author Gopal
 */
public class ConfluenceCloudTest extends UITestConfiguration {

	private WebDriver driver;
	private Properties property;
	private String baseUrl;
	private String email;
	private String passwd;
	private String space;
	private String templateName;
	private String content;
	private String restriction;
	private String pageTitleFromPropertyFile;

	/**
	 * Below are the steps performed as part of BeforeClass annotation execution. 
	 * 1) Reading the property file 
	 * 2) Creating the driver instance based on the browser mentioned in property file 
	 * 3) Open the base URL 
	 * 4) Logs-in to the application
	 */
	@BeforeClass
	public void beforeClass() {
		UITestConfiguration uiTestConfiguration = new UITestConfiguration();
		property = uiTestConfiguration.readPropertyFile();
		driver = uiTestConfiguration.createDriver();
		baseUrl = property.getProperty("baseUrl");
		email = property.getProperty("email");
		passwd = property.getProperty("passwd");
		space = property.getProperty("space");
		templateName = property.getProperty("templateName");
		content = property.getProperty("content");
		restriction = property.getProperty("restriction");
		pageTitleFromPropertyFile = property.getProperty("pageTitle");

		driver.get(baseUrl);
		ConfluenceCloudLogin confluenceCloudLogin = new ConfluenceCloudLogin(driver);
		confluenceCloudLogin.login(email, passwd);
		ConfluenceDashboard confluenceDashboard = new ConfluenceDashboard(driver);
		confluenceDashboard.waitForDashboardToLoad();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	/**
	 * Below are the steps performed as part of BeforeMethod execution. 
	 * 1) Navigates to the Confluence Dashboard page which is our Home/Landing page
	 * 2) Wait till the page loaded
	 */
	@BeforeMethod
	public void beforeMethod() {
		ConfluenceDashboard confluenceDashboard = new ConfluenceDashboard(driver);
		confluenceDashboard.navigateToConfluenceDashboardPage();
		confluenceDashboard.waitForDashboardToLoad();
	}

	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Clicks on the create page button(+ symbol) available on left hand side of the dashboard page 
	 * 2) Verify that the create dialog is displayed 
	 * 3) Fill-up create dialog by selecting space and template name and clicking on Create button 
	 * 4) Verify new page Editor is displayed 
	 * 5) Enter Page Title, content and publish the page 
	 * 6) Verify that the page is created by asserting the page title
	 */
	@Test
	public void createNewPage() {
		ConfluenceDashboard confluenceDashboard = new ConfluenceDashboard(driver);
		confluenceDashboard.clickCreatePageButton();

		CreatePageDialog createPageDialog = new CreatePageDialog(driver);
		Assert.assertTrue(createPageDialog.isDialogDisplayed());
		createPageDialog.fillUpDialog(space, templateName);

		NewPageEditor newPageEditor = new NewPageEditor(driver);
		String pageTitle = space + UUID.randomUUID().toString();
		newPageEditor.publishPage(pageTitle, content);

		WikiPage wikiPage = new WikiPage(driver);
		Assert.assertTrue(wikiPage.getPageTitle().equals(pageTitle));
	}

	/**
	 * Below are the steps performed as part of this test case execution. 
	 * 1) Selects(By clicking the title of the page) the page for which the restriction is to be applied
	 * 2) Verify that the restriction dialog is displayed 
	 * 3) Check the restriction title message which should be null
	 * 4) Fill-up restriction dialog by selecting restriction and clicking on Apply button 
	 * 5) Verify that the restriction is applied
	 */
	@Test()
	public void setRestrictions() {
		ConfluenceDashboard confluenceDashboard = new ConfluenceDashboard(driver);
		confluenceDashboard.selectExistingPage(pageTitleFromPropertyFile);

		WikiPage wikiPage = new WikiPage(driver);
		Assert.assertNull(wikiPage.getRestrictionTitltMsg());
		wikiPage.clickRestrictionButton();

		RestrictionDialog restrictionDialog = new RestrictionDialog(driver);
		Assert.assertTrue(restrictionDialog.isDialogDisplayed());
		restrictionDialog.applyRestriction(restriction);
		if (restriction.equals("No restrictions")) {
			Assert.assertTrue(wikiPage.getRestrictionTitltMsg().equals("Unrestricted"));
		} else {
			Assert.assertTrue(wikiPage.getRestrictionTitltMsg().equals("Restrictions apply"));
		}
	}
}