package com.qa.api.gorest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.FutureRequestExecutionService;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.HttpRequestFutureTask;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Tryingtimes {
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
		/*
		 * HttpClient
		 * httpClient=HttpClientBuilder.create().setMaxConnPerRoute(1).build(); //
		 * HttpClient httpClient =
		 * HttpClientBuilder.create().setMaxConnPerRoute(5).build(); ExecutorService
		 * executorService = Executors.newFixedThreadPool(1);
		 * FutureRequestExecutionService futureRequestExecutionService = new
		 * FutureRequestExecutionService(client, executorService);
		 * 
		 * HttpRequestFutureTask<Boolean> task = futureRequestExecutionService.execute(
		 * new HttpGet("http://www.google.com"),HttpClientContext.create(), new
		 * OkidokiHandler()); // blocks until the request complete and then returns true
		 * if you can connect to Google boolean ok=task.get(); task.scheduledTime(); //
		 * returns the timestamp the task was scheduled task.startedTime(); // returns
		 * the timestamp when the task was started task.endedTime(); // returns the
		 * timestamp when the task was done executing task.requestDuration(); // returns
		 * the duration of the http request task.taskDuration(); // returns the duration
		 * of the task from the moment it was scheduled
		 *//*
			 * FutureRequestExecutionMetrics metrics =
			 * futureRequestExecutionService.metrics() metrics.getActiveConnectionCount() //
			 * currently active connections metrics.getScheduledConnectionCount(); //
			 * currently scheduled connections metrics.getSuccessfulConnectionCount(); //
			 * total number of successful requests
			 * metrics.getSuccessfulConnectionAverageDuration(); // average request duration
			 * metrics.getFailedConnectionCount(); // total number of failed tasks
			 * metrics.getFailedConnectionAverageDuration(); // average duration of failed
			 * tasks metrics.getTaskCount(); // total number of tasks scheduled
			 * metrics.getRequestCount(); // total number of requests
			 * metrics.getRequestAverageDuration(); // average request duration
			 * metrics.getTaskAverageDuration(); // average task duration
			 */	}

	public String getRespString(HttpResponse response) throws IOException {
		String respString = EntityUtils.toString(response.getEntity());
		return respString;
	}

}
final class OkidokiHandler implements ResponseHandler<Boolean> {
    public Boolean handleResponse(
            final HttpResponse response) throws IOException {
        return response.getStatusLine().getStatusCode() == 200;
    }
}
