package api2.session03;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class Test_PostMethod {

    @Test
    public void test03(){
        JSONObject jsonData=new JSONObject();
        jsonData.put("name","Prachi");
        jsonData.put("job","QA");
        RestAssured.baseURI="https://reqres.in";
        RestAssured.given().header("Content-type","application/json")
                .contentType(ContentType.JSON)
                .body(jsonData.toJSONString())
                .when().post("/api/users")
                .then().statusCode(201)
                .log().all();
    }

}
