package com.qa.api.gorest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Demo2 {
	String str="{"results": [
	             {
	               "layerId": 5,
	               "layerName": "Pharmaceutical Entities",
	               "attributes": {
	                 "OBJECTID": "35",
	                 "FACILITYTYPE": "Pharmacy",
	                 "FACILITYSUBTYPE": "24 Hr Pharmacy",
	                 "COMMERCIALNAME_E": "SADD MAARAB PHARMACY"
	                 },
	               "geometryType": "esriGeometryPoint",
	              },
	             {
	               "layerId": 5,
	               "layerName": "Pharmaceutical Entities",
	               "attributes": {
	                 "OBJECTID": "1",
	                 "FACILITYTYPE": "Pharmacy",
	                 "FACILITYSUBTYPE": "24 Hr Pharmacy",
	                 "COMMERCIALNAME_E": "GAYATHY HOSPITAL  PHARMACY"
	               },
	               "geometryType": "esriGeometryPoint",
	             },
	                {
	               "layerId": 5,
	               "layerName": "Pharmaceutical Entities",
	               "attributes": {
	                 "OBJECTID": "255",
	                 "FACILITYTYPE": "Pharmacy",
	                 "FACILITYSUBTYPE": "24 Hr Pharmacy",
	                 "COMMERCIALNAME_E": "AL DEWAN PHARMACY"
	                 },
	               "geometryType": "esriGeometryPoint",
	              }
	           ]}";
	
	 public void sort(String data) throws IOException {
		    JsonNode node = new ObjectMapper().readTree(data);
		    ArrayNode array = (ArrayNode) node.get("results");
		    Iterator<JsonNode> i =array.elements();
		    List<JsonNode> list = new ArrayList<>();
		    while(i.hasNext()){
		        list.add(i.next());
		    }
		    list.sort(Comparator.comparing(o -> o.get("attributes").get("COMMERCIALNAME_E").asText()));
		}
	}

