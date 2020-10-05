package testCases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetTest {
	
  @Test
  public void getMethodTest() {
	  
	  RestAssured.baseURI="http://dummy.restapiexample.com/api/v1/employee";
	  
	  //REQUEST OBJECT
	  RequestSpecification httpRequest= RestAssured.given();
	  
	 //RESPONSE OBJECT
	  Response response =httpRequest.request(Method.GET, "/1");
	  
	  String responseBody= response.getBody().asString();
	  int statusCode= response.getStatusCode();
	  String statusLine= response.getStatusLine();
	  
	  System.out.println("The response body is: "+responseBody);
	  System.out.println("The Status code is: "+statusCode);
	  System.out.println("The statusLine is: "+statusLine);
	  
  }
}
