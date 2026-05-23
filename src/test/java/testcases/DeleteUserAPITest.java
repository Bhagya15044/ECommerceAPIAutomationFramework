package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserAPITest extends BaseTest
{
    @Test
    public void DeleteUserTest()
    {

        Response response = requestSpecification
                .when()
                .delete("/users/1");

        response.prettyPrint();

        response.then().statusCode(200);

        int id = response.jsonPath().getInt("id");
        String username = response.jsonPath().getString("username");

        System.out.println(username);

        Assert.assertEquals(id,1);
        Assert.assertEquals(username,"johnd");

    }
}
