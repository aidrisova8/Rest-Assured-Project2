package api2.session05;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateResponseHeader {
    @Test
    public void getSingleUser(){
        RequestSpecification requestSpec= RestAssured.given();
        //specify base URI
        requestSpec.baseUri("https://reqres.in");
        requestSpec.basePath("/api/users/2");

       Response response= requestSpec.get();

       //validate response header
    String contentType=response.getHeader("Content-Type");
        System.out.println("Value of content type: "+contentType);

        String connection=response.getHeader("Connection");
        System.out.println("Value of connection type: "+connection);

        //read all response header attributes/keys and print their values/ list of headers
        Headers headersList= response.getHeaders();
        //iterate over header list
        for(Header header: headersList){
            System.out.println("Key: "+header.getName()+" Value: "+header.getValue());
        }

        //validate content - type
        Assert.assertEquals(contentType,"application/json; charset=utf-8","Header content type mismatch");

    }

}
