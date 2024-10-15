package iti.PetStore.Tests.Pet;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.PetMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindPetByIDTest {

    Response response;
    String petID;

    @BeforeClass
    public void beforeClass(){
        PetMethods petMethods = new PetMethods();
        response=  petMethods.testAddPet();
        petID =response.getBody().jsonPath().get("id").toString();

        response=  petMethods.FindPetByID(petID);
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
    public void validatePetName() {
        // Validate the name of the new pet
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("name").equals("Dog Happy");
    }

    @Test
    public void validatePetID() {
        // Retrieve pet ID
        String expectedID = petID;

        response.jsonPath().get("id").equals( expectedID);
    }

    @Test
    public void validatePetStatus() {
        // Validate the status of the new pet
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("status").equals("available");
    }

    @Test
    public void validateTagsExistence() {
        // Check if the response body contains the "tags" property
        response.getBody().jsonPath().get("tags");
    }

    @Test
    public void validatePhotoUrlsExistence() {
        // Check if the response body contains the "photoUrls" property
        response.getBody().jsonPath().get("photoUrls");
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
