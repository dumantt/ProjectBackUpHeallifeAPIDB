package utilities;

import hooks.API_Hooks;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class API_Utils {


    // public static String generate_Token()
    // public static Response get_Request(String token, String endpoint)
    // public static Response delete_Request(String token, String endpoint)


    public static RequestSpecification spec;
    public static String generateToken(){


        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

        spec.pathParams("pp1","api","pp2","getToken");

        Map<String,Object> dataCredential = new HashMap<>();

        dataCredential.put("email", ConfigReader.getProperty("email"));
        dataCredential.put("password", ConfigReader.getProperty("password"));

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .when()
                .body(dataCredential)
                .post("/{pp1}/{pp2}");

        JsonPath respJP = response.jsonPath();

        String token = respJP.getString("token");

        return token;
    }


    public static Response getRequest(String token, String endpoint) {

        Response response = given().headers(
                "Authorization",
                "Bearer " + token,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).when().get(endpoint);


        return response;


    }

    public static Response deleteRequest(String token, String endpoint){
        Response response = given().headers(
                "Authorization",
                "Bearer " + token,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).when().delete(endpoint);
        return  response;
    }



}
