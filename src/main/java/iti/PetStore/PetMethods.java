package iti.PetStore;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import static iti.PetStore.EnvVariables.petID;

public class PetMethods extends BaseTest {

    public Response response;

    public Response testAddPet() {
        String postBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"new pet\"\n" +
                "  },\n" +
                "  \"name\": \"Dog Happy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://in.pinterest.com/pin/dog-happy-new-year-pets--697776536049610476/\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        response = createRequestSpec()
                .body(postBody)
                .when().post("/pet");

        response.then().log().all();
        return response;
    }

    public Response testAddPetWithInvalidID() {
        String postBody = "{\n" +
                "  \"id\": \"aa\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"new pet\"\n" +
                "  },\n" +
                "  \"name\": \"Dog Happy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://in.pinterest.com/pin/dog-happy-new-year-pets--697776536049610476/\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        response = createRequestSpec()
                .body(postBody)
                .when().post("/pet");

        response.then().log().all();
        return response;
    }

    public Response UpdateThePetImage(String petID) {
        String additionalMetadata = "this is new dog";
        String filePath = "D:/photos/test2.jpg";

        try {
            response = createRequestSpec()
                    .contentType(ContentType.MULTIPART)
                    .multiPart("additionalMetadata", additionalMetadata)
                    .multiPart("file", new File(filePath))
                    .when().post("/pet/" + petID + "/uploadImage");

            response.then().log().all();
            return response;
        } catch (Exception e) {
            System.out.println("Error occurred while uploading image: " + e.getMessage());
            throw e;
        }
    }

    public Response FindPetByID(String petID) {
        response = createRequestSpec()
                .when().get("/pet/" + petID);

        response.then().log().all();
        return response;
    }

    public Response FindPetByInvalidID(int petID) {

        response = createRequestSpec()
                .when().get("/pet/" + petID);

        response.then().log().all();
        return response;
    }

    public Response FindPetByStatus() {
        response = createRequestSpec()
                .queryParam("status", "available")
                .when().get("/pet/findByStatus");

        response.then().log().all();
        return response;
    }

    public Response FindPetByInvalidStatus() {
        response = createRequestSpec()
                .queryParam("status", "arwa")
                .when().get("/pet/findByStatus");

        response.then().log().all();
        return response;
    }

    public Response UpdateExistingPet(String petID) {
        String updateBody = "{\n" +
                "  \"id\":" + petID + " ,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"updated happy pet\"\n" +
                "  },\n" +
                "  \"name\": \"updated Dog Happy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://in.pinterest.com/pin/dog-happy-new-year-pets--697776536049610476/\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        response = createRequestSpec()
                .body(updateBody)
                .when().put("/pet");

        response.then().log().all();
        return response;
    }

    public Response UpdatePetWithInvalidID(String petID) {
        String updateBody = "{\n" +
                "  \"id\":\"aaa\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"updated happy pet\"\n" +
                "  },\n" +
                "  \"name\": \"updated Dog Happy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://in.pinterest.com/pin/dog-happy-new-year-pets--697776536049610476/\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        response = createRequestSpec()
                .body(updateBody)
                .when().put("/pet");

        response.then().log().all();
        return response;
    }

    public Response GetPetAfterUpdatedByID() {
        response = createRequestSpec()
                .queryParam("status", "available")
                .when().get("/pet/" + petID);

        response.then().log().all();
        return response;
    }

    public Response DeletePet(String petID) {
        response = createRequestSpec()
                .queryParam("status", "available")
                .when().delete("/pet/" + petID);

        response.then().log().all();
        return response;
    }

    public Response DeletePetNotExist(int petID) {
        response = createRequestSpec()
                .queryParam("status", "available")
                .when().delete("/pet/" + petID);

        response.then().log().all();
        return response;
    }
}