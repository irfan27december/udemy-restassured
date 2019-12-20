package googleplacesearch;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AddAndDeletePlace {

	@Test
	public void addAndDeletePlace(){

		//Provide Base URL
		String requestBody = "{"+
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
				"}";
		RestAssured.baseURI = "http://216.10.245.166";
		Response response = given().
				queryParam("key", "qaclick123").
				body(requestBody).			
				when().
				post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("status",equalTo("OK")).
				extract().response();
		String responseString = response.asString();
		System.out.println("Response string is "+responseString);
		//Capture place id from the response
		//Convert the raw response to JSON response
		JsonPath js = new JsonPath(responseString);
		String placeID = js.get("place_id");
		System.out.println("place_id "+placeID);

		//Pass the place id to delete request
		given().
		queryParam("key","qaclick123").
		body("{"+
				"\"place_id\": \""+placeID+"\""+
				"}").
		when().
		post("/maps/api/place/delete/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));

	}
}
