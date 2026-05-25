
package testcases;
import base.BaseTest;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserWorkflowTest extends BaseTest
{
    public static int userId;

    @Test(priority = 1)
    public void CreateUserTest()
    {
        User user = new User();

        user.setEmail("test@gmail.com");
        user.setUsername("testuser");
        user.setPassword("test123");

        Response response = requestSpecification
                .body(user)
                .when()
                .post("/users");

        response.prettyPrint();

        response.then().statusCode(201);

        User responseUser = response.as(User.class);

        userId = responseUser.getId();

        System.out.println(userId);

       Assert.assertTrue(userId > 0);
        //Assert.assertTrue(false); just to check the failure like skip of remaining test cases
    }

    @Test(priority = 2, dependsOnMethods = "CreateUserTest")
    public void UpdateUserTest()
    {
        User user = new User();

        user.setEmail("updated@gmail.com");
        user.setUsername("updateduser");
        user.setPassword("updated123");

        Response response = requestSpecification
                .body(user)
                .when()
                .put("/users/" + userId);

        response.prettyPrint();

        response.then().statusCode(200);

        User responseUser = response.as(User.class);

        System.out.println(responseUser.getUsername());
        System.out.println(responseUser.getEmail());

        Assert.assertEquals(responseUser.getUsername(), "updateduser");
        Assert.assertTrue(responseUser.getEmail().contains("@"));
    }

    @Test(priority = 3, dependsOnMethods = "UpdateUserTest")
    public void DeleteUserTest()
    {
        Response response = requestSpecification
                .when()
                .delete("/users/" + userId);

        response.prettyPrint();

        response.then().statusCode(200);
    }
}