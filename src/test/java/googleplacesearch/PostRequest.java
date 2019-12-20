package googleplacesearch;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest {

	@Test()
	public void postCallExample() {
		
		//Provide Base URL
		RestAssured.baseURI = "http://216.10.245.166";
		given().
		queryParam("key", "qaclick123").
		body("{"+
			"\"location\": {"+
				"\"lat\": -38.383494,"+
				"\"lng\": 33.427362"+
			"},"+
			"\"accuracy\": 50,"+
			"\"name\": \"Frontline house\","+
			"\"phone_number\": \"(+91) 9848179563\","+
			"\"address\": \"BDH\","+
			"\"types\": [\"shoe park\"],"+
			"\"website\": \"http: //google.com\","+
			"\"language\": \"French - IN\""+
		"}").			
			when().
			post("/maps/api/place/add/json").
			then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
			body("status",equalTo("OK"));
		
		//Create a place and delete
				

	}

}
