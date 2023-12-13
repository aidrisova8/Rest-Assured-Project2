package api2.session12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeserializationDemo {
   @Test
    public void createUser(){
       RequestSpecification reqSpec= RestAssured.given();
       reqSpec.baseUri("https://reqres.in");
       reqSpec.basePath("/api/users");

       // create request body
       JSONObject jsonData=new JSONObject();
       jsonData.put("name","Kamila");
       jsonData.put("job","SDET");

       //perform post request
    Response response= reqSpec
               .contentType(ContentType.JSON)
               .body(jsonData.toJSONString())
               .post();
       response.then().log().all().statusCode(201);
       ResponseBody responseBody=response.getBody();

       //deserialize responsebody i.e json response body to clas object
       JsonPostRequestResponse responseClass=responseBody.as(JsonPostRequestResponse.class);
       Assert.assertEquals(responseClass.name,"Kamila");
       Assert.assertEquals(responseClass.job,"SDET");


    }

}
