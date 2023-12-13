package api2.session03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class Test_PatchMethod {

    @Test
    public void test04(){
        JSONObject jsonData=new JSONObject();
        jsonData.put("name","Donna");
        jsonData.put("job","Plumber");

        RestAssured.baseURI="https://reqres.in";
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonData.toJSONString())
                .when().patch("/api/users/303")
                .then().statusCode(200).log().all();
    }
}
