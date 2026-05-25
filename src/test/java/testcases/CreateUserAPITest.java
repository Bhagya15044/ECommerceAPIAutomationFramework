package testcases;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import models.User;

public class CreateUserAPITest extends BaseTest
{
    public static int userId;

    @Test(priority = 1,groups = {"smoke", "regression"}, dataProvider = ("userData"))
    public void CreateUserTest(String email, String username, String password)
    {

        User user = new User();

        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        // Send Post-Request
        Response response = requestSpecification
                .body(user)
                .when()
                .post("/users");

        // Printing the response
        response.prettyPrint();

        // Validating status code
        response.then().statusCode(201);

        // Deserializing response JSON into Java object
        User responseUser = response.as(User.class);

        // Storing runtime-generated ID for API chaining
        userId = responseUser.getId();

        // Printing generated ID
        System.out.println(userId);

        // Validating generated ID
        Assert.assertTrue(userId > 0);
    }

    @DataProvider(name = "userData")
    public  Object[][] userData()
    {
        return new Object[][]
                {
                        {"test1@gmail.com", "user1", "pass1"},
                        {"test2@gmail.com", "user2", "pass2"},
                        {"test3@gmail.com", "user3", "pass3"}
                };
    }
}
/*
      After getting the response from the server, first thing I inspected the response structure
      and printed using the response.prettyPrint(); so this will give currently what is field present in the response structure
      Then I extracted the field id because currently this one is present on rs and

      Why I did this because to avoid test failures, scalability, avoid unnecessary lines of coding

 * Important understanding of payload flow:

This:
User user = new User();

creates:
runtime payload object

Then:
user.setEmail(...)
fills payload data dynamically.

Then:
.body(user)
serializes object → JSON automatically.

 */