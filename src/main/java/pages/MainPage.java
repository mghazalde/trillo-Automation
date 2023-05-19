package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import com.shaft.driver.SHAFT;

public class MainPage {

	SHAFT.GUI.WebDriver driver;
	JavascriptExecutor js;

	public MainPage(SHAFT.GUI.WebDriver driver) {
		this.driver = driver;
		driver.getDriver().manage().deleteAllCookies();
	}

	String url = System.getProperty("url");
	String userName = System.getProperty("username");
	String password = System.getProperty("password");
	String url1 = System.getProperty("url1");
	String atlasianTitle = System.getProperty("atlasianTitle");

	By trelloLoginBotton = By.xpath("//a[@class='Buttonsstyles__Button-sc-1jwidxo-0 kTwZBr']");

	By userNameField = By.xpath("//input[@id='user']");
	By loginButton = By.xpath("//input[@id='login']");
	By passwordField = By.xpath("//input[@id='password']");
	By loginSubmitButton = By.xpath("//button[@id='login-submit']");

	public static By boardLocation() {
		return By.xpath("//div[@class='board-tile-details is-badged']");

	}

	public void navigate() {
		driver.browser().maximizeWindow();
		driver.browser().navigateToURL(url);
	}

	public MainPage loginPhase1() throws Exception {

		driver.element().click(trelloLoginBotton);

		return this;
	}

	public MainPage loginPhase2() throws Exception {

		// testData = new SHAFT.TestData.JSON("testData.json");
		driver.element().type(userNameField, userName);
		driver.element().click(loginButton);
		Thread.sleep(1000);

		return this;
	}

	public MainPage atlassianLogin() throws Exception {

		// testData = new SHAFT.TestData.JSON("testData.json");
		driver.element().type(passwordField, password).keyPress(loginSubmitButton, Keys.ENTER);
		Thread.sleep(1000);

		return this;
	}

	public MainPage invalidUserLogin() throws Exception {

		// login - negative scenario
		driver.browser().refreshCurrentPage();
		// driver.element().type(userName, generateRandomValues());
		Thread.sleep(1000);
		// driver.element().type(password, test_ValidPwd);
		driver.element().click(loginButton);
		return this;
	}

	public MainPage clickBoard() throws InterruptedException  {

	driver.element().click(boardLocation());
	Thread.sleep(500);
		
//		js = (JavascriptExecutor) driver.getDriver();
//		WebElement element = driver.getDriver().findElement(By.xpath("//div[@class='board-tile-details is-badged']"));
//        js.executeScript("arguments[0].click();", element);
        
		return this;
	}
}
