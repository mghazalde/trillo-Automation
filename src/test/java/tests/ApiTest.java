package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import apis.BoardPage;
import apis.CardPage;
import apis.ListPage;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiTest {
	private String key;
	private String token;
	private BoardPage boardPage;
	private ListPage listPage;
	private CardPage cardPage;

	@BeforeClass
	public void setUp() {

		// Deleting any created boards before test

		RestAssured.baseURI = "https://api.trello.com/1";
		Response response = RestAssured.given().param("fields", "name,url")
				.param("key", "baf4ed3a72162192e77c6771ba0e9e77")
				.param("token", "ATTAbcd413aa498bac832fa1eb4a27de5395735eb8f3c9b18827244ba1273828932d8522F24A")
				.get("/members/me/boards");
		// Parse the response using JsonPath
		JsonPath jsonPath = response.jsonPath();

		// Retrieve the list of board IDs from the response
		List<String> boardIds = jsonPath.getList("id");

		// Iterate through the board IDs and send DELETE requests for each ID
		for (String boardId : boardIds) {
			Response deleteResponse = RestAssured.given().queryParam("key", "baf4ed3a72162192e77c6771ba0e9e77")
					.queryParam("token", "ATTAbcd413aa498bac832fa1eb4a27de5395735eb8f3c9b18827244ba1273828932d8522F24A")
					.delete("/boards/" + boardId);
		}


		// Instantiate the page objects
		boardPage = new BoardPage();
		listPage = new ListPage();
		cardPage = new CardPage();
	}

	@Test(priority = 1)
	public void validateBoardCreationResponseCode() {
		boardPage.createBoard();
		int statusCode = boardPage.getStatusCode();

		// Assertions
		Assert.assertEquals(statusCode, 200, "Expected status code 200 for card creation, but found " + statusCode);

	}

	@Test(priority = 2)
	public void validateBoardisCreated() {
		// boardPage.createBoard();
		String boardId = boardPage.getBoardId();

		// Assertions
		Assert.assertNotNull(boardId, "Board ID is null");

	}

	@Test(priority = 3)

	public void validateBoardName() {
		// boardPage.createBoard();
		String responseBody = boardPage.getResponseBody();
		String expectedResponseBody = boardPage.getExpectedresBody();
		Assert.assertTrue(responseBody.contains(expectedResponseBody), "Expected boardName in response body");

	}

	@Test(priority = 4)
	public void validateListCreationResponseCode() {
		listPage.CreateList();
		int statusCode = listPage.getStatusCode();

		// Validate the response status code

		Assert.assertEquals(statusCode, 200, "Expected status code 200 for list creation, but found " + statusCode);

	}

	@Test(priority = 5)
	public void validateListisCreated() {
		String listId = listPage.getListId();

		// Assertions
		Assert.assertNotNull(listId, "List ID is null");

	}

	@Test(priority = 6)

	public void validateListName() {
		String responseBody = listPage.getResponseBody();
		String expectedResponseBody = listPage.getExpectedresBody();
		Assert.assertTrue(responseBody.contains(expectedResponseBody), "Expected listName in response body");

	}

	@Test(priority = 7)

	public void validateCardCreationResponseCode() {
		cardPage.CreateCard();
		int statusCode = cardPage.getStatusCode();

		// Validate the response status code

		Assert.assertEquals(statusCode, 200, "Expected status code 200 for card creation, but found " + statusCode);

	}

	@Test(priority = 8)
	public void validateCardisCreated() {
		String cardId = cardPage.getCardId();

		// Assertions
		Assert.assertNotNull(cardId, "Card ID is null");

	}

	@Test(priority = 9)

	public void validateCardtName() {
		String responseBody = cardPage.getResponseBody();
		String expectedResponseBody = cardPage.getExpectedresBody();
		Assert.assertTrue(responseBody.contains(expectedResponseBody), "Expected cardName in response body");

	}

	@AfterClass
	public void tearDown() {
		
	}
}
