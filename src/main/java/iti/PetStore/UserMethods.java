package iti.PetStore;

import io.restassured.response.Response;

public class UserMethods extends  BaseTest {

    Response response;

    public Response CreateUser() {
        String postBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"arwa\",\n" +
                "  \"firstName\": \"arwa\",\n" +
                "  \"lastName\": \"ashraf\",\n" +
                "  \"email\": \"arwam1161@gmail.com\",\n" +
                "  \"password\": \"a12345\",\n" +
                "  \"phone\": \"010999999\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        response = createRequestSpec()
                .body(postBody)
                .when().post("/user");

        response.then().log().all();
        return response;
    }

    public Response CreateUserWithInvalidID() {
        String postBody = "{\n" +
                "  \"id\": \"arwa\",\n" +
                "  \"username\": \"arwa\",\n" +
                "  \"firstName\": \"arwa\",\n" +
                "  \"lastName\": \"ashraf\",\n" +
                "  \"email\": \"arwam1161@gmail.com\",\n" +
                "  \"password\": \"a12345\",\n" +
                "  \"phone\": \"010999999\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        response = createRequestSpec()
                .body(postBody)
                .when().post("/user");

        response.then().log().all();
        return response;
    }

    public Response LogsUserIntoTheSystem() {
        response = createRequestSpec()
                .queryParam("username","arwa ashraf")
                .queryParam("password","a12345")
                .when().get("/user/login");

        response.then().log().all();
        return response;
    }

    public Response LogsUserIntoTheSystemWithoutPassword() {
        response = createRequestSpec()
                .queryParam("username","arwa ashraf")
                .when().get("/user/login");

        response.then().log().all();
        return response;
    }

    public Response GetUserByUserName() {
        response = createRequestSpec()
                .when().get("/user/arwa");

        response.then().log().all();
        return response;
    }

    public Response GetUserByInvalidUserName() {
        response = createRequestSpec()
                .when().get("/user/12");

        response.then().log().all();
        return response;
    }

    public Response UpdateUser() {
        String updateBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"arwaupdate\",\n" +
                "  \"firstName\": \"arwaupdate\",\n" +
                "  \"lastName\": \"ashrafupdate\",\n" +
                "  \"email\": \"arwam1161@gmail.com\",\n" +
                "  \"password\": \"a12345\",\n" +
                "  \"phone\": \"010999999\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        response = createRequestSpec()
                .body(updateBody)
                .when().put("/user/arwa");

        response.then().log().all();
        return response;
    }

    public Response UpdateUserWithInvalidUserName() {
        String updateBody = "{\n" +
                "  \"id\": \"arwa\",\n" +
                "  \"username\": \"arwaupdate\",\n" +
                "  \"firstName\": \"arwaupdate\",\n" +
                "  \"lastName\": \"ashrafupdate\",\n" +
                "  \"email\": \"arwam1161@gmail.com\",\n" +
                "  \"password\": \"a12345\",\n" +
                "  \"phone\": \"010999999\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        response = createRequestSpec()
                .body(updateBody)
                .when().put("/user/12");

        response.then().log().all();
        return response;
    }

    public Response CheckTheDataOfUserAfterUpdate() {
        response = createRequestSpec()
                .when().get("/user/arwaupdate");

        response.then().log().all();
        return response;
    }

    public Response LogsOutCurrentLoggedInUserSession() {
        response = createRequestSpec()
                .when().get("/user/logout");

        response.then().log().all();
        return response;
    }

    public Response LogsUserIntoTheSystemAfterLogsOut() {
        response = createRequestSpec()
                .queryParam("username","arwa ashraf")
                .queryParam("password","a12345")
                .when().get("/user/login");

        response.then().log().all();
        return response;
    }

    public Response DeleteUser() {
        response = createRequestSpec()
                .when().delete("/user/arwa");

        response.then().log().all();
        return response;
    }

    public Response DeleteUserNotExisting() {
        response = createRequestSpec()
                .when().delete("/user/11");

        response.then().log().all();
        return response;
    }
}
