package iti.PetStore.Tests.User;

import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.UserMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteUserNotExist {

    Response response;
    @BeforeClass
    public void beforeClass(){
        UserMethods userMethods = new UserMethods();
        response=  userMethods.DeleteUserNotExisting();
    }

    @Test
    public void validateStatusCode() {
        //Response
        response.then().statusCode(404);
    }

    @Test
    public void validateResponseTime() {
        //Response
        response.then().time(Matchers.lessThan(EnvVariables.AssertTime));
    }
}
