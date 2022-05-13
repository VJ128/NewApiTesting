package com.qa.api.gorest;

import java.net.URI;
import java.net.URISyntaxException;

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
	
	
}
