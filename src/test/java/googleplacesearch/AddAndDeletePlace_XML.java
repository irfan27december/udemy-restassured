package googleplacesearch;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import googleplacesearch.PayloadGenerator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.ReadProperties;
import utilities.ReusableMethods;

public class AddAndDeletePlace_XML {
	ReadProperties properties = new ReadProperties();

	@Test
	public void addAndDeletePlace() throws IOException{

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
		String createPlacePayLaod = PayloadGenerator.generatePayLoadString("CreatePlace.xml");
		RestAssured.baseURI = properties.getPropertyValue("BASEURI");
		Response response = given().				
				queryParam(properties.getPropertyValue("KEYNAME"), properties.getPropertyValue("KEYVALUE")).
				//body(requestBody).

				body(createPlacePayLaod).
				when().
				post(properties.getPropertyValue("XML_POST_RESOURCE")).
				then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
				//body("status",equalTo("OK")).
				extract().response();

		//Capture place id from the response
		//Convert the raw response to xml response
		/*String responseString = response.asString();
		System.out.println("Response string is "+responseString);
		XmlPath xmlPath = new XmlPath(responseString);

		String placeID = xmlPath.get("response.place_id");
		System.out.println("place_id "+placeID);*/

		//Use reusable methods
		XmlPath xmlPath = ReusableMethods.rawToXML(response);
		String placeID = xmlPath.get("response.place_id");
		System.out.println("place_id "+placeID);

		//Pass the place id to delete request
		/*given().
		//queryParam("key","qaclick123").
		queryParam(properties.getPropertyValue("KEYNAME"), properties.getPropertyValue("KEYVALUE")).
		body("{"+
				"\"place_id\": \""+placeID+"\""+
				"}").
		when().
		post(properties.getPropertyValue("XML_DELETE_RESOURCE")).
		then().assertThat().statusCode(200).and().contentType(properties.getPropertyValue("CONTENTTYPE_XML")).and().
		body("status",equalTo("OK"));*/

	}


	public static String GenerateStringFromResource(String path) throws IOException{
		//log.info("Inside PayloadConverter function");
		//String filePath = System.getProperty("user.dir")+"\\resources\\"+filename;
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (Exception e) {
			//log.error(e);
			return null;
		}
	}
}
