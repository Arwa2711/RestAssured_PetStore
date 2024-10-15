package iti.PetStore.Tests.User;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.UserMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetUserByUserName {

    Response response;
    String userID;
    @BeforeClass
    public void beforeClass(){
        UserMethods userMethods = new UserMethods();
        response=  userMethods.CreateUser();
        userID =response.getBody().jsonPath().get("message").toString();

        response=  userMethods.GetUserByUserName();
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

        String expectedID = userID;
        response.jsonPath().get("id").equals( expectedID);
    }

    @Test
    public void validateResponseUserName() {
        // Validate the response userName
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("username").equals("arwa");
    }

    @Test
    public void validateResponseFirstName() {
        // Validate the response firstName
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("firstName").equals("arwa");
    }

    @Test
    public void validateResponseLastName() {
        // Validate the response lastName
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("lastName").equals("ashraf");
    }

    @Test
    public void validateResponseEmail() {
        // Validate the response email
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("email").equals("arwam1161@gmail.com");
    }

    @Test
    public void validateResponsePassword() {
        // Validate the response password
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("password").equals("a12345");
    }

    @Test
    public void validatePhoneExistence() {
        // Validate if the response body contains the "phone" property
        response.getBody().jsonPath().get("phone");
    }

    @Test
    public void validateUserStatusExistence() {
        // Validate if the response body contains the "userStatus" property
        response.getBody().jsonPath().get("userStatus");
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
