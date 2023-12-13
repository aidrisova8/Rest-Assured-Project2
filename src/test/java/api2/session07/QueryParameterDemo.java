package api2.session07;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class QueryParameterDemo {

    @Test
    public void filterData(){
        //Get request specification for the given request
        RequestSpecification reqSpec= RestAssured.given();

        //        //specify   uri
        reqSpec.baseUri("https://reqres.in");
        reqSpec.basePath("/api/users");
        reqSpec.queryParam("page",2).queryParam("id",10);

        //perform get request
        Response response=reqSpec.get();
       String responseBodyString= response.getBody().asString();
        System.out.println("Response body: "+responseBodyString);

        //get json path view of response body
        JsonPath jsonPathView=response.jsonPath();


        //get first name
       String firstName= jsonPathView.get("data.first_name");
        System.out.println(firstName);
        Assert.assertEquals(firstName,"Byron");
    }
}
