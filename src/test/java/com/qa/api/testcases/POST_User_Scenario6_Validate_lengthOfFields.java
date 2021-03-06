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

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class POST_User_Scenario6_Validate_lengthOfFields {
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
		String emailId = "api" + Math.random() + "@gmail.com";
		String name = "Test_123ekdsjofdsfoidf93ufjkendilurj3ioewkjffdddddddddddddddddddeeeeeqadcscdscfdgert4r443333333333333333333333333333333333fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff44444444444444444444444dsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa444444444444444444444444444444444444444444444444444444444444444443333333333333333333333366666666666666666666666666666666666gggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggbbbbbbbbbbbbbbbbbbbbbbbbbbafffffffffffffffffffiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiitttttttttttttttttttttttttttttttttttttttttttttttteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeesssssssssssssssssssssssssssssssssttttttttttttttttttttttttttt";
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
		HttpEntity entity = response.getEntity();
		String respString = EntityUtils.toString(response.getEntity());
		// System.out.println(EntityUtils.toByteArray(entity).length);
		return respString;
	}

//tc to find if field accepts more than the range of length as per requirement
	@Test
	public void validateMaxLengthOfNameField() {
		try {
			HttpResponse response = postRequest(baseUrl);
			String respStrng = getRespString(response);
			System.out.println("respString " + respStrng);
			Assert.assertTrue(respStrng
					.contentEquals("[{\"field\":\"name\",\"message\":\"is too long (maximum is 200 characters)\"}]"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// incomplete
	@Test(enabled = false)
	public void validateLengthOfFields() {
		try {
			HttpResponse response = postRequest(baseUrl);
			String respStrng = getRespString(response);
			System.out.println("respString " + respStrng);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
