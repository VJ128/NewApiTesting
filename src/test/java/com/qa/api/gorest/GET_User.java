package com.qa.api.gorest;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GET_User {
	public static void main(String[] args) throws IOException {
		HttpGet request = new HttpGet(
				"https://gorest.co.in/public/v2/users/7616?access-token=5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b");
		// add request headers
		// request.addHeader("custom-key", "mkyong");
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(request)) {
			// Get HttpResponse Status
			System.out.println(response.getProtocolVersion()); // HTTP/1.1
			System.out.println(response.getStatusLine().getStatusCode()); // 200
			System.out.println(response.getStatusLine().getReasonPhrase()); // OK
			System.out.println(response.getStatusLine().toString()); // HTTP/1.1 200 OK
			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header);
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// return it as a String
				String result = EntityUtils.toString(entity);
				System.out.println(result);
				System.out.println(result.contains("Test"));
			}
		}
	}
}











