package iti.PetStore.Tests.User;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.UserMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckTheDataOfUserAfterUpdate {

    Response response;
    String updatedUserID;
    @BeforeClass
    public void beforeClass(){
        UserMethods userMethods = new UserMethods();
        response=  userMethods.UpdateUser();
        updatedUserID =response.getBody().jsonPath().get("message").toString();

        response=  userMethods.CheckTheDataOfUserAfterUpdate();
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
    public void validateUserID() {
        String expectedID = updatedUserID;
        response.jsonPath().get("id").equals( expectedID);
    }

    @Test
    public void validateResponseUserName() {
        // Validate the response userName
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("username").equals("arwaupdate");
    }

    @Test
    public void validateResponseFirstName() {
        // Validate the response firstName
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("firstName").equals("arwaupdate");
    }

    @Test
    public void validateResponseLastName() {
        // Validate the response lastName
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("lastName").equals("ashrafupdate");
    }
    @Test
    public void validateEmailExistence() {
        // Validate if the response body contains the "email" property
        response.getBody().jsonPath().get("email");
    }

    @Test
    public void validatePasswordExistence() {
        // Validate if the response body contains the "password" property
        response.getBody().jsonPath().get("password");
    }

    @Test
    public void validatePhoneExistence() {
        // Validate if the response body contains the "phone" property
        response.getBody().jsonPath().get("phone");
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
