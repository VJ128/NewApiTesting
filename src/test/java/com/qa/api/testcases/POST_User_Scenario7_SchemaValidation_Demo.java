package com.qa.api.testcases;

import java.io.InputStream;
import java.util.Set;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion.VersionFlag;

public class POST_User_Scenario7_SchemaValidation_Demo {
	 private static InputStream inputStreamFromClasspath(String path) {
	        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
	    }

	    public static void main(String[] args) throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(VersionFlag.V201909);

	        try (
	                InputStream jsonStream = inputStreamFromClasspath("postdata.json");
	                InputStream schemaStream = inputStreamFromClasspath("postdata-schema.json")
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
