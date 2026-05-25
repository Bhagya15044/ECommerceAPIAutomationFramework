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
    @BeforeClass(alwaysRun = true)
    // why alwaysRun = true added means sometimes group executions like smoke,sanity, regression runs.
    // when testng runs the selected groups configurations like @Beforeclass, @Beforemethod will not execute automatically unless we add alwaysRun = True
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
// Why alwaysRun = true is added:
//
// Sometimes during group executions like:
// smoke, sanity, regression
//
// TestNG runs only selected grouped test methods.
//
// During this process, configuration methods like:
// @BeforeClass
// @BeforeMethod
// @AfterClass
//
// may not execute automatically.
//
// To ensure setup/configuration methods execute
// regardless of group filtering,
// we use:
//
// @BeforeClass(alwaysRun = true)
//
// This guarantees framework initialization happens properly
// before grouped test execution.