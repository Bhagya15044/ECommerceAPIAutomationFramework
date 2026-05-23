package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import utils.AuthUtility;
import utils.ConfigReader;

public class BaseTest
{
    // Reusable RequestSpecification object
    public RequestSpecification requestSpecification ;

    // Centralized shared token variable
    public static String token;
    @BeforeClass
    public void setup()
    {
        //BaseURI configuration
        RestAssured.baseURI = ConfigReader.getProperty("base.url");

        // RequestSpecification initialization
        requestSpecification = RestAssured.given();

        // Common request configuration
        requestSpecification.contentType(ContentType.JSON);

        token = AuthUtility.generateToken();

        // Dynamic Authorization header injection
        requestSpecification.header("Authorization", "Bearer " + token);
    }
}
