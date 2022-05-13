package com.qa.api.gorest;

import java.io.IOException;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Post {
	 public static void main(String[] args) {

	        try {
	            String result = sendPOST("https://gorest.co.in/public/v2/users?access-token=5c704e9580b42878fedd75089099a464072a73f93bb763666279b587b86c995b");
	            System.out.println(result);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }

	    private static String sendPOST(String url) throws IOException {

	        String result = "";
	        HttpPost post = new HttpPost(url);

	        StringBuilder json = new StringBuilder();
			json.append("{");
			json.append("\"name\":\"api_test\",");
			json.append("\"email\":\"api_test123@gmail.com\",");
			json.append("\"gender\":\"female\",");
			json.append("\"status\":\"active\"");
			json.append("}");

	        // send a JSON data
	        post.setEntity(new StringEntity(json.toString()));

	        try (CloseableHttpClient httpClient = HttpClients.createDefault();
	             CloseableHttpResponse response = httpClient.execute(post)) {

	            result = EntityUtils.toString(response.getEntity());
	        }

	        return result;
	    }

	}