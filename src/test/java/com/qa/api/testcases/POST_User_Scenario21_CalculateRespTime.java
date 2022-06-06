package com.qa.api.testcases;

import java.io.IOException;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class POST_User_Scenario21_CalculateRespTime {
	static CloseableHttpResponse response;
//Another method at the bottom
	public static void main(String[] args) throws IOException {
		HttpGet request = new HttpGet(
				"https://gorest.co.in/public/v2/users/3876?access-token=5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// open your connection
		long start = System.currentTimeMillis(); 
		// send request, wait for response (the simple socket calls are all blocking)
		response = httpClient.execute(request);
		long end = System.currentTimeMillis();
		System.out.println("Round trip response time = " + (end - start) + " millis");

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
/*
 public class GET_User_RespTime2 {
static	CloseableHttpResponse response;
	public static void main(String[] args) throws IOException {
		HttpGet request = new HttpGet(
				"https://gorest.co.in/public/v2/users/3876?access-token=5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b");
		// add request headers
		// request.addHeader("custom-key", "mkyong");
		
StopWatch watch = new StopWatch();
CloseableHttpClient httpClient = HttpClients.createDefault();
try {
watch.start();
 response = httpClient.execute(request);}
catch (IOException e) {
    e.printStackTrace();
} finally {
    watch.stop();
}
System.out.println("Resp time is -> "+String.format(watch.toString()));
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

*/
