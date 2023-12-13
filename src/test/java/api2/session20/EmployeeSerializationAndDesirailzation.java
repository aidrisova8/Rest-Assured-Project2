package api2.session20;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class EmployeeSerializationAndDesirailzation {

    @Test
    public void createJsonObjectFromEmployeeClassObject() throws JsonProcessingException {
      Employee emp1=new Employee();
      emp1.setFirstname("Jim");
      emp1.setLastname("Doe");
      emp1.setGender("male");
      emp1.setAge(25);
      emp1.setSalary(100000.20);

      //Convert employee class object to Json  payload as String
        ObjectMapper objMapper=new ObjectMapper();
        String employeeJSON=objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
        System.out.println(employeeJSON);

        Response response= RestAssured.given()
                .baseUri("https://httpbin.org/post")
                .contentType(ContentType.JSON)
                .body(employeeJSON)
                .post();
        response.then().log().all().statusCode(200);

        //convert JSOn String(employeeJson) to class object
        Employee emp2=objMapper.readValue(employeeJSON, Employee.class);
        System.out.println("-------------Print after Json Object to Class Object--------------------");
        System.out.println(emp2.getFirstname());

    }
}
