package api2.session08;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Authorization {

    @Test
    public void BasicAuth(){
        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("https://postman-echo.com");
        reqSpec.basePath("/basic-auth");
      Response response= reqSpec.auth().basic("postman","password").get();
        System.out.println("Response status: "+response.statusLine());
        System.out.println("Response body: "+response.body().asString());
    }

    //http://httpbin.org/digest-auth/undefined/aigul/aigul
    @Test
    public void DigestAuth(){
        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("http://httpbin.org");
        reqSpec.basePath("/digest-auth/undefined/aigul/aigul");
       // Response response= reqSpec.get(); //result will be 401 UNAUTHORIZED
        Response response= reqSpec.auth().digest("aigul","aigul").get();//result will be 200 because we mentioned digest

        Assert.assertEquals(response.statusCode(),200,"Check for status code");
        System.out.println("Digest Response status: "+response.statusLine());
        System.out.println("Digest Response body: "+response.body().asString());
    }
}
