package utils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigReader;

public class AuthUtility

{
    public static String generateToken()
    {
        //First prepare a payload
        String requestPayload ="{\n" +
                "\"username\":\"" + ConfigReader.getProperty("username") + "\",\n" +
                "\"password\":\"" + ConfigReader.getProperty("password") + "\"\n" +
                "}";
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when().post(ConfigReader.getProperty("base.url")+"/auth/login");

        String token = response.jsonPath().getString("token");
        return token; // instead printing the token we are returning
    }
}

 /*
        RESPONSIBILITY OF AuthUtility
Soon it will:
✅ generate token
✅ return token
✅ centralize auth logic
✅ avoid auth duplication
✅ support token refresh later
This is:
utility/service layer behavior

WHY STATIC METHOD?
Because:
utility methods usually:
do not require object creation

utility class is:

independent reusable service layer
     */