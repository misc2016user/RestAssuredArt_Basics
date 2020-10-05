package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidateHeaders {
  @Test
  public void validateHeadersMehod() {
	  
	  RestAssured.baseURI="https://reqres.in/";
	  RequestSpecification httpRequest=RestAssured.given();
	  
	  Response response= httpRequest.request(Method.GET,"/api/users/2");
	  
	  //One header validation
	  String connection= response.getHeader("Connection");
	  Assert.assertEquals("keep-alive", connection);
	  	  
	  //All headers at once
	  Headers allHeaders=response.getHeaders();
	  for(Header header:allHeaders) {
		  System.out.println(header.getName()+"           "+header.getValue());
	  }
  }
}
