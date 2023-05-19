package apis;
import io.restassured.RestAssured;
import io.restassured.response.Response;

	
public class BoardPage {

	private static String boardId;
    public static String getBoardId() {
        return boardId;
    }
	private String  responseBody;
	
	public String getResponseBody() {
        return responseBody;
    }
    private String expectedResponseBody;
    public String getExpectedresBody() {
        return expectedResponseBody;
    }
   
    private int actualStatusCode;
    public int getStatusCode() {
    	return actualStatusCode;
    }
    
    String url = System.getProperty("baseURI");

    String token = System.getProperty("token");
    String key = System.getProperty("key");
    String requestBodyBoard = System.getProperty("requestBodyBoard");
    public void createBoard()
    
    {
    	
    	//apiData = new SHAFT.TestData.JSON("testData.json");
        RestAssured.baseURI = url;
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParam("key", key)
                .queryParam("token", token)
                .body(requestBodyBoard)
                .post("/boards");

        // Validate the status code
        this.actualStatusCode = response.getStatusCode();

        // Validate the response body
         this.expectedResponseBody = System.getProperty("expectedResponseBoard");
         this.responseBody = response.getBody().asString();

        // Extract the board ID from the response
        this.boardId = response.jsonPath().getString("id");
    }
}