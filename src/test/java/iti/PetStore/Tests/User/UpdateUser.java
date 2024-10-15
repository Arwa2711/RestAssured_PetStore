package iti.PetStore.Tests.User;

import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.UserMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateUser {

    Response response;
    @BeforeClass
    public void beforeClass(){
        UserMethods userMethods = new UserMethods();
        response=  userMethods.UpdateUser();
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

        EnvVariables.userIDUpdated =response.getBody().jsonPath().get("message").toString();
        System.out.println(EnvVariables.userIDUpdated);
    }

    @Test
    public void validateCodeExistence() {
        // Validate if the response body contains the "code" property
        response.getBody().jsonPath().get("code");
    }

    @Test
    public void validateTypeExistence() {
        // Validate if the response body contains the "type" property
        response.getBody().jsonPath().get("type");
    }

    @Test
    public void validateMessageExistence() {
        // Validate if the response body contains the "message" property
        response.getBody().jsonPath().get("message");
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
