package iti.PetStore.Tests.Pet;

import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.PetMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindPetByInvalidID {

    Response response;

    @BeforeClass
    public void beforeClass(){
        PetMethods petMethods = new PetMethods();
        response=  petMethods.FindPetByInvalidID(111111111);
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
