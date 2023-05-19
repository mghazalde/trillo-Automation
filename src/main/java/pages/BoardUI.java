package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.shaft.driver.SHAFT;

public class BoardUI {

	SHAFT.GUI.WebDriver driver;
	JavascriptExecutor js;
	SHAFT.TestData.JSON testData;

	public BoardUI(SHAFT.GUI.WebDriver driver) {
		this.driver = driver;
	}

	public static By listLocation() {
		return By.xpath("(//div[@class='list-header-target js-editing-target'])[1]");

	}

	public static By CardLocation() {
		return By.xpath("(//div[@class='js-list list-wrapper list-wrapper-with-margins'])[1]");
	}

	public static By NewCardLocation() {
		
	return By.xpath("(//div[@class='js-list list-wrapper list-wrapper-with-margins'])[2]");
	}
	
	public static By MovedCardLocation() {
		return By.xpath("//div[@class='list-card-details js-card-details']");
	}
	
	public static By TodoListLocation() {
		return By.xpath("(//div)[133]");
	}
	By addNewCard = By.xpath("(//span[@class='js-add-a-card'][normalize-space()='Add a card'])[2]");

	By cardNameField = By.xpath("//textarea[@placeholder='Enter a title for this card…']");
    By addCardButton = By.xpath("//input[@value='Add card']");
    By cancelButton = By.xpath("//a[@class='icon-lg icon-close dark-hover js-cancel']");
    By secondCard = By.xpath("(//div)[139]");
    By firstCard = By.xpath("(//div)[118]");
    By archiveCard = By.xpath("//a[@title='Archive']");
    By delete = By.xpath("//a[@title='Delete']");
    By confirmDelete = By.xpath("//input[@value='Delete']");
    By move = By.xpath("//a[@title='Move']");
    By dropDown = By.xpath("//select[@class='js-select-list']");
    By confirmMove = By.xpath("//input[@value='Move']");
    By closeDialog = By.xpath("//a[@aria-label='Close dialog']");
    By myListLocation = By.xpath("(//span[contains(text(),'Add a card')])[1]");
    
	public BoardUI ClickAddNewCard() {

		driver.element().click(addNewCard);

		return this;
	}

	public BoardUI TypeCardName() {
		testData = new SHAFT.TestData.JSON("testData.json");

		driver.element().type(cardNameField, testData.getTestData("newCardName"));
        
		return this;
	}
	
	public BoardUI ClickAddCardBotton() {
		
		driver.element().click(addCardButton).click(cancelButton);
		
		return this;
	}
	
	public BoardUI ClickSecondCard() {
		driver.element().click(secondCard);
		return this;
	}

	public BoardUI ClickArchive() {
		driver.element().click(archiveCard).click(delete).click(confirmDelete);
		return this;
	}
	
	public BoardUI ClickMoveCard() {
		
		driver.element().click(firstCard).click(move).select(dropDown, "To Do").click(confirmMove).click(closeDialog);
		
		return this;
	}
		
		public BoardUI MoveCardBack() {
			
        driver.element().dragAndDrop(MovedCardLocation(), myListLocation);			
			return this;	
	}
}
