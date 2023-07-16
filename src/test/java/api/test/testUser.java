package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.userEndPoints;
import api.payload.User;
import io.restassured.response.Response;


public class testUser {

	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		Response r = userEndPoints.createUser(userPayload);
		r.then().log().all();
		
		AssertJUnit.assertEquals(r.getStatusCode(),200);
		
	}
	
	@Test(priority=2)
	public void testGetUser() {
		
		Response r = userEndPoints.getUser(this.userPayload.getUsername());
		r.then().log().all();
		
		AssertJUnit.assertEquals(r.getStatusCode(),200);
		
	}
	
	@Test(priority=3)
	public void testUpdateUser() {
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		
		Response r = userEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		r.then().log()
		//.all();
		.body();
		//.statusCode(200);
		AssertJUnit.assertEquals(r.getStatusCode(),200);
		
		Response rAfter = userEndPoints.getUser(this.userPayload.getUsername());
		rAfter.then().log().all();
		AssertJUnit.assertEquals(rAfter.getStatusCode(),200);
		
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		Response r = userEndPoints.deleteUser(this.userPayload.getUsername());
		r.then().log().all();
		
		AssertJUnit.assertEquals(r.getStatusCode(),200);
		
	}
	
	
	
	
	
}
