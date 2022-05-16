package com.qa.api.gorest;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class POST_User_InValidData {
	public static void main(String[] args) {
		try {
			String result = sendPOST(
					"https://gorest.co.in/public/v2/users?access-token=5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b");
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String sendPOST(String url) throws IOException {

		String result = "";
		HttpPost post = new HttpPost(url);

		// add request parameters or form parameters
		List<NameValuePair> urlParameters = new ArrayList<>();
		String emailId = "$$$$";
		String name = "$$$$";
		String gender = "$$$$";
		String status = "$$$$";
		urlParameters.add(new BasicNameValuePair("name", name));
		urlParameters.add(new BasicNameValuePair("email", emailId));
		urlParameters.add(new BasicNameValuePair("gender", gender));
		urlParameters.add(new BasicNameValuePair("status", status));
		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {

			result = EntityUtils.toString(response.getEntity());
			System.out.println(response.getStatusLine().getProtocolVersion());//HTTP/1.1
			System.out.println(response.getStatusLine().getStatusCode());//422
			System.out.println(response.getStatusLine().getReasonPhrase());//Unprocessable Entity
			System.out.println(response.getStatusLine().toString());//HTTP/1.1 422 Unprocessable Entity
			System.out.println(result.contains("id"));
			System.out.println(result.contains(name));
			System.out.println(result.contains(emailId));
			System.out.println(result.contains(gender));
			System.out.println(result.contains(status));
			Header[] headers=response.getAllHeaders();
			for(Header header:headers) {
System.out.println(header);
				}
			}
		return result;
	}
}