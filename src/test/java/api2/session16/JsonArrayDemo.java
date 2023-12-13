package api2.session16;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonArrayDemo {
    @Test
    public void createUserUsingJsonArray(){
       /* create Json Array request body using Json Object
     [  {"firstname":"John",
        "lastname":"Doe",
        "age":28,
        "salary":10000.56}
         {"firstname":"Jane",
        "lastname":"Doe",
        "age":28,
        "salary":10000.56}
         {"firstname":"Kim",
        "lastname":"Yan",
        "age":28,
        "salary":10000.56} ]
        */

        //create Json objects for users
        JSONObject user1=new JSONObject();
        user1.put("firstname","John");
        user1.put("lastname","Doe");
        user1.put("age",28);
        user1.put("salary",10000.56);


        JSONObject user2=new JSONObject();
        user2.put("firstname","Jane");
        user2.put("lastname","Doe");
        user2.put("age",28);
        user2.put("salary",10000.56);


        JSONObject user3=new JSONObject();
        user3.put("firstname","Kim");
        user3.put("lastname","Yan");
        user3.put("age",28);
        user3.put("salary",10000.56);


        //add json object to json array
        JSONArray userPayload=new JSONArray();
        userPayload.add(user1);
        userPayload.add(user2);
        userPayload.add(user3);

        RequestSpecification reqSpec= RestAssured.given();
    Response response= reqSpec.baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .body(userPayload)
                .when().post();
    response.then().log().all().statusCode(201);
    }

    @Test
    public void createJsonArrayUsingList(){
     //create Json Array request body using List


        //create Json objects for users using Map
        Map<String,Object> user1=new HashMap<>();
        user1.put("firstname","John");
        user1.put("lastname","Doe");
        user1.put("age",28);
        user1.put("salary",10000.56);


        Map<String,Object> user2=new HashMap<>();
        user2.put("firstname","Jane");
        user2.put("lastname","Doe");
        user2.put("age",28);
        user2.put("salary",10000.56);


        Map<String,Object> user3=new HashMap<>();
        user3.put("firstname","Kim");
        user3.put("lastname","Yan");
        user3.put("age",28);
        user3.put("salary",10000.56);


        //add json Array using List
        List<Map<String,Object>> userPayload=new ArrayList<>();
        userPayload.add(user1);
        userPayload.add(user2);
        userPayload.add(user3);

        RequestSpecification reqSpec= RestAssured.given();
        Response response= reqSpec.baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .body(userPayload)
                .when().post();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),201);
    }

}
