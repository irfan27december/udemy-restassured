package googleplacesearch;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import googleplacesearch.PayloadGenerator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.ReadProperties;

public class AddAndDeletePlace {
	ReadProperties properties = new ReadProperties();

	@Test
	public void addAndDeletePlace(){

		//Provide Base URL
		/*String requestBody = "{"+
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
				"}";*/
		String createPlacePayLaod = PayloadGenerator.generatePayLoadString("CreatePlace.json");
		RestAssured.baseURI = properties.getPropertyValue("BASEURI");
		Response response = given().				
				queryParam(properties.getPropertyValue("KEYNAME"), properties.getPropertyValue("KEYVALUE")).
				//body(requestBody).

				body(createPlacePayLaod).
				when().
				post(properties.getPropertyValue("POST_RESOURCE")).
				then().assertThat().statusCode(200).and().contentType(properties.getPropertyValue("CONTENTTYPE_JSON")).and().
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
		//queryParam("key","qaclick123").
		queryParam(properties.getPropertyValue("KEYNAME"), properties.getPropertyValue("KEYVALUE")).
		body("{"+
				"\"place_id\": \""+placeID+"\""+
				"}").
		when().
		post(properties.getPropertyValue("DELETE_RESOURCE")).
		then().assertThat().statusCode(200).and().contentType(properties.getPropertyValue("CONTENTTYPE_JSON")).and().
		body("status",equalTo("OK"));

	}
}
