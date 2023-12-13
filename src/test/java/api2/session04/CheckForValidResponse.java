package api2.session04;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckForValidResponse {
    //https://reqres.in/api/users/2
    @Test(enabled = false)
    public void getSingleUser() {
        //specify base URL
       baseURI = "https://reqres.in/api/users/2";

        // Get Request specification of the request
        RequestSpecification requestSpecification =  given();

        //call get method
        Response resp = requestSpecification.get();

        //get response code
        int actualStatusCode = resp.getStatusCode();

        //validate actual status code with expected
        Assert.assertEquals(actualStatusCode, 200, "Incorrect status code received");

        String actualStatusLine= resp.getStatusLine();
        Assert.assertEquals(actualStatusLine,"HTTP/1.1 200 OK");
        System.out.println("Test 1");
    }

    @Test
    public void getSingleUserUsingValidatableResponse() {
        //specify base URL
         baseURI = "https://reqres.in/api/users/2";

        // Get Request specification of the request
        RequestSpecification requestSpecification =  given();

        //call get method
        Response resp = requestSpecification.get();

       ValidatableResponse validateRes= resp.then();

       //status code
        validateRes.statusCode(200); //if you pass here 201 , line 49 will  not be executed
        System.out.println("Test 2");
        //status line
        validateRes.statusLine("HTTP/1.1 200 OK");


    }

    @Test()
    public void getSingleUserUsingBDDMethod() {
              given()
              .when()
                      .get("https://reqres.in/api/users/2")
              .then().statusCode(200)
                      .statusLine("HTTP/1.1 200 OK");

    }
}
