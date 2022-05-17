package com.qa.api.reqres;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class GET_HttpRequest {
	static String result;
	public static void main(String[] args) throws Exception {
		HttpGet request = new HttpGet("https://reqres.in/api/users");

		// add request headers
		request.addHeader("custom-key", "mkyong");
		request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");

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

				long len = entity.getContentLength();
				System.out.println(len);
				//if (len != -1 && len < 2048) {
					// return it as a String
					 result = EntityUtils.toString(entity);
					 sortDataByFirstName(result);
					System.out.println(result);
				//} else {
					// Stream content out
				//}
			}
		}
		System.out.println(request.getRequestLine().getMethod());
		System.out.println(request.getRequestLine().getUri());
		System.out.println(request.getRequestLine().getProtocolVersion());
		// testBasicDateFormat();
	}
	 public static void sortDataByFirstName(String data) throws IOException {
		    JsonNode node = new ObjectMapper().readTree(data);
		    ArrayNode array = (ArrayNode) node.get("data");
		    Iterator<JsonNode> i =array.elements();
		    List<JsonNode> list = new ArrayList<>();
		    while(i.hasNext()){
		        list.add(i.next());
		    }
		    list.sort(Comparator.comparing(o -> o.get("first_name").asText()));
		    System.out.println(list);
		}
}

/*
 * @Test public void testBasicProperties() throws Exception { final HttpGet
 * httpget = new HttpGet("http://host/path"); Assert.assertEquals("GET",
 * httpget.getRequestLine().getMethod());
 * Assert.assertEquals("http://host/path", httpget.getRequestLine().getUri());
 * Assert.assertEquals(HttpVersion.HTTP_1_1,
 * httpget.getRequestLine().getProtocolVersion()); }
 * 
 * @Test public void testEmptyURI() throws Exception { final HttpGet httpget =
 * new HttpGet(""); Assert.assertEquals("/", httpget.getRequestLine().getUri());
 * }
 * 
 */

/*
 * public static void testBasicDateFormat() throws Exception { final Calendar
 * calendar = Calendar.getInstance(); calendar.setTimeZone(DateUtils.GMT);
 * calendar.set(2020, Calendar.MAY, 01, 0, 0, 0);
 * calendar.set(Calendar.MILLISECOND, 0); final Date date = calendar.getTime();
 * System.out.println(date); System.out.println(DateUtils.formatDate(date));
 * System.out.println(DateUtils.formatDate(date, DateUtils.PATTERN_RFC1123)); //
 * Assert.assertEquals("Fri, 14 Oct 2005 00:00:00 GMT",
 * DateUtils.formatDate(date)); //
 * Assert.assertEquals("Fri, 14 Oct 2005 00:00:00 GMT",
 * DateUtils.formatDate(date, DateUtils.PATTERN_RFC1123));
 */