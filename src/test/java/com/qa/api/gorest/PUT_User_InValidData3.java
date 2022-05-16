package com.qa.api.gorest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

public class PUT_User_InValidData3 {
	String baseUrl="https://gorest.co.in/public/v2/users/3890";
	//String baseUrl="https://reqres.in/api/users/2");
	String reqPayload="";
	static String bearer="5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b";
	/*
	 * public static void main(String[] args) { try { // HttpResponse response =
	 * sendPUT(baseUrl); // HttpResponse response = sendPUT(baseUrl); // String
	 * result = EntityUtils.toString(response.getEntity()); //
	 * System.out.println(result); } catch (IOException e) { e.printStackTrace(); }
	 * }
	 */

	private static HttpResponse sendPUT(String url) throws IOException {
		// String result = "";
		HttpPut put = new HttpPut(url);
		put.addHeader("Content-Type", "application/json");
		put.addHeader("Authorization",bearer);
		String str = "$$$$";
		// send a JSON data
		put.setEntity(new StringEntity(str));
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse response = httpClient.execute(put);

		System.out.println(response.getStatusLine().getProtocolVersion());//
		System.out.println(response.getStatusLine().getStatusCode());//
		System.out.println(response.getStatusLine().getReasonPhrase());//
		System.out.println(response.getStatusLine().toString());

		return response;
	}

}