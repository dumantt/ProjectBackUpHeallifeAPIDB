package hooks;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import utilities.API_Utils;

import utilities.ConfigReader;

public class API_Hooks{
    public static RequestSpecification spec;
    public static String token;



    @Before (order = 1)
    public void beforeGenerateToken(){

        token = API_Utils.generateToken();
        System.out.println("Tokenim : " + token);

    }



    @Before (order = 0)
    public void setUpApi(){

        spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("base_url")).build();

    }





}