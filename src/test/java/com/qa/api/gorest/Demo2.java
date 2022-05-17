package com.qa.api.gorest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
public class Demo2 {
	    private static FileWriter file;

	    @SuppressWarnings("unchecked")
	    public static void main(String[] args) {
	 String s="abcd";
	        // JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
	        JSONObject obj = new JSONObject();
	        obj.put("Name", "Vijaya");
	        obj.put("Author", "App Shah");
	 
	        JSONArray company = new JSONArray();
	        company.add("Company: Facebook");
	        company.add("Company: PayPal");
	        company.add("Company: Google");
	        obj.put("Company List", company);
	        try {
	 	            // Constructs a FileWriter given a file name, using the platform's default charset
	            file = new FileWriter(".\\abc.json");
	         //   file.write(obj.toJSONString());

	 
	        } catch (IOException e) {
	            e.printStackTrace();
	 
	        } finally {
	 
	            try {
	                file.flush();
	                file.close();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
	 
	    static public void CrunchifyLog(String str) {
	        System.out.println("str");
	    }
	 
	}