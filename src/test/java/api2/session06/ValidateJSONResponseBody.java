package api2.session06;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateJSONResponseBody {
    //https://www.youtube.com/watch?v=lAEOrXAdWak&list=PLnNg6KqJ3HGjVFcRxr4QaRn7fHe2k8tNQ&index=6

   @Test
   public void userListResponseBody(){
       //get request specification reference
       RequestSpecification reqSpec= RestAssured.given();

       // specify base uri & base path
       reqSpec.baseUri("https://reqres.in");
       reqSpec.basePath("/api/users?page=2");

       //create get request
       Response response= reqSpec.get();

       //read response body
       ResponseBody  responseBody= response.getBody();

       String responseString=responseBody.asString();
       System.out.println("Response body: "+responseString);

       //check for presence of George in response body
       Assert.assertTrue(responseString.contains("George"));

   //use jsonpath finder https://jsonpathfinder.com/, get json path view of response body

       JsonPath jsonPathView=responseBody.jsonPath();

       //x.data[4].first_name
       String firstName= jsonPathView.get("data[0].first_name");
       System.out.println(firstName);
       Assert.assertEquals(firstName,"George");



   }
}
