package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.driver.SHAFT;

import pages.BoardUI;
import pages.MainPage;

public class UITest {

	SHAFT.GUI.WebDriver driver;
	SHAFT.TestData.JSON testData;
	private MainPage main;
	private BoardUI boardUI;

	@BeforeClass
	public void beforeClass() {
		driver = new SHAFT.GUI.WebDriver();
		testData = new SHAFT.TestData.JSON("testData.json");
		// Instantiate the page objects
		main = new MainPage(driver);
		boardUI = new BoardUI(driver);
		main.navigate();

	}

	@Test(priority = 1)
	public void validateLoginPhase1() throws Exception {
		main.loginPhase1();
		driver.assertThat().browser().url().equals(testData.getTestData("url1"));

	}

	@Test(priority = 2)
	public void validateLoginPhase2() throws Exception {

		main.loginPhase2();
		driver.assertThat().browser().title().contains(testData.getTestData("atlasianTitle")).perform();
	}

	@Test(priority = 3)
	public void validateLoginPhase3() throws Exception {
		main.atlassianLogin();
		driver.assertThat().browser().url().contains(testData.getTestData("boards")).perform();

	}

	@Test(priority = 4)
	public void validateBoardIsDisplayed() throws Exception {

		driver.assertThat().element(main.boardLocation()).isVisible().perform();

	}

	@Test(priority = 5)
	public void validateBoardName() {

		driver.assertThat().element(main.boardLocation()).text().contains(testData.getTestData("expectedResponseBoard"))
				.perform();

	}

	@Test(priority = 6)
	public void validateBoardIsload() throws InterruptedException {
		main.clickBoard();

		driver.assertThat().browser().title().contains(testData.getTestData("expectedResponseBoard")).perform();

	}

	@Test(priority = 7)
	public void validateListIsDisplayed() {
		driver.assertThat().element(boardUI.listLocation()).isVisible().perform();
	}

	@Test(priority = 8)
	public void validateListName() {
		driver.assertThat().element(boardUI.CardLocation()).text()
				.contains(testData.getTestData("expectedResponseList")).perform();
	}

	@Test(priority = 9)
	public void validateCardIsDisplayed() {
		driver.assertThat().element(boardUI.CardLocation()).isVisible().perform();
	}

	@Test(priority = 10)
	public void validateCardName() {
		driver.assertThat().element(boardUI.CardLocation()).text()
				.contains(testData.getTestData("expectedResponseCard")).perform();
	}

	@Test(priority = 11)
	public void validateAddNewCard() {
		boardUI.ClickAddNewCard().TypeCardName().ClickAddCardBotton();
		driver.assertThat().element(boardUI.NewCardLocation()).text().contains(testData.getTestData("newCardName"))
				.perform();
	}

	@Test(priority = 12)
	public void validatArchiveCard() {
		boardUI.ClickSecondCard().ClickArchive();
		driver.assertThat().element(boardUI.NewCardLocation()).text()
				.doesNotContain(testData.getTestData("newCardName")).perform();
	}

	@Test(priority = 13)
	public void validatCardMovedfromList() {
		boardUI.ClickMoveCard();
		driver.assertThat().element(boardUI.CardLocation()).text()
				.doesNotContain(testData.getTestData("expectedResponseCard")).perform();
	}

	@Test(priority = 14)
	public void validatCardMovedToList() {
		driver.assertThat().element(boardUI.MovedCardLocation()).text()
				.contains(testData.getTestData("expectedResponseCard")).perform();

	}

	@Test(priority = 15)
	public void validatCardMovedBackToMyListByDragAndDrop() {
		boardUI.MoveCardBack();
		driver.assertThat().element(boardUI.MovedCardLocation()).text()
				.contains(testData.getTestData("expectedResponseCard")).perform();
	}

	@Test(priority = 16)
	public void validatCardMovedBackFromToDoList() {
		driver.assertThat().element(boardUI.TodoListLocation()).text()
				.doesNotContain(testData.getTestData("expectedResponseCard")).perform();
	}

	@AfterClass(alwaysRun = false)
	public void afterClass() {
		driver.browser().closeCurrentWindow();

	}

}
