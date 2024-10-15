package iti.PetStore.Tests.Store;

import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.StoreMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindPurchaseOrderByInvalidID {

    Response response;
    @BeforeClass
    public void beforeClass(){
        StoreMethods storeMethods = new StoreMethods();
        response=  storeMethods.FindPurchaseOrderByInvalidID(1111111);
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
