package com.qa.api.testcases;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class POST_User_Scenario12_MissingParametersInPayload {
	String baseUrl = "https://gorest.co.in/public/v2/users";
	// String baseUrl="https://reqres.in/api/users/2");
	String bearer = "Bearer 5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b";

	public CloseableHttpResponse postRequest(String url) throws IOException {
		// Create HttpClient
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost postReq = new HttpPost(url);
		postReq.addHeader("Authorization", bearer);
		// postReq.addHeader("Content-Type", "application/json");
		List<NameValuePair> urlParameters = new ArrayList<>();
	// Try to comment all parameters and diff combinations
	//	String emailId = "api" + Math.random() + "@gmail.com";
		String name = "API_Test";
		String gender = "male";
		String status = "active";
		urlParameters.add(new BasicNameValuePair("name", name));
	//	urlParameters.add(new BasicNameValuePair("email", emailId));
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
	public void postWithMissingEmailID() {
		try {
			HttpResponse response = postRequest(baseUrl);
			// Assert.assertEquals(response.getStatusLine().getProtocolVersion(),
			// "HTTP/1.1");//
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 422);//
			Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Unprocessable Entity");//
			Assert.assertEquals(response.getStatusLine().toString(), "HTTP/1.1 422 Unprocessable Entity");
			String respStrng = getRespString(response);
			Assert.assertTrue(respStrng.contains("can't be blank"));
			System.out.println("respString " + respStrng);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}