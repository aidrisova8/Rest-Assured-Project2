package api2.session03;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Test_DeleteMethod {

    @Test
    public void test06(){
        RestAssured.baseURI="https://reqres.in";
        RestAssured.given()
                .when().delete("/api/users/303")
                .then().statusCode(204).log().all();
    }
}
