package api2.session17;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class JsonObjectUsingJacksonApi {

    @Test
    public void CreateUser(){
          /*
        "firstname":"Amod",
        "lastname":"Mahajan",
        "age":28,
        "salary":10000.56,
        "IsMarried": true,
        "Hobbies":["Music","Cooking"],
        "TechSkill":{"Programming language":"Java",
                      "WebAutomation":"Selenium",
                      "API Testing":"Rest Assured"}
         */

        //Create objectMapper class Instance
        ObjectMapper objectMapper=new ObjectMapper();

        //create object node i.e. json node
        ObjectNode userDetails=objectMapper.createObjectNode();
        userDetails.put("firstname","Amod");
        userDetails.put( "lastname","Mahajan");
        userDetails.put( "age",28);
        userDetails.put( "salary",10000.56);
        userDetails.put( "IsMarried",true);
        userDetails.set("Hobbies",objectMapper.convertValue(Arrays.asList("Cooking","music"),JsonNode.class));

        ObjectNode techSkill=objectMapper.createObjectNode();
        techSkill.put("Programming language","Java");
        techSkill.put("WebAutomation","Selenium");
        techSkill.put("API Testing","Rest Assured");
        userDetails.set("TechSkill",techSkill);

        //print user details json object
        try {
            String userDetailsAsString=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDetails);
            System.out.println(userDetailsAsString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //Retrieve field value
      String firstName=  userDetails.get("firstname").asText();
      int age= userDetails.get("age").asInt();
      String webAutomation=userDetails.get("TechSkill").get("WebAutomation").asText();
        System.out.println(firstName);
        System.out.println(age);
        System.out.println(webAutomation);

        System.out.println("Print all fields name:\n");
        Iterator<String> fieldNameIterator= userDetails.fieldNames();
        while (fieldNameIterator.hasNext()){
            System.out.println(fieldNameIterator.next());
        }

        System.out.println("Print all fields values:\n");
        Iterator<JsonNode> fieldValueIterator= userDetails.elements();
        while (fieldValueIterator.hasNext()){
            System.out.println(fieldValueIterator.next());
        }

        System.out.println("Print all fields name and value both:\n");
        Iterator<Map.Entry<String,JsonNode>> keyValueEntries= userDetails.fields();
        while (keyValueEntries.hasNext()){
       Map.Entry<String,JsonNode> node= keyValueEntries.next();
            System.out.println(node.getKey()+" : "+node.getValue());

        }

        //Remove field "firstName" from Json Object
       String removedValue= userDetails.remove("firstname").asText();
        System.out.println(removedValue);
        System.out.println("Print all fields name and value both after removing:\n");
        Iterator<Map.Entry<String,JsonNode>> keyValueEntries1= userDetails.fields();
        while (keyValueEntries1.hasNext()){
            Map.Entry<String,JsonNode> node1= keyValueEntries1.next();
            System.out.println(node1.getKey()+" : "+node1.getValue());

        }

        //update json object
        userDetails.put( "lastname","Doe");
        System.out.println("Last name after updating: "+userDetails.get("lastname").asText());

        techSkill.put("Programming language","C++");
        userDetails.set("TechSkill",techSkill);
        System.out.println("Programming language after updating: "+userDetails.get("TechSkill").get("Programming language").asText());

        Response response= RestAssured.given()
                .baseUri("https://reqres.in/api/users")
                .contentType(ContentType.JSON)
                .body(userDetails)
                .when().post();
               response.then().log().all().statusCode(201);
    }
}
