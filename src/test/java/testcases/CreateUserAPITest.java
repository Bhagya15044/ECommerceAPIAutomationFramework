package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserAPITest extends BaseTest
{
    @Test
    public void CreateUserTest()
    {


        //Request Payload
        String Payload = "{\n" +
                "\"email\":\"test@gmail.com\",\n" +
                "\"username\":\"testuser\",\n" +
                "\"password\":\"test123\"\n" +
                "}";

        //Send Post-Request
        Response response = requestSpecification // inside of content type writing manually we replaced with this
                .body(Payload)
                .when().post("/users");

        // Printing the response
        response.prettyPrint();

        //validating the response
        response.then().statusCode(201);

        //extracting the response fields
//        String username = response.jsonPath().getString("username");
//        String email = response.jsonPath().getString("email");
        int id = response.jsonPath().getInt("id");



        //printing the extracted JSON fields
//        System.out.println(username);
//        System.out.println(email);

        Assert.assertTrue(id > 0);
//        Assert.assertEquals(username, "testuser");
//        Assert.assertTrue(email.contains("@"));
    }
}
