package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReqresNoPojoTest {
    private final static String URL = "https://reqres.in/";
   @Test
   public void CheckAvatarsNoPojoTest(){
       Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOK200());
       Response response =given()
               .when()
               .get("api/users?page=2")
               .then().log().all()
               .body("page", Matchers.equalTo(2))
               .body("data.id",Matchers.notNullValue())
               .body("data.email",Matchers.notNullValue())
               .body("data.first_name",Matchers.notNullValue())
               .body("data.last_namel",Matchers.notNullValue())
               .body("data.avatar",Matchers.notNullValue())
               .extract().response();
       JsonPath jsonPath=response.jsonPath();
        List<String> emails =jsonPath.getList("data.email");
       List<String> ids =jsonPath.getList("data.id");
       List<String> avatars =jsonPath.getList("data.avatar");

       for(int i=0;i< avatars.size();i++){
           Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
           System.out.println(avatars.get(i));
       }
       Assert.assertTrue(emails.stream().allMatch(x->x.endsWith("@reqres.in")));
   }
   @Test
    public void successRegTestNoPojo(){
       Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecOK200());
       Map<String,String> user=new HashMap<>();
       user.put("email", "eve.holt@reqres.in");
       user.put("password","pistol");
       Response response= given()
               .body(user)
               .when()
               .post("api/register")
               .then().log().all()
              // .body("id",Matchers.equalTo(4))
            //   .body("token",Matchers.equalTo("QpwL5tke4Pnpja7X4"))
               .extract().response();
       JsonPath jsonPath =response.jsonPath();
       int id=jsonPath.get("id");
       String token = jsonPath.get("token");

       Assert.assertEquals(4,id);
       Assert.assertEquals("QpwL5tke4Pnpja7X4",token);

   }

   @Test
   public void unSuccessRegTest(){
       Specifications.installSpecification(Specifications.requestSpec(URL),Specifications.responseSpecError400());
       Map<String,String> user = new HashMap<>();
       user.put("email","sydney@fife");
    Response response=   given()
               .body(user)
               .when()
               .post("api/register")
               .then().log().all()
               .extract().response();
    JsonPath jsonPath=response.jsonPath();
    String error=jsonPath.get("error");
    Assert.assertEquals("Missing password",error);
   }
}
