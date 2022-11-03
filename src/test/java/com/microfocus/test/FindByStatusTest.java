package com.microfocus.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
/*
 * Get request with query param
 */
public class FindByStatusTest {
	public static String baseUrl = "https://petstore.swagger.io/v2/";
	
	
	@Test
	public void validateFindPetByStatus() {

		String resource = "pet/findByStatus";

		String response = RestAssured
				.given().queryParam("status", "pending")
				.when().get(baseUrl + resource)
				.then().log().all().statusCode(200).extract().asPrettyString();
		
		System.out.println(response);
	}
}
