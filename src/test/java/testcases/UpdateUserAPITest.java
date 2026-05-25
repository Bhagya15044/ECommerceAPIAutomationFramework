package testcases;

import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import models.User;

public class UpdateUserAPITest extends BaseTest
{
    @Test(priority = 2 , groups = {"regression"})
    public void UpdateUserTest()
    {

        User user = new User();
       user.setEmail("updated@gmail.com");
       user.setUsername("updateduser");
       user.setPassword("updated123");

        //configuring and storing the response from the server
        Response response = requestSpecification
                .body(user)
                .when()
                .put("/users/"+CreateUserAPITest.userId);

        //printing the server response
        response.prettyPrint();

        //validating the StatusCode
        response.then().statusCode(200);

        // we are doing deserialization, instead of manually extracting JSON fields
        User responseUser = response.as(User.class);

        System.out.println(responseUser.getUsername());
        System.out.println(responseUser.getEmail());

        //Validating deserialized response object fields
        Assert.assertEquals(responseUser.getUsername(), "updateduser");
        Assert.assertTrue(responseUser.getEmail().contains("@"));
    }
}
