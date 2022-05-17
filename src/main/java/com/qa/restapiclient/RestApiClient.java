package com.qa.restapiclient;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestApiClient {

	public CloseableHttpResponse post(String url, Map<String, String> headerMap, String reqPayLoad) throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		for (Entry<String, String> entry : headerMap.entrySet()) {
			post.addHeader(entry.getKey(), entry.getValue());
		}
		post.setEntity(new StringEntity(reqPayLoad));
		CloseableHttpResponse response = client.execute(post);
		return response;
	}
}
