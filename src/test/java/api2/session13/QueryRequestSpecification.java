package api2.session13;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class QueryRequestSpecification {

    @Test
    public void createUser(){
        //create json data
        JSONObject jsonData=new JSONObject();
        jsonData.put("name","Kami");
        jsonData.put("job","QA");

        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .body(jsonData.toJSONString())
                .header("Header1","header value");

        //query details from request specification

     QueryableRequestSpecification queryRequest= SpecificationQuerier.query(reqSpec);

     //get base URI and base path,body,headers
        String retrieveBaseUri=queryRequest.getBaseUri();
        String retrieveBasePath=queryRequest.getBasePath();
        String retrieveBody=queryRequest.getBody();
        Headers retrieveAllHeaders=queryRequest.getHeaders();
        for ( Header header: retrieveAllHeaders) {
            System.out.println(header.getName());
            System.out.println(header.getValue());
        }
        System.out.println(retrieveBaseUri);
        System.out.println(retrieveBasePath);
        System.out.println(retrieveBody);




    }
}
