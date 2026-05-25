package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserAPITest extends BaseTest
{
    @Test(priority = 3 , groups = {"regression"})
    public void DeleteUserTest()
    {

        Response response = requestSpecification
                .when()
                .delete("/users/" + CreateUserAPITest.userId);

        response.prettyPrint();

        response.then().statusCode(200);


    }
}
