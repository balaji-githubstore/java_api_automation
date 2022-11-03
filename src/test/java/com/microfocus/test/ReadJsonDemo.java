package com.microfocus.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ReadJsonDemo {

	public static void main(String[] args) throws IOException {
		
		FileInputStream file=new FileInputStream("test-data/addpet.json");

		ObjectMapper mapper=new ObjectMapper();
		JsonNode json= mapper.readTree(file);
		
		System.out.println(json.get("id"));
		System.out.println(json.get("name"));
		System.out.println(json.get("category").get("name"));
		
		System.out.println(json.get("tags"));
		System.out.println(json.get("tags").get(0));
		System.out.println(json.get("tags").get(0).get("name"));
		
		FileInputStream file2=new FileInputStream("test-data/addpet.json");
		
		ObjectMapper mapper1=new ObjectMapper();
		JsonNode json1= mapper1.readTree(file2);
		
		System.out.println(json1.equals(json));
		
		Assert.assertTrue(json1.equals(json));
		
		
	}

}
