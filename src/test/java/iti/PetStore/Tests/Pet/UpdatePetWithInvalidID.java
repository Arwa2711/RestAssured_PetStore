package iti.PetStore.Tests.Pet;

import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.PetMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdatePetWithInvalidID {

    Response response;
    String petID;

    @BeforeClass
    public void beforeClass(){
        PetMethods petMethods = new PetMethods();
        response=  petMethods.testAddPet();
        petID =response.getBody().jsonPath().get("id").toString();

        response=  petMethods.UpdatePetWithInvalidID(petID);
    }

    @Test
    public void validateStatusCode() {
        //Response
        response.then().statusCode(500);
    }

    @Test
    public void validateResponseTime() {
        //Response
        response.then().time(Matchers.lessThan(EnvVariables.AssertTime));
    }

}
