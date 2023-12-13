package api2.session22;

import api2.session20.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DemoNestedJsonPayload {
    @Test
    public void createUser() throws JsonProcessingException {
        EmployeePojoClass emp1=new EmployeePojoClass();
        emp1.setFirstname("John");
        emp1.setLastname("Doe");
        emp1.setGender("male");
        emp1.setAge(75);
        emp1.setSalary(100000.20);

        EmployeeAddress emp1address=new EmployeeAddress();
        emp1address.setStreet("Park Avenue");
        emp1address.setCity("Dayton");
        emp1address.setState("OH");
        emp1address.setPinCode(49550);
        emp1.setAddress(emp1address);

        //convert class object to json object as string
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonPayload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
        System.out.println(jsonPayload);

        Response response= RestAssured.given()
                .baseUri("https://httpbin.org/post")
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when().post();
        response.then().log().all().statusCode(200);
        EmployeePojoClass employeePojo2 = objectMapper.readValue(jsonPayload, EmployeePojoClass.class);

        System.out.println("First Name  " + employeePojo2.getFirstname());
        System.out.println("City  " + employeePojo2.getAddress().getCity());
    }
}
