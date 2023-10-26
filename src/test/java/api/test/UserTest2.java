package api.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.enpoints.UserEndPoints;
import api.enpoints.UserEndPoints2;
import api.payloads.User;
import groovyjarjarantlr4.v4.runtime.misc.LogManager;
import io.restassured.response.Response;

public class UserTest2 {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	private void setup() {
		faker= new Faker();
		userPayload= new User();
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		logger= org.apache.logging.log4j.LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*********Creating User***************************");
		Response response= UserEndPoints2.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("*********User Created***************************");
	}
	
	
	
	@Test(priority=2)
	public void testGetUserName()
	{
		logger.info("*********Reading User***************************");

		Response response= UserEndPoints2.readUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********User  is displayed***************************");

	}
	

	
	@Test(priority = 3)
	public  void testUpdateUserByName() {
		
		logger.info("*********Updating User***************************");

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		Response response= UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 405);
		
		logger.info("*********User is updated***************************");

		
	}
	
	public void testDeleteUserByName()
	{
		logger.info("*********Deleting User***************************");

		Response response= UserEndPoints2.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*********User deleted***************************");


	}
	
	

}
