package api2.session03;

import static io.restassured.RestAssured.*; //important make static if doesn't want type RESTAssured
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;

public class Test_GetMethod {

    @Test
    public void test01(){
        Response res = get("https://reqres.in/api/users?page=2");
        System.out.println("Response code " + res.statusCode());
        System.out.println("Response body " + res.getBody().asString());
        System.out.println("Response time " + res.getTime());
        System.out.println("Response header " + res.getHeader("Content-Type"));
        //validate status code
        //expected status code 200
        int expectedStatusCode=200;
        int actualStatusCode= res.statusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
    }
    @Test
    public  void test02(){
      //given,when, then
       baseURI="https://reqres.in/api/users";
       given().queryParam("page","2")
              .when().get()
              .then().statusCode(200);
    }
}
