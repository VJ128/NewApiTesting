package com.qa.api.reqres;


import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GET_HttpRequest {

	public static void main(String[] args) throws Exception {
		HttpGet request = new HttpGet("https://reqres.in/api/users/2");

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
					String result = EntityUtils.toString(entity);
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