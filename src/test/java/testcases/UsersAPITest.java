package testcases;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;


public class UsersAPITest extends BaseTest
{
    @Test
    public void validateUserDetailsTest()
    {

        Response value =  requestSpecification
                .when().get("/users");

        value.prettyPrint();

        value.then().statusCode(200);

        int UserID = value.jsonPath().getInt("[0].id");
        String username = value.jsonPath().getString("[0].username");
        String password = value.jsonPath().getString("[0].password");
        String email = value.jsonPath().getString("[0].email");

        //below is the Nested JSON extraction
        String firstname = value.jsonPath().getString("[0].name.firstname");
        String city = value.jsonPath().getString("[0].address.city");

        System.out.println(UserID);
        System.out.println(username);
        System.out.println(password);
        System.out.println(email);
        System.out.println(firstname);
        System.out.println(city);

       Assert.assertEquals(UserID,1);
       Assert.assertFalse(username.isEmpty());
       Assert.assertTrue(email.contains("@"));
       Assert.assertTrue(password.length()>5);
       Assert.assertFalse(firstname.isEmpty());
    }
}
