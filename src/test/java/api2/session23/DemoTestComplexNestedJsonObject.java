package api2.session23;

import api2.session22.EmployeeAddress;
import api2.session22.EmployeePojoClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class DemoTestComplexNestedJsonObject {
    @Test
    public void createUser() throws JsonProcessingException {
//create request payload
      NestedJsonPojoClass requestPayload=new NestedJsonPojoClass();
      requestPayload.setCompanyName("XYZ LTD");
      requestPayload.setCity("Phila");
      requestPayload.setStreet("Sycomore Str");
      requestPayload.setPinCode(95600);

        List<String> banks=new ArrayList<>();
        banks.add("HDFC");
        banks.add("SBI");
        banks.add("AXIS");
      requestPayload.setBankAccount(banks);

        EmployeePojoClass emp1=new EmployeePojoClass();
        emp1.setFirstname("Jim");
        emp1.setLastname("Doe");
        emp1.setGender("male");
        emp1.setAge(25);
        emp1.setSalary(100000.20);
        EmployeeAddress emp1address=new EmployeeAddress();
        emp1address.setStreet("Park Avenue");
        emp1address.setCity("Dayton");
        emp1address.setState("OH");
        emp1address.setPinCode(49550);
        emp1.setAddress(emp1address);

        EmployeePojoClass emp2=new EmployeePojoClass();
        emp2.setFirstname("Kim");
        emp2.setLastname("Yan");
        emp2.setGender("male");
        emp2.setAge(40);
        emp2.setSalary(35000.20);
        EmployeeAddress emp2address=new EmployeeAddress();
        emp2address.setStreet("Main Avenue");
        emp2address.setCity("Phila");
        emp2address.setState("PA");
        emp2address.setPinCode(90000);
        emp2.setAddress(emp2address);

        List<EmployeePojoClass> employees=new ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);
      requestPayload.setEmployeeList(employees);

      //convert class object to Json object as String
        ObjectMapper objectMapper=new ObjectMapper();
        String payload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestPayload);
        System.out.println(payload);
        Response response= RestAssured.given()
                .baseUri("https://httpbin.org/post")
                .contentType(ContentType.JSON)
                .body(payload)
                .when().post();
        response.then().log().all().statusCode(200);



    }
}
