package utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public static XmlPath rawToXML(Response response){
		String responseString = response.asString();
		System.out.println("Response string is "+responseString);
		XmlPath xmlPath = new XmlPath(responseString);

		/*String placeID = xmlPath.get("response.place_id");
		System.out.println("place_id "+placeID);*/
		return xmlPath;
	}
	
	public static JsonPath rawToJSON(Response response){
		String responseString = response.asString();
		System.out.println("Response string is "+responseString);
		JsonPath jsonPath = new JsonPath(responseString);

		/*String placeID = jsonPath.get("response.place_id");
		System.out.println("place_id "+placeID);*/
		return jsonPath;
	}
}
