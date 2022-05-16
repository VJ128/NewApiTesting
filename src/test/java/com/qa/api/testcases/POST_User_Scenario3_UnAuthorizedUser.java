package com.qa.api.testcases;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class POST_User_Scenario3_UnAuthorizedUser {
	String baseUrl = "https://gorest.co.in/public/v2/users";

	public CloseableHttpResponse postRequest(String url) throws IOException {
		// Create HttpClient
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost postReq = new HttpPost(url);
		List<NameValuePair> urlParameters = new ArrayList<>();
		String emailId = "api" + Math.random() + "@gmail.com";
		String name = "API_Test";
		String gender = "male";
		String status = "active";
		urlParameters.add(new BasicNameValuePair("name", name));
		urlParameters.add(new BasicNameValuePair("email", emailId));
		urlParameters.add(new BasicNameValuePair("gender", gender));
		urlParameters.add(new BasicNameValuePair("status", status));
		postReq.setEntity(new UrlEncodedFormEntity(urlParameters));
		CloseableHttpResponse response = client.execute(postReq);
		return response;
	}

	public String getRespString(HttpResponse response) throws IOException {
		String respString = EntityUtils.toString(response.getEntity());
		return respString;
	}

	@Test // post with valid data
	public void postWithValidData() {
		try {
			HttpResponse response = postRequest(baseUrl);
			// Assert.assertEquals(response.getStatusLine().getProtocolVersion(),
			// "HTTP/1.1");//
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 401);//
			Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Unauthorized");//
			Assert.assertEquals(response.getStatusLine().toString(), "HTTP/1.1 401 Unauthorized");
			String respStrng = getRespString(response);
			System.out.println("respString " + respStrng);
			Assert.assertTrue(respStrng.contains("Authentication failed"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}