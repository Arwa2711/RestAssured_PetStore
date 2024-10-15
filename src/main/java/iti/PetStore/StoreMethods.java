package iti.PetStore;

import io.restassured.response.Response;

public class StoreMethods extends  BaseTest {

    Response response;

    public Response ReturnPetInventoriesByStatus() {
        response = createRequestSpec()
                .when().get("store/inventory");

        response.then().log().all();
        return response;
    }

    public Response CreatePetOrder() {
        String postBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"petId\": 0,\n" +
                "  \"quantity\": 5,\n" +
                "  \"shipDate\": \"2024-09-22T08:09:02.371Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        response = createRequestSpec()
                .body(postBody)
                .when().post("/store/order");

        response.then().log().all();
        return response;
    }

    public Response CreatePetOrderWithInvalidData() {
        String postBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"petId\": \"aaa\",\n" +
                "  \"quantity\": 5,\n" +
                "  \"shipDate\": \"2024-09-22T08:09:02.371Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        response = createRequestSpec()
                .body(postBody)
                .when().post("/store/order");

        response.then().log().all();
        return response;
    }

    public Response FindPurchaseOrderByID(String id) {
        response = createRequestSpec()
                .when().get("/store/order/" + id);

        response.then().log().all();
        return response;
    }

    public Response FindPurchaseOrderByInvalidID(int id) {
        response = createRequestSpec()
                .when().get("/store/order/" + id);

        response.then().log().all();
        return response;
    }

    public Response DeletePurchaseOrderByID(String id) {
        response = createRequestSpec()
                .when().delete("/store/order/" + id);

        response.then().log().all();
        return response;
    }

    public Response DeletePurchaseOrderByInvalidID(int id) {
        response = createRequestSpec()
                .when().delete("/store/order/" + id);

        response.then().log().all();
        return response;
    }
}
