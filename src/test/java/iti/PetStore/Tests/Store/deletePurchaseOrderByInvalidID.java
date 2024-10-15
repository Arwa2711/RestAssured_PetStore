package iti.PetStore.Tests.Store;

import io.restassured.response.Response;
import iti.PetStore.EnvVariables;
import iti.PetStore.StoreMethods;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class deletePurchaseOrderByInvalidID {


    Response response;
    String id;

    @BeforeClass
    public void beforeClass() {
        StoreMethods storeMethods = new StoreMethods();
        response = storeMethods.DeletePurchaseOrderByInvalidID(11111);
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
