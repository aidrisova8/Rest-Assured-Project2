package api2.session21;

import api2.session20.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayPojoClassDemo {

    @Test
    public void createEmployeesJsonArray() throws JsonProcessingException {
        //create 1st employeeObject
        Employee emp1=new Employee();
        emp1.setFirstname("Jim");
        emp1.setLastname("Doe");
        emp1.setGender("male");
        emp1.setAge(25);
        emp1.setSalary(100000.20);

        Employee emp2=new Employee();
        emp2.setFirstname("Kim");
        emp2.setLastname("Yan");
        emp2.setGender("male");
        emp2.setAge(40);
        emp2.setSalary(35000.20);

        Employee emp3=new Employee();
        emp3.setFirstname("Jane");
        emp3.setLastname("Doe");
        emp3.setGender("female");
        emp3.setAge(68);
        emp3.setSalary(400000.00);

        //create list of employee
        List<Employee> listEmp=new ArrayList<>();
        listEmp.add(emp1);
        listEmp.add(emp2);
        listEmp.add(emp3);

        //Convert employee class objects to json array payload as String Deserialization
        ObjectMapper objectMapper=new ObjectMapper();
        String jsonArrayPayload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listEmp);
        System.out.println(jsonArrayPayload);

        //create Request
        Response response= RestAssured.given()
                .baseUri("https://httpbin.org/post")
                .contentType(ContentType.JSON)
                .body(jsonArrayPayload)
                .when().post();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
//convert Json Array to Employee class Objects Deserialization
        ResponseBody responseBody=response.getBody();

        JsonPath jsonPathView=responseBody.jsonPath();
        System.out.println("Employee objects in response body");
        List<Employee> allEmployees=jsonPathView.getList("json", Employee.class);
        for(Employee emp: allEmployees){
            System.out.println(emp.getFirstname()+" " +emp.getLastname());
        }


    }
}
