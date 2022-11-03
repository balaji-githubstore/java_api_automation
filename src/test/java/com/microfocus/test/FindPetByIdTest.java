package com.microfocus.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
/*
 * Get request with path parameter
 */
public class FindPetByIdTest {

	public static String baseUrl = "https://petstore.swagger.io/v2/";

	@Test
	public void validStatusCodeFindPetById() {

		int petId = 9001;
		String resource = "pet/" + petId;

		RestAssured.given().when().get(baseUrl + resource).then().log().all().statusCode(200);
	}

	@Test
	public void invalidStatusCodeFindPetById() {

		int petId = 800;
		String resource = "pet/" + petId;
		RestAssured.given().when().get(baseUrl + resource).then().statusCode(404);
	}

	@Test
	public void validResponseFindPetById() {

		int petId = 5001;
		String resource = "pet/" + petId;

		String response = RestAssured
				.given()
				.when().get(baseUrl + resource)
				.then().log().all().statusCode(200).extract().asPrettyString();

		//System.out.println(response);

		Assert.assertTrue(response.contains("doggie")); // expect true
		Assert.assertTrue(response.contains("5001"));
		Assert.assertTrue(response.contains("available"));
		
//		String expectedJson="{\r\n"
//				+ "    \"id\": 5001,\r\n"
//				+ "    \"category\": {\r\n"
//				+ "        \"id\": 0,\r\n"
//				+ "        \"name\": \"string\"\r\n"
//				+ "    },\r\n"
//				+ "    \"name\": \"doggie\",\r\n"
//				+ "    \"photoUrls\": [\r\n"
//				+ "        \"string\"\r\n"
//				+ "    ],\r\n"
//				+ "    \"tags\": [\r\n"
//				+ "        {\r\n"
//				+ "            \"id\": 0,\r\n"
//				+ "            \"name\": \"string\"\r\n"
//				+ "        }\r\n"
//				+ "    ],\r\n"
//				+ "    \"status\": \"available\"\r\n"
//				+ "}";
//		
//		Assert.assertEquals(response, expectedJson);
	}

}
