package com.qa.api.testcases;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.pojos.PojoForPOST;
import com.qa.restapiclient.RestApiClient;
import com.qa.utilities.ReadExcelDemo;
import com.qa.utilities.ReadFromExcelPoi;
public class POST_User_Scenario8_DataDrivenTesting {
	RestApiClient restApiClient;
	String BaseUrl = "https://gorest.co.in/public/v2/users";
	PojoForPOST pojo, javaResp;
	String bearer = "Bearer 5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b";
	String stringJsonResponse,reqPayload;
	CloseableHttpResponse jresponse;

	@DataProvider(name="DP1")
	public Object[][] dataProvider() throws Exception{
	ReadExcelDemo readExcelDemo=new ReadExcelDemo();
		Object[][] testObjArray = ReadExcelDemo
				.getTableArray(System.getProperty("user.dir") + "\\src\\test\\resources\\Book1.xlsx", "Sheet1");
		return (testObjArray);
	}
	
	@Test(dataProvider = "DP1")
	public void dataDrivenTesting(String name,String email,String gender,String status) throws IOException {
		System.out.println(name+" "+email+" "+gender+" "+status );
		restApiClient = new RestApiClient();
		Map<String, String> headerMap = new HashMap();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Authorization", bearer);
		JSONObject request= new JSONObject();
		   request.put("name",name);
		   request.put("email",email);
		   request.put("gender",gender);
		   request.put("status",status);
		   reqPayload=request.toString();
		jresponse = restApiClient.post(BaseUrl, headerMap, reqPayload);
		stringJsonResponse = EntityUtils.toString(jresponse.getEntity());
		System.out.println(stringJsonResponse);

		// Method to deserialize JSON content from given JSON content String
		ObjectMapper objectMapper=new ObjectMapper();
		javaResp = objectMapper.readValue(stringJsonResponse, PojoForPOST.class);
		
		/*
		 * Assert.assertEquals(javaResp.getName(),name);
		 * Assert.assertEquals(javaResp.getEmail(),email);
		 * Assert.assertEquals(javaResp.getGender(),gender);
		 * Assert.assertEquals(javaResp.getStatus(),status);
		 */
	}
}