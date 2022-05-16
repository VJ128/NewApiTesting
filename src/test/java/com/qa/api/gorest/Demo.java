package com.qa.api.gorest;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

public class Demo {
	public static void main(String[] args) throws URISyntaxException {
		URI uri = new URIBuilder()
				.setScheme("https")
				.setHost("reqres.in/api")
				.setPath("/users")
				.setParameter("page", "%$#@((*")
				.build();
				HttpGet httpget = new HttpGet(uri);
				System.out.println(httpget.getURI());
				
				/*
				 * String url = UriComponentsBuilder
				 * .fromHttpUrl('https://gorest.co.in/public/v2/users') .queryParam('password',
				 * 'nithi%') .build() .encode() // This will URL-encode the parameter values
				 * .toUriString();
				 */
	}
	public void setUnAndPswd() {
	UsernamePasswordCredentials creds = new UsernamePasswordCredentials("user", "pwd");
	System.out.println(creds.getUserPrincipal().getName());
	System.out.println(creds.getPassword());}
}
/*
public void respHandling() {
Document result = Request.Get("http://somehost/content")
.execute().handleResponse(new ResponseHandler<Document>() {

public Document handleResponse(final HttpResponse response) throws IOException {
StatusLine statusLine = response.getStatusLine();
HttpEntity entity = response.getEntity();
if (statusLine.getStatusCode() >= 300) {
    throw new HttpResponseException(
            statusLine.getStatusCode(),
            statusLine.getReasonPhrase());
}
if (entity == null) {
    throw new ClientProtocolException("Response contains no content");
}
DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
try {
    DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
    ContentType contentType = ContentType.getOrDefault(entity);
    if (!contentType.equals(ContentType.APPLICATION_XML)) {
        throw new ClientProtocolException("Unexpected content type:" +
            contentType);
    }
    String charset = contentType.getCharset();
    if (charset == null) {
        charset = HTTP.DEFAULT_CONTENT_CHARSET;
    }
    return docBuilder.parse(entity.getContent(), charset);
} catch (ParserConfigurationException ex) {
    throw new IllegalStateException(ex);
} catch (SAXException ex) {
    throw new ClientProtocolException("Malformed XML document", ex);
}
}

});}*/

/*

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.data.Users;
import RestAPIClient.RestAPIClient;
import RestAPIClient.TestUtil;

public class POST extends TestBase {

	CloseableHttpResponse jsonresponse;
	RestAPIClient client;
	Users users;
	String url = geturl();
	ObjectMapper mapper;
	Users usersreobj;
	JSONObject jobject;
	String response, name, job;
	TestUtil util;
	Pattern p;
	@BeforeClass
	public void postapirequest() throws ClientProtocolException, IOException {
		client = new RestAPIClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");
		mapper = new ObjectMapper();
		users = new Users("Abc", "leader");
		String stringentity = util.convertJavaObjectToJson(users);
		jsonresponse = client.post(url, headermap, stringentity);

		// json String

		response = EntityUtils.toString(jsonresponse.getEntity(), "UTF-8");
		System.out.println(response);

		// json to java object
		usersreobj = util.covertJsonToJavaObject(response, Users.class);
	}

	@Test
	// Reason phrase validation
	public void reasonphrvalidation() {
		Assert.assertEquals(jsonresponse.getStatusLine().getReasonPhrase(), "Created");
	}

	@Test
	// Statuscode validation
	public void statuscodevalidation() {
		int statuscode = jsonresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, StatusCode_201);
	}

	@Test(enabled=false)
	public void getname() throws ParseException, IOException {
		Assert.assertEquals(usersreobj.getName(), "morpheus");
	}
    //validating that length of name field is between 5 and 10
	@Test(enabled=false)
	public void getnameLength() throws ParseException, IOException {
		if (usersreobj.getName().length() >= 5 && usersreobj.getName().length() <= 10) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}
	
	@Test
	public void getnameAndLength() throws ParseException, IOException {
		String name1="^[a-zA-Z]";
		String status="";
		String actualName=usersreobj.getName();
		p=Pattern.compile(name1);
		Matcher m=p.matcher(actualName);
		System.out.println(m);
		if(actualName.isBlank()||actualName.isEmpty()) {
			//status="entered name is blank/empty";
			Assert.assertTrue(false);
		}
		else if(m.matches()==false) {
			status="name is not valid according to the requirements";
			System.out.println(status);
			//Assert.assertTrue(false);
		}
		
	}

	@Test
	public void testingjob() {
		Assert.assertEquals(usersreobj.getJob(), "leader");
	}

	@Test
	public void testingid() {
		Assert.assertTrue(usersreobj.getId() != null);
	}

	@Test
	// Header validation
	public void headervalidation() {
		Header[] header = jsonresponse.getAllHeaders();
		for (Header hd : header) {
			if (hd.getName().equals("Content-Type")) {
				Assert.assertEquals(hd.getValue(), "application/json; charset=utf-8");
			}
		}
	}
}
*/

/*
 private final class OkidokiHandler implements ResponseHandler<Boolean> {
    public Boolean handleResponse(
            final HttpResponse response) throws ClientProtocolException, IOException {
        return response.getStatusLine().getStatusCode() == 200;
    }
}

HttpRequestFutureTask<Boolean> task = futureRequestExecutionService.execute(
    new HttpGet("http://www.google.com"), HttpClientContext.create(),
    new OkidokiHandler());
// blocks until the request complete and then returns true if you can connect to Google
boolean ok=task.get();
task.scheduledTime() // returns the timestamp the task was scheduled
task.startedTime() // returns the timestamp when the task was started
task.endedTime() // returns the timestamp when the task was done executing
task.requestDuration // returns the duration of the http request
task.taskDuration // returns the duration of the task from the moment it was scheduled

FutureRequestExecutionMetrics metrics = futureRequestExecutionService.metrics()
metrics.getActiveConnectionCount() // currently active connections
metrics.getScheduledConnectionCount(); // currently scheduled connections
metrics.getSuccessfulConnectionCount(); // total number of successful requests
metrics.getSuccessfulConnectionAverageDuration(); // average request duration
metrics.getFailedConnectionCount(); // total number of failed tasks
metrics.getFailedConnectionAverageDuration(); // average duration of failed tasks
metrics.getTaskCount(); // total number of tasks scheduled
metrics.getRequestCount(); // total number of requests
metrics.getRequestAverageDuration(); // average request duration
metrics.getTaskAverageDuration(); // average task duration
*/