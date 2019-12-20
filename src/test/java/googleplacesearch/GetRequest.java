package googleplacesearch;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest {
	
	/*public static void main(String args[]){
		
	}*/

	@Test()
	public void getCallExample() {
		
		//Provide Base URL
		RestAssured.baseURI = "https://maps.googleapis.com";
		given().
				/*header("","").
				cookie("","").
				body("").*/
				param("location","-33.8670522,151.1957362").
				param("radius","1500").
				param("key","AIzaSyA03bO2x2fYHYfXEhiwD_9gx54QXsfVe-I").
				when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				//body("results[0].geometry.location.lat",equalTo("-33.8585416"));
				body("results[0].name",equalTo("Sydney")).and().
				body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
				header("Server", "scaffolding on HTTPServer2");
				

	}

}
