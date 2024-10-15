package iti.PetStore;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseTest {

    public  String BASE_URL = EnvVariables.DEV_BASE_URL;

    public RequestSpecification createRequestSpec() {
        return RestAssured.given()
                .log().all().baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

}
