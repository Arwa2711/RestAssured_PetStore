package iti.PetStore.Tests.Store;

import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.StoreMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReturnPetInventoriesByStatus {

    Response response;
    @BeforeClass
    public void beforeClass(){
        StoreMethods storeMethods = new StoreMethods();
        response=  storeMethods.ReturnPetInventoriesByStatus();
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
    public void validateStatusExistence() {
        // Validate if the response body contains the "status" property
        response.getBody().jsonPath().get("status");
    }

    @Test
    public void validateAvailableExistence() {
        // Validate if the response body contains the "available" property
        response.getBody().jsonPath().get("available");
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
