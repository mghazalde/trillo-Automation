package apis;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ListPage {

	private BoardPage boardPage;

	String url = System.getProperty("baseURI");

	String token = System.getProperty("token");
	String key = System.getProperty("key");
	String requestBodyList = System.getProperty("requestBodyList");

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

	private static String listId;

	public static String getListId() {
		return listId;
	}

	public void CreateList() {
		BoardPage boardPage = new BoardPage();
		String boardId = boardPage.getBoardId();

		// Set the base URL of the Trello API
		RestAssured.baseURI = url;

		// Specify the request body
		String listUrl = "/boards/" + boardId + "/lists/";
		// Send the POST request to create a new list
		Response response = RestAssured.given().queryParam("key", key).queryParam("token", token)
				.contentType(ContentType.JSON).body(requestBodyList).post(listUrl);

		// Validate the response status code
		this.expectedResponseBody = System.getProperty("expectedResponseList");
		this.responseBody = response.getBody().asString();
		this.actualStatusCode = response.getStatusCode();

		// Extract the list ID from the response
		this.listId = response.jsonPath().getString("id");

	}
}
