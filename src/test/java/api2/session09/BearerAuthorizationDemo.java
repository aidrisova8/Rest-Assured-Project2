package api2.session09;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BearerAuthorizationDemo {

    @Test
    public  void bearerToken(){
        // create request specification

        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("https://gorest.co.in");
        reqSpec.basePath("/public/v2/users");

        JSONObject payload=new JSONObject();
        payload.put("name","Dia");
        payload.put("gender","Female");
        payload.put("email","Dia@gmail.com");
        payload.put("status","active");
        String AuthToken="Bearer 61ced03707d99a23bcd41dfb5b9c88828b211eadc9b62656945c0d24e55e7ae1";

        reqSpec.headers("Authorization",AuthToken)
                .contentType(ContentType.JSON)
                .body(payload.toJSONString());
        Response response =reqSpec.post();

        Assert.assertEquals(response.statusCode(),201,"Check for status code");
        System.out.println("Digest Response status: "+response.statusLine());
        System.out.println("Digest Response body: "+response.body().asString());



    }
}
