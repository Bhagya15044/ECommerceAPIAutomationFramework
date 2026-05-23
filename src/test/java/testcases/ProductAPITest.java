package testcases;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductAPITest extends BaseTest
{
   @Test
    public void getProductsTest()
   {
       

       Response response = requestSpecification
               .when()
               .get("/products");

       // Print Response Body
       response.prettyPrint();

       // Validate Status Code
       response.then().statusCode(200);

       // extracting the JSON Fields from the response
       int ProductID = response.jsonPath().getInt("[0].id");
       String ProductTitle = response.jsonPath().getString("[0].title");
       float ProductPrice = response.jsonPath().getFloat("[0].price");

       //printing the response extracted from JSON
       System.out.println(ProductID);
       System.out.println(ProductTitle);
       System.out.println(ProductPrice);

       //validating the response body extracted from the JSON
       Assert.assertEquals(ProductID, 1);
       Assert.assertFalse(ProductTitle.isEmpty());
   }
}
