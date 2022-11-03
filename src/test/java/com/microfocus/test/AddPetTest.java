package com.microfocus.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class AddPetTest {

	public static String baseUrl = "https://petstore.swagger.io/v2/";
	
	@Test
	public void addValidPetTest()
	{
		String resource="pet";
		
		String response=RestAssured
		.given().header("Content-Type", "application/json").body("{\r\n"
				+ "  \"id\": 8001,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"string\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"doggie\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}")
		.when().post(baseUrl+resource)
		.then().log().all().statusCode(200).extract().asPrettyString();
		
		System.out.println("------------------------------------------");
		System.out.println(response);
		
		
	}
	
	@Test
	public void addValidPetFromJsonTest() throws FileNotFoundException
	{
		FileInputStream file=new FileInputStream("test-data/addpet.json");
		
		JsonPath jsonPath=new JsonPath(file);
		String json=jsonPath.prettify();
		
		String resource="pet";
		
		String response=RestAssured
		.given().header("Content-Type", "application/json").body(json)
		.when().post(baseUrl+resource)
		.then().log().all().statusCode(200).extract().asPrettyString();
		
		System.out.println("------------------------------------------");
		System.out.println(response);
		Assert.assertTrue(response.contains("9001"));
	}
	
	@Test
	public void addValidPetFromJson2Test() throws FileNotFoundException
	{
		FileInputStream file=new FileInputStream("test-data/addpet.json");

		String resource="pet";
		
		String response=RestAssured
		.given().header("Content-Type", "application/json").body(file)
		.when().post(baseUrl+resource)
		.then().log().all().statusCode(200).extract().asPrettyString();
		
		System.out.println("------------------------------------------");
		System.out.println(response);
		
	}
	
	@Test
	public void updateValidPetFromJson3Test() throws FileNotFoundException, JsonMappingException, JsonProcessingException
	{
		FileInputStream file=new FileInputStream("test-data/addpet.json");

		String resource="pet";
		
		String response=RestAssured
		.given().header("Content-Type", "application/json").body(file)
		.when().put(baseUrl+resource)
		.then().log().all().statusCode(200).extract().asPrettyString();
		
		System.out.println("------------------------------------------");
		System.out.println(response);
		
		ObjectMapper mapper=new ObjectMapper();
		JsonNode actualJson= mapper.readTree(response);
		
		System.out.println(actualJson.get("id"));
		
	}
}
