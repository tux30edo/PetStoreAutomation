package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DDT {

	@Test(priority=1,dataProvider= "Data",dataProviderClass=Dataproviders.class)
	public void testPostUsers(String userID, String UserName, String FirstName, String LastName, String Email, String Password
			, String PhoneNumber)
	{
		User userPayload = new User();
		//System.out.println (userID+UserName+FirstName+LastName+Email+Password+PhoneNumber);
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(Email);
		userPayload.setPassword(Password);
		userPayload.setPhone(PhoneNumber);
		
		Response r = userEndPoints.createUser(userPayload);
		r.then().log().all();
		
		AssertJUnit.assertEquals(r.getStatusCode(),200);
	}	
	
	@Test(priority=2,dataProvider= "UserNames",dataProviderClass=Dataproviders.class)
	public void testDeleteUsers(String UserName) {
		
		//System.out.println(UserName);
		
		Response r = userEndPoints.deleteUser(UserName);
		r.then().log().all();
		
		AssertJUnit.assertEquals(r.getStatusCode(),200);
	}
	
}
