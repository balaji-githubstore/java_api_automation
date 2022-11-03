package com.microfocus.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class DeletePetTest {

public static String baseUrl = "https://petstore.swagger.io/v2/";
	
	@Test
	public void deleteValidPetTest()
	{
		int petId=9001;
		String resource="pet/"+petId;
		
		
		String response=RestAssured
		.given()
		.when().delete(baseUrl+resource)
		.then().log().all().statusCode(200).extract().asPrettyString();
		
		System.out.println("------------------------------------------");
		System.out.println(response);
	}
	
	@Test
	public void deleteInvalidPetTest()
	{
		int petId=90017887;
		String resource="pet/"+petId;
		
		
		String response=RestAssured
		.given()
		.when().delete(baseUrl+resource)
		.then().log().all().statusCode(404).extract().asPrettyString();
		
		System.out.println("------------------------------------------");
		System.out.println(response);
	}
}
