package com.qa.api.gorest;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class POST_User_ValidData {
	 private static InputStream inputStreamFromClasspath(String path) {
	        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	    }

	    public static void main(String[] args) throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(VersionFlag.V201909);

	        try (
	                InputStream jsonStream = inputStreamFromClasspath("example.json");
	                InputStream schemaStream = inputStreamFromClasspath("example-schema.json")
	        ) {
	            JsonNode json = objectMapper.readTree(jsonStream);
	            JsonSchema schema = schemaFactory.getSchema(schemaStream);
	            Set<ValidationMessage> validationResult = schema.validate(json);

	            // print validation errors
	            if (validationResult.isEmpty()) {
	                System.out.println("no validation errors :-)");
	            } else {
	                validationResult.forEach(vm -> System.out.println(vm.getMessage()));
	            }
	        }
	    }
	}
//{"id":3362,"name":"API_Test","email":"api0.1957582652504538@gmail.com","gender":"male","status":"active"}
