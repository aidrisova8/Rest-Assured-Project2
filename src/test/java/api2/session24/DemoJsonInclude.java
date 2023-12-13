package api2.session24;

import api2.session22.EmployeePojoClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoJsonInclude {

    @Test
    public void testMethod1() throws JsonProcessingException {
        //create payload
        EmployeePojoClassDefaultValues emp1=new EmployeePojoClassDefaultValues();
        emp1.setFirstname("Jim");
      //  emp1.setLastname("Doe");
        emp1.setGender("male");
        emp1.setAge(25);
        emp1.setSalary(100000.20);
      //  emp1.setMarried(true);
        String[] hobbies={};
        emp1.setHobbies(hobbies);

        List<String> degree=new ArrayList<>();
        emp1.setDegrees(degree);

        Map<String,String > famMembers=new HashMap<>();
emp1.setFamilyMembers(famMembers);
        ObjectMapper objectMapper=new ObjectMapper();
        String payload=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp1);
        System.out.println(payload);

    }
}
