package api2.session18;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class FileUploadDemo {
    @Test
    public void uploadFile(){
        // Create file object
        File testFileUpload=new File("C:\\Users\\aydi1\\Desktop\\fileUploadRestAssured.txt");

        Response response= RestAssured.given()
                .baseUri("http://httpbin.org/post")
                .multiPart("file",testFileUpload)
                .contentType("multipart/form-data")
                .when().post();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test
    public void uploadImage(){
        File testFileUpload=new File("C:\\Users\\aydi1\\Pictures\\Screenshots\\Collections.png");

        Response response= RestAssured.given()
                .baseUri("https://petstore.swagger.io/v2/pet/1/uploadImage")
                .multiPart("file",testFileUpload)
                .contentType("multipart/form-data")
                .when().post();
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(),200);
    }
}
