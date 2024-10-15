package iti.PetStore.Tests.Pet;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.PetMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FindPetByStatusTest {

    Response response;
    @BeforeClass
    public void beforeClass(){
        PetMethods petMethods = new PetMethods();
        response=  petMethods.FindPetByStatus();
    }

    @Test
    public void validateStatusCode() {
        //Response
        response.then().statusCode(200);
    }

    @Test
    public void validateResponseTime() {
        //Response
        response.then().time(Matchers.lessThan(EnvVariables.AssertTime));
    }

    @Test
    public void validateResponseCode() {
        // Validate the response status code as it stored in a variable named 'code'
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("code").equals(200);
    }

    @Test
    public void validateStatus() {
        // Check if the response body is an array
        List list =response.getBody().jsonPath().get();

               // Iterate over each element in the array
        for (int x=0; x<list.size() ;x++) {

            response.then().body(" status["+x+"]", Matchers.equalTo("available"));
            System.out.println("status["+x+"]  "+ response.jsonPath().get("status["+x+"]"));
        }
    }

    @Test
    public void validateContentType() {
        // Check if the Content-Type header is application/json
        String contentType = response.headers().get("Content-Type").toString();
        if (contentType.equals("application/json")) {
            System.out.println("Content-Type is indeed application/json");
        } else {
            System.out.println("Content-Type is not application/json");
        }
    }

}
