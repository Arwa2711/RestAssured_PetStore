package iti.PetStore.Tests.Store;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.StoreMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class deletePurchaseOrder {


    Response response;
    String id;
    @BeforeClass
    public void beforeClass(){
        StoreMethods storeMethods = new StoreMethods();
        response=  storeMethods.CreatePetOrder();

        id =response.getBody().jsonPath().get("id").toString();

        response=  storeMethods.DeletePurchaseOrderByID(id);
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
    public void validateUpdatedPetID() {
        // Extract pet ID from response
        String responseID = response.jsonPath().getString("id");

        // Validate extracted pet ID against expected ID
        response.then().body("message", Matchers.equalTo(id));
    }

    @Test
    public void validateResponseCode() {
        // Validate the response status code as it stored in a variable named 'code'
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("code").equals(200);
    }

    @Test
    public void validateTypeExistence() {
        // Validate if the response body contains the "type" property
        response.getBody().jsonPath().get("type");
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
