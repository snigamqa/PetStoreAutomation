package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.enpoints.UserEndPoints;
import api.payloads.User;
import api.utilies.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	
	@Test(priority=1, dataProvider ="Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String UserID, String UserName, String FirstName, String LastName, String email, String Password, String PH)
	{
		User userPayload=  new User();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setUsername(UserName);
		userPayload.setEmail(email);
		userPayload.setPassword(Password);
		userPayload.setPhone(PH);
	
		Response response= UserEndPoints.createUser(userPayload);
		
		Assert.assertEquals(response.getStatusCode(), 200);
	
	
	}
	
	@Test(priority= 2, dataProvider ="UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteByName(String UserName)
	
	{
		
		Response response= UserEndPoints.deleteUser(UserName);
				
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
		
		
	}

}
