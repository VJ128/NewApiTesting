package com.qa.api.gorest;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class POST_User_InValidData2 {
	public static void main(String[] args) {

		try {
			// String result =
			// sendPOST("https://gorest.co.in/public/v2/users?access-token=5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b");
			String result = sendPOST("https://reqres.in/api/users/2");
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String sendPOST(String url) throws IOException {

		String result = "";
		HttpPost post = new HttpPost(url);
		// String string = "{\"$$$$\": \"Sam Smith\", \"technology\": \"Python\"}";

		// json.append("\"name\":\"mkyong\",");
		// json.append("\"notes\":\"hello\"");

		StringBuilder json = new StringBuilder();
	//	json.append("{");
		json.append("$$$$");
		//json.append("\"@#$^&*(()\":\"#%^&*(\"");
		// json.append("\"notes\":\"hello\"");
	//	json.append("}");

		// send a JSON data
		post.setEntity(new StringEntity(json.toString()));

		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {

			result = EntityUtils.toString(response.getEntity());
			System.out.println(response.getStatusLine().getProtocolVersion());// HTTP/1.1
			System.out.println(response.getStatusLine().getStatusCode());// 422
			System.out.println(response.getStatusLine().getReasonPhrase());// Unprocessable Entity
			System.out.println(response.getStatusLine().toString());
		}

		return result;
	}

}