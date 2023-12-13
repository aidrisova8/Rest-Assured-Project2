package api2.session15;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class JsonObjectUsingJavaMapDemo {

    @Test
    public void createToken(){

        /*  create json object
        {
    "username" : "admin",
    "password" : "password123"
}    */
       Map<String,String> authToken=new HashMap<>();
       authToken.put("username","admin");
       authToken.put("password","password123");

   Response response= RestAssured.given()
               .baseUri("https://restful-booker.herokuapp.com")
               .contentType(ContentType.JSON)
               .body(authToken)
               .when().post("/auth");
   response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);


    }
    @Test
    public void createUser(){
        /*
        "firstname":"Amod",
        "lastname":"Mahajan",
        "age":28,
        "salary":10000.56,
        "IsMarried": true,
        "Hobbies":["Music","Computer","Games"],
        "TechSkill":{"Programming language":"Java",
                      "WebAutomation":"Selenium",
                      "API Testing":"Rest Assured"}
         */

        HashMap<String, Object> userData=new HashMap<>();
        userData.put( "firstname","Amod");
        userData.put( "lastname","Mahajan");
        userData.put( "age",28);
        userData.put( "salary",10000.56);
        userData.put( "IsMarried",true);

        ArrayList<String> hobbies=new ArrayList<>();
        hobbies.add("Music");
        hobbies.add("Computers");
        hobbies.add("Games");
        userData.put("Hobbies",hobbies);

        HashMap<String, String> techSkills=new HashMap<>();
        techSkills.put("Programming language","Java");
        techSkills.put("WebAutomation","Selenium");
        techSkills.put("API Testing","Rest Assured");
        userData.put("TechSkills",techSkills);

        Response response= RestAssured.given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .body(userData)
                .when().post();
        response.then().log().all();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),201);

    }
}
