package com.qa.api.testcases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.qa.pojos.PojoForPOST;
import com.qa.restapiclient.RestApiClient;

public class POST {
	RestApiClient restApiClient;
	String BaseUrl = "https://gorest.co.in/public/v2/users";
	PojoForPOST pojo, javaResp;
	String bearer = "Bearer 5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b";
	ObjectMapper objectMapper;
	String stringJsonResponse;
	String name = "ReniJohn", email = "reni" + Math.random() + "@gmail.com", 
			gender = "female", status = "active";
	String jStatus, jName, jEmail, jGender;
	CloseableHttpResponse jresponse;
	//JSONObject jsonresp;
	private static FileWriter file;
	
	private static InputStream inputStreamFromClasspath(String path) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	}

	@BeforeClass
	public void postRequest() throws IOException {
		restApiClient = new RestApiClient();
		Map<String, String> headerMap = new HashMap();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Authorization", bearer);
		objectMapper = new ObjectMapper();
		pojo = new PojoForPOST(name, email, gender, status);
		// Java object is converted to Json(Serialization)
		String reqPayload = objectMapper.writeValueAsString(pojo);
		jresponse = restApiClient.post(BaseUrl, headerMap, reqPayload);
		stringJsonResponse = EntityUtils.toString(jresponse.getEntity());
		System.out.println(stringJsonResponse);
	//	jsonresp = new JSONObject(stringJsonResponse);
		
		try {
		file = new FileWriter(".\\src\\main\\resources\\postdata.json");
        file.write(stringJsonResponse);}
		catch (IOException e) {
            e.printStackTrace();
         } finally {
 
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
		
		
		// Json is converted to Javaobject(De-Serialization)
		javaResp = objectMapper.readValue(stringJsonResponse, PojoForPOST.class);
		jName = javaResp.getName();
		jEmail = javaResp.getEmail();
		jGender = javaResp.getGender();
		jStatus = javaResp.getStatus();
		
	}

	@Test
	public void dataValidation() {
		/*		Object name1 = jsonresp.get("name");
		String name2 = user.getName();
		Assert.assertEquals(name1, name2);*/
		Assert.assertEquals(jName, name);
		Assert.assertEquals(jEmail, email);
		Assert.assertEquals(jGender, gender);
		Assert.assertEquals(jStatus, status);
	}

	@Test
	public void schemaValidation() throws IOException {
		JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(VersionFlag.V201909);

		try (InputStream jsonStream = inputStreamFromClasspath("postdata.json");
				InputStream schemaStream = inputStreamFromClasspath("postdata-schema.json")) {
			JsonNode json = objectMapper.readTree(jsonStream);
			JsonSchema schema = schemaFactory.getSchema(schemaStream);
			Set<ValidationMessage> validationResult = schema.validate(json);

			// print validation errors
			if (!validationResult.isEmpty()) 
			validationResult.forEach(vm -> System.out.println(vm.getMessage()));
			Assert.assertTrue(validationResult.isEmpty());
		}
	}

	@Test
	public void lengthOfParametersValidation() {
		Assert.assertTrue(jName.length() >= 4 & jName.length() <= 10);
	}

	@Test
	public void idNotNull() {
		Assert.assertNotNull(javaResp.getId());
	}
	@Test
	public void respPayloadNotNull() {
		Assert.assertNotNull(stringJsonResponse);
	}
	//header validation
		@Test
		public void headerValidation()
		{
			Header[] headerarray=jresponse.getAllHeaders();
			
			for(Header header:headerarray)
			{
				if(header.getName().equals("Content-Type")) {
				Assert.assertEquals(header.getValue(), "application/json; charset=utf-8");
			}
			}
		}
	@AfterClass
	public void tearDown() {
	}
}
// req payload should should not be null
// blank job,blank name,spl characters
// no duplicate record