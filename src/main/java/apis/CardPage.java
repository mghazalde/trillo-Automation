package apis;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CardPage {
	private BoardPage boardPage;
	private ListPage listPage;

	String url = System.getProperty("baseURI");

	String token = System.getProperty("token");
	String key = System.getProperty("key");
	String requestBodyCard = System.getProperty("requestBodyCard");

	private int actualStatusCode;

	public int getStatusCode() {
		return actualStatusCode;
	}

	private String responseBody;

	public String getResponseBody() {
		return responseBody;
	}

	private String expectedResponseBody;

	public String getExpectedresBody() {
		return expectedResponseBody;

	}

	private static String cardId;

	public static String getCardId() {
		return cardId;
	}

	public void CreateCard() {
		ListPage listPage = new ListPage();
		String listId = listPage.getListId();
		RestAssured.baseURI = url;

		Response response = RestAssured.given().queryParam("idList", listId).queryParam("key", key)
				.queryParam("token", token).contentType(ContentType.JSON).body(requestBodyCard).post("/cards");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200, "Expected status code 200, but found " + statusCode);

		String cardId = response.jsonPath().getString("id");
		Assert.assertNotNull(cardId, "Card ID is null");

		System.out.println("Created Card ID: " + cardId);

		this.expectedResponseBody = System.getProperty("expectedResponseCard");
		this.responseBody = response.getBody().asString();
		this.actualStatusCode = response.getStatusCode();

		// Extract the list ID from the response
		this.cardId = response.jsonPath().getString("id");
	}
}
