package testcases;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AuthAPITest extends BaseTest
{
     @Test
     public void generateTokenTest()
     {
         String requestPayload = "{\n" +
                 "\"username\":\"mor_2314\",\n" +
                 "\"password\":\"83r5^_\"\n" +
                 "}";

         Response response = requestSpecification
                 .body(requestPayload)
                 .when()
                 .post("/auth/login");

         response.prettyPrint();

         response.then().statusCode(201);

         token= response.jsonPath().getString("token");

         System.out.println(token);
         Assert.assertFalse(token.isEmpty());
     }

}
