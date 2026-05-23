package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserAPITest extends BaseTest {
    @Test
    public void UpdateUserTest() {


        //preparing the payload
        String requestPayload = "{\n" +
                "\"email\":\"updated@gmail.com\",\n" +
                "\"username\":\"updateduser\",\n" +
                "\"password\":\"updated123\"\n" +
                "}";

        //configuring and storing the response from the server
        Response response = requestSpecification
                .body(requestPayload)
                .when()
                .put("/users/1");

        //printing the server response
        response.prettyPrint();

        //validating the StatusCode
        response.then().statusCode(200);

        //Extraction server response field using jsonpath
       // int updateID = response.jsonPath().getInt("id"); ,
        // because here id does contain so test failed , but server is returned email, username, password

        String username = response.jsonPath().getString("username");
        String email = response.jsonPath().getString("email");

        //System.out.println(updateID);
        System.out.println(username);
        System.out.println(email);

        //validating the extracted JSON field using TestNG Assertions
       // Assert.assertEquals(updateID, 1);
        Assert.assertEquals(username, "updateduser");
        Assert.assertTrue(email.contains("@"));
    }
}
