package iti.PetStore.Tests.User;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.UserMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LogsOutCurrentLoggedInUserSession {

    Response response;
    @BeforeClass
    public void beforeClass(){
        UserMethods userMethods = new UserMethods();
        response=  userMethods.LogsOutCurrentLoggedInUserSession();
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
    public void validateResponseMessage() {
        // Validate the response message
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("message").equals("ok");
    }


    @Test
    public void validateTypeExistence() {
        // Validate if the response body contains the "type" property
        response.getBody().jsonPath().get("type");
    }

    @Test
    public void validateCodeExistence() {
        // Validate if the response body contains the "code" property
        response.getBody().jsonPath().get("code");
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
