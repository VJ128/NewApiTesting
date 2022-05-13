package com.qa.api.testcases;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class POST_User_Scenario11_BlankPathInUrl {
	String baseUrl = "https://gorest.co.in/public/v2/users/%&^^%$";
	// String baseUrl="https://reqres.in/api/users/2");
	String bearer = "Bearer 5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b";
	URI uri;

	public CloseableHttpResponse postRequest(URI uri2) throws IOException, Exception {

		// Create HttpClient
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost postReq = new HttpPost(uri2);
		postReq.addHeader("Authorization", bearer);
		// postReq.addHeader("Content-Type", "application/json");
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
	public void postWithValidData() throws Exception {
		try {
			uri = new URIBuilder()
					.setScheme("https")
					.setHost("gorest.co.in/public/v2/")
					.setPath("/users").setPath(" ")
					.build();
			HttpResponse response = postRequest(uri);
			Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);//
			Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Found");//
			Assert.assertEquals(response.getStatusLine().toString(), "HTTP/1.1 404 Not Found");
			String respStrng = getRespString(response);
			System.out.println("respString " + respStrng);
			Assert.assertTrue(respStrng.contains("Page Not Found | GO REST"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}