package com.qa.api.testcases;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
	String name = "Ashley", email = "ash" + Math.random() + "@gmail.com", gender = "female", status = "active";
	String jStatus, jName, jEmail, jGender;
	CloseableHttpResponse jresponse;
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
		// Method that can be used to serialize any Java value as a String
		String reqPayload = objectMapper.writeValueAsString(pojo);
		jresponse = restApiClient.post(BaseUrl, headerMap, reqPayload);
		stringJsonResponse = EntityUtils.toString(jresponse.getEntity());
		System.out.println(stringJsonResponse);

		try {
			file = new FileWriter(".\\src\\main\\resources\\postdata.json");
			file.write(stringJsonResponse);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// Method to deserialize JSON content from given JSON content String
		javaResp = objectMapper.readValue(stringJsonResponse, PojoForPOST.class);
		jName = javaResp.getName();
		jEmail = javaResp.getEmail();
		jGender = javaResp.getGender();
		jStatus = javaResp.getStatus();
	}

	// 1.Response Code Validation
	@Test
	public void respCodeValidation() {
		Assert.assertEquals(jresponse.getStatusLine().getStatusCode(), 201);
	}

	// 2.ReasonPhraseValidation
	@Test
	public void reasonPhraseValidation() {
		Assert.assertEquals(jresponse.getStatusLine().getReasonPhrase(), "Created");
	}
    // 3.DataValidation
	@Test
	public void dataValidation() {
		/*
		 * Object name1 = jsonresp.get("name"); String name2 = user.getName();
		 * Assert.assertEquals(name1, name2);
		 */
		Assert.assertEquals(jName, name);
		Assert.assertEquals(jEmail, email);
		Assert.assertEquals(jGender, gender);
		Assert.assertEquals(jStatus, status);
	}
    // 4.Schema Validation
	@Test()
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
    // 5.Parameter length Validation
	@Test
	public void lengthOfParametersValidation() {
		Assert.assertTrue(jName.length() >= 4 & jName.length() <= 10);
	}
    // 6.Parameters not null validation
	@Test
	public void idNotNull() {
		Assert.assertNotNull(javaResp.getId());
	}
    // 7.RespPayloadNotNull
	@Test
	public void respPayloadNotNull() {
		Assert.assertNotNull(stringJsonResponse);
	}
 
	// 8.Header validation
	@Test
	public void headerValidation() {
		Header[] headerarray = jresponse.getAllHeaders();

		for (Header header : headerarray) {
			if (header.getName().equals("Content-Type")) {
				Assert.assertEquals(header.getValue(), "application/json; charset=utf-8");
			}
		}
	}

	// 9.Header count validation
	@Test
	public void headerCountValidation() {
		Header[] headerarray = jresponse.getAllHeaders();
		Assert.assertEquals(headerarray.length, 21);
	}

	/* After sorting
	 * [{"id":5,"email":"charles.morris@reqres.in","first_name":"Charles",
	 * "last_name":"Morris","avatar":"https://reqres.in/img/faces/5-image.jpg"},
	 * {"id":3,"email":"emma.wong@reqres.in","first_name":"Emma","last_name":"Wong",
	 * "avatar":"https://reqres.in/img/faces/3-image.jpg"},
	 * {"id":4,"email":"eve.holt@reqres.in","first_name":"Eve","last_name":"Holt",
	 * "avatar":"https://reqres.in/img/faces/4-image.jpg"},
	 * {"id":1,"email":"george.bluth@reqres.in","first_name":"George","last_name":
	 * "Bluth","avatar":"https://reqres.in/img/faces/1-image.jpg"},
	 * {"id":2,"email":"janet.weaver@reqres.in","first_name":"Janet","last_name":
	 * "Weaver","avatar":"https://reqres.in/img/faces/2-image.jpg"},
	 * {"id":6,"email":"tracey.ramos@reqres.in","first_name":"Tracey","last_name":
	 * "Ramos","avatar":"https://reqres.in/img/faces/6-image.jpg"}]
	 *  Before sorting
	 * {"page":1,"per_page":6,"total":12,"total_pages":2,"data":[{"id":1,"email":
	 * "george.bluth@reqres.in","first_name":"George","last_name":"Bluth","avatar":
	 * "https://reqres.in/img/faces/1-image.jpg"},{"id":2,"email":
	 * "janet.weaver@reqres.in","first_name":"Janet","last_name":"Weaver","avatar":
	 * "https://reqres.in/img/faces/2-image.jpg"},{"id":3,"email":
	 * "emma.wong@reqres.in","first_name":"Emma","last_name":"Wong","avatar":
	 * "https://reqres.in/img/faces/3-image.jpg"},{"id":4,"email":
	 * "eve.holt@reqres.in","first_name":"Eve","last_name":"Holt","avatar":
	 * "https://reqres.in/img/faces/4-image.jpg"},{"id":5,"email":
	 * "charles.morris@reqres.in","first_name":"Charles","last_name":"Morris",
	 * "avatar":"https://reqres.in/img/faces/5-image.jpg"},{"id":6,"email":
	 * "tracey.ramos@reqres.in","first_name":"Tracey","last_name":"Ramos","avatar":
	 * "https://reqres.in/img/faces/6-image.jpg"}],"support":{"url":
	 * "https://reqres.in/#support-heading",
	 * "text":"To keep ReqRes free, contributions towards server costs are appreciated!"
	 * }}
	 */ 
	// 10.sort Data By FirstName
	@Test(enabled=false)
	public static void sortDataByFirstName(String data) throws IOException {
		JsonNode node = new ObjectMapper().readTree(data);
		ArrayNode array = (ArrayNode) node.get("data");
		Iterator<JsonNode> i = array.elements();
		List<JsonNode> list = new ArrayList<>();
		while (i.hasNext()) {
			list.add(i.next());
		}
		list.sort(Comparator.comparing(o -> o.get("first_name").asText()));
		System.out.println(list);
	}


	@AfterClass
	public void tearDown() {
	}
}
// req payload should should not be null
// blank job,blank name,spl characters
// no duplicate record