package iti.PetStore.Tests.Store;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.StoreMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindPurchaseOrderByID {

    Response response;
    @BeforeClass
    public void beforeClass(){
        StoreMethods storeMethods = new StoreMethods();
        response=  storeMethods.CreatePetOrder();

        String id =response.getBody().jsonPath().get("id").toString();
        response=  storeMethods.FindPurchaseOrderByID(id);
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
    public void validateOrderStatus() {
        // Validate the status of the order
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("status").equals("placed");
    }

    @Test
    public void validateOrderCompletion() {
        // the completion of the order
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("complete").equals(true);
    }

    @Test
    public void validateShipDateExistence() {
        // Validate if the response body contains the "ship Date" property
        response.getBody().jsonPath().get("shipDate");
    }

    @Test
    public void validateQuantityExistence() {
        // Validate if the response body contains the "quantity" property
        response.getBody().jsonPath().get("quantity");
    }

    @Test
    public void validatePetIdExistence() {
        // Validate if the response body contains the "pet Id" property
        response.getBody().jsonPath().get("petId");
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
