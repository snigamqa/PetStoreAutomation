package api.enpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserEndPoints {

	
  public static Response createUser(User payload)
  {
	  
	 Response response=given()
	  	.contentType(ContentType.JSON)
	  	.accept(ContentType.JSON)
	  	.body(payload)
	  
	  .when()
	  	.post(routes.post_url);
	 
	 return response;
	  
  }
  
  public static Response readUser(String userName)
  {
	  
	 Response response=given()
			 				.pathParam("username",userName)
	  
	  .when()
	  	.get(routes.get_url);
	 
	 return response;
	  
  }
  
  
  public static Response updateUser(String userName,User payload)
  {
	  
	 Response response= given()
			  .contentType(ContentType.JSON)
			  .accept(ContentType.JSON)
			  .pathParam("username",userName)
			 			
	  
	  .when()
	  	.put(routes.update_url);
	 
	 return response;
	  
  }
  
  public static Response deleteUser(String userName)
  {
	  
	 Response response=given()
			 				.pathParam("username",userName)
	  
	  .when()
	  	.delete(routes.delete_url);
	 
	 return response;
	  
  }
	
}
