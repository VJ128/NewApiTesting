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

public class POST_User_Scenario5_InvalidData_Not_JSON {
	String baseUrl = "https://gorest.co.in/public/v2/users";
	// String baseUrl="https://reqres.in/api/users/2");
	String bearer = "Bearer 5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b";

	public CloseableHttpResponse postRequest(String url) throws IOException {
		// Create HttpClient
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost postReq = new HttpPost(url);
		postReq.addHeader("Authorization", bearer);
		postReq.addHeader("Content-Type", "application/json");
		String reqPyld="@#$%^&*";
		postReq.setEntity(new StringEntity(reqPyld));
		CloseableHttpResponse response = client.execute(postReq);
		return response;
	}

	public String getRespString(HttpResponse response) throws IOException {
		String respString = EntityUtils.toString(response.getEntity());
		return respString;
	}

	@Test// post with valid data
	public void postWithValidData() {
		try {
			HttpResponse response = postRequest(baseUrl);
			System.out.println(response.getStatusLine().getStatusCode());
			//Assert.assertEquals(response.getStatusLine().getProtocolVersion(), "HTTP/1.1");//
			//Assert.assertEquals(response.getStatusLine().getStatusCode(), 406);//
			//Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "Not Acceptable");//
			//Assert.assertEquals(response.getStatusLine().toString(), "HTTP/1.1 406 Not Acceptable");
			String respStrng = getRespString(response);
			System.out.println("respString " + respStrng);
		//	Assert.assertTrue(respStrng.contains("is invalid"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}




/*
URI uri = new URIBuilder()
.setScheme("http")
.setHost("www.google.com")
.setPath("/search")
.setParameter("q", "httpclient")
.setParameter("btnG", "Google Search")
.setParameter("aq", "f")
.setParameter("oq", "")
.build();
HttpGet httpget = new HttpGet(uri);
System.out.println(httpget.getURI());*/