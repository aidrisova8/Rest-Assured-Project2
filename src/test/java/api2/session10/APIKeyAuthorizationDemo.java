package api2.session10;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APIKeyAuthorizationDemo {

    //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    //https://home.openweathermap.org/   to get an API key
@Test
    public void  getWeatherDataByCity(){

    RequestSpecification reqSpec= RestAssured.given();
    reqSpec.baseUri("https://api.openweathermap.org");
    reqSpec.basePath("/data/2.5/weather");
    reqSpec.queryParam("q","delhi").queryParam("appid","a022ddcb6a108e11398d590c5e3b646f");
   Response response= reqSpec.get();
           response.then().log().all();

    Assert.assertEquals(response.statusCode(),200,"Check for status code");
    System.out.println("Digest Response status: "+response.statusLine());
    System.out.println("Digest Response body: "+response.body().asString());
    }
}
