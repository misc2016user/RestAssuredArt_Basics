package testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PostTest {
  @Test
  public void postTestMethod() {
	  
	  //http://dummy.restapiexample.com/	  
	  RestAssured.baseURI="https://reqres.in/api";
	  RequestSpecification httpRequest= RestAssured.given();
	  
	  //Create body
	  JSONObject requestParams= new JSONObject();
	  requestParams.put("name", "newKashif");
	  requestParams.put("job", "Automation SDET");
	
	  //Add params to body && Convert body items to JSON string
	  httpRequest.body(requestParams.toJSONString());
	  
	  //Create Header
	  httpRequest.header("Content-Type", "application/json");
	  
	  Response response= httpRequest.request(Method.POST,"/users");
	  
	  
	  System.out.println("After post , the response is " +response.asString());
	  int statusCode=response.getStatusCode();
	  System.out.println("After post , the status code is " +statusCode);
	  
	  Assert.assertEquals(201, statusCode);
	  	  
  }
}
