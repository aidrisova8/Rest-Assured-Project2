package api2.session14;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.testng.annotations.Test;
import  org.hamcrest.Matchers;

import java.util.List;

public class ValidateXNLResponse {

    @Test
    public void getTravellersData(){

//http://restapi.adequateshop.com/api/Traveler?page=1
        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("http://restapi.adequateshop.com")
                .basePath("/api/Traveler")
                .header("accept","application/xml")
                .header("Content-Type","application/xml")
                .queryParam("page","1");

        Response response=reqSpec.get();
        response.prettyPrint();
       //1st approach
        response.then().body("TravelerinformationResponse.travelers.Travelerinformation[2].name",Matchers.equalTo("vano"));//we got it from response body
//2nd approach
        XmlPath objXmlPath=new XmlPath(response.asString());
   String travelerNameActual=objXmlPath.get("TravelerinformationResponse.travelers.Travelerinformation[2].name").toString();
   Assert.assertEquals(travelerNameActual,"vano");

   //verify total travelers to be 10
 List<String> listOfTravelers= objXmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation");
 int totalTravelerCount= listOfTravelers.size();
 Assert.assertEquals(totalTravelerCount,10);

 //verify for name vano in  travellers list
        boolean found=false;
        List<String> nameOfTravelers= objXmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        for(String name: nameOfTravelers){
            System.out.println(name);
            if(name.equals("vano")){
               found=true;
               break;
            }
        }
        Assert.assertEquals(found,true);
    }
    @Test
    public void AddPet(){
        String xmlData= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Pet>\n" +
                "\t<id>0</id>\n" +
                "\t<Category>\n" +
                "\t\t<id>0</id>\n" +
                "\t\t<name>string</name>\n" +
                "\t</Category>\n" +
                "\t<name>doggie</name>\n" +
                "\t<photoUrls>\n" +
                "\t\t<photoUrl>string</photoUrl>\n" +
                "\t</photoUrls>\n" +
                "\t<tags>\n" +
                "\t\t<Tag>\n" +
                "\t\t\t<id>0</id>\n" +
                "\t\t\t<name>string</name>\n" +
                "\t\t</Tag>\n" +
                "\t</tags>\n" +
                "\t<status>available</status>\n" +
                "</Pet>";
        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("https://petstore.swagger.io")
                .basePath("/v2/pet")
                .header("accept","application/xml")
                .header("Content-Type","application/xml")
                .body(xmlData);
        Response response=reqSpec.post();
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(),200);
response.then().body("Pet.name",Matchers.equalTo("doggie"));

    }
}
