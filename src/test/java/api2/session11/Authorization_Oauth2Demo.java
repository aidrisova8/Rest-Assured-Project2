package api2.session11;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Authorization_Oauth2Demo {
//www.developer.paypal.com
static String accessToken="";
    @Test
    public void GetOauth2AccessToken(){
        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("https://api-m.sandbox.paypal.com");
        reqSpec.basePath("/v1/oauth2/token");
String clientId="Go to paypal";
String clientSecret="go to paypal";


      Response response= reqSpec.auth().preemptive().basic(clientId,clientSecret).param("grant_type","client_credentials").post();
      response.prettyPrint();
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());

        Assert.assertEquals(response.statusCode(),200);

        //get access token from response body

     accessToken=   response.getBody().path("access_token");
        System.out.println(accessToken);


    }
  @Test(dependsOnMethods = "GetOauth2AccessToken")
    public void listInvoice(){
Response res= RestAssured.given().auth().oauth2(accessToken)
         .queryParam("page","3")
         .queryParam("page_size","4")
         .queryParam("total_count_required","true")
         .get("https://api-m.sandbox.paypal.com/v1/invoicing/invoices");
res.prettyPrint();
      System.out.println(res.statusCode());
      System.out.println(res.statusLine());

      Assert.assertEquals(res.statusCode(),200);
    }
}
