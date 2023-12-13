package api2.session30;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

public class DemoJsonSchemaValidation {
   @Test
    public void TestMethod(){
        String requestPayload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
       Response response=RestAssured.given()
               .baseUri("https://restful-booker.herokuapp.com/auth")
               .contentType(ContentType.JSON)
               .body(requestPayload)
               .when().post();
       response.then().log().all().assertThat().statusCode(200)
               .body("token", Matchers.notNullValue())
               .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema1.json"));
//               .body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\aydi1\\Desktop")));
    }
}
