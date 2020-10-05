package dataDrivenTC;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class postEmployee {
  @DataProvider(name="dataProviderName")
  public String[][] getTestData() throws IOException{
	//return new Object[][] {
		//{"amit","dev",1000},{"deep","manager",2000},{"Kashif","SDET",3000}
		
		String path = System.getProperty("user.dir")+"/src/test/java/dataDrivenTC/TestData.xlsx";
		int rowCount=XLUtility.getRowCount(path, "Sheet1");
		int colCount=XLUtility.getCellCount(path, "Sheet1", 1);
		
		String[][] empData=new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				empData[i-1][j]= XLUtility.getCellData(path, "Sheet1", i, j);
				System.out.println("Reading test data :"+empData[i-1][j]);
			}	
		}
		
		return (empData);
	//};
  }
	
	@Test(dataProvider="dataProviderName")
  public void postEmployeeMethod(String eName,String eJob, String eSalary) {
	  //ASSUME A POST REQUEST WHICH WILL CREATE A NEW EMPLOYEE AND WE ARE VALIDATING IF THE EMPLOYEE
	  //IS SUCCESFULLY CREATED. THE URL IS CURRENTLY NOT WORKING
	  
	  //Base URI
	  RestAssured.baseURI="https://reqres.in";
	  
	  //Request object
	  RequestSpecification httpRequest= RestAssured.given();
	  	  
	  //Request body parameters
	  JSONObject requestParams = new JSONObject();
	  requestParams.put("name", eName);
	  requestParams.put("job", eJob);
	  requestParams.put("salary", eSalary);
	  	  
	  //Add params to body && Convert body items to JSON string
	  httpRequest.body(requestParams.toJSONString());
	  
	  //request Header
	  httpRequest.header("Content-Type", "application/json");
	  
	  //Response Object
	  Response response= httpRequest.request(Method.POST,"/api/users");
	  
	  //Response validation
	  String responseBody=response.getBody().asString();
	  
	  System.out.println("Here is the response"+responseBody);
	  Assert.assertTrue(responseBody.contains(eName));
	  Assert.assertTrue(responseBody.contains(eJob));
	  Assert.assertTrue(responseBody.contains(eSalary));
	  
  }
}
