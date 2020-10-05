package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ValidateResponseBody {
  @Test
  public void validateResponseBodyTest() {
	  RestAssured.baseURI="https://reqres.in/api/user";
	  
	  RequestSpecification httpRequest= RestAssured.given();
	  Response response =httpRequest.request(Method.GET,"/2");
	  
	  //Print complete response body
	  String responseBody=response.getBody().asString();
	  System.out.println("The body is "+responseBody);
	  
	  //Verify if there is a node present
	 // Assert.assertTrue(responseBody.contains("year"));
	  
	  //Extract node values- use JsonPath
	  JsonPath jsonpath = response.jsonPath();
	  System.out.print("The value of year in response is "+jsonpath.get("data.year")); //jsonpath.get("year") will give NULL
	
	  
  }
}
