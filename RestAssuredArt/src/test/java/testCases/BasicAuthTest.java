package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicAuthTest {
  @Test
  public void basicAuthTestMethod() {
	  
	  //SETTING BASIC AUTH PARAMETERS
	  PreemptiveBasicAuthScheme basicAuth= new PreemptiveBasicAuthScheme();
	  basicAuth.setUserName("username");
	  basicAuth.setPassword("pwd");
	  
	  RestAssured.authentication=basicAuth;
	  
	  
	  RestAssured.baseURI="baseURI";
	  
	  //REQUEST OBJECT
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  //RESPONSE OBJECT
	  Response response = RestAssured.request(Method.GET,"/login"); 
	  
	  int responseCode= response.getStatusCode();
	  Assert.assertEquals(200, responseCode);
	  
  }
}
