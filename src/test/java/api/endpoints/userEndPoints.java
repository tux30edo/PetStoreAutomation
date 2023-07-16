package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;

public class userEndPoints {
	
	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("Routes");
		return routes;
	}

	public static Response createUser(User payload)
	{
		String post_url = getURL().getString("post_url");
		
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(post_url);
		
		return response;
	}	
	
	public static Response getUser(String userName)
	{
		
		Response response = 
				given()
					.pathParam("username", userName)
				.when()
					.get(Routes.get_url);	
		return response;
	}
	
	public static Response updateUser(String userName, User payload)
	{
		
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
					.pathParam("username", userName)
				.when()
					.put(Routes.update_url);	
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		
		Response response = 
				given()
					.pathParam("username", userName)
				.when()
					.delete(Routes.delete_url);	
		return response;
	}
	
}
