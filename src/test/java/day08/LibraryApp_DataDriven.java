package day08;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utility.ConfigurationReader;

public class LibraryApp_DataDriven {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";

    }

    // give a name your test in this format
    // iteration 1 | username:firstColdata , password: secondColdata
    @ParameterizedTest(name = "iteration {index} | username:{0} , password:{1}")
    @CsvFileSource(resources = "/credentials.csv")
    public void testLoginCredentials(String user, String pass){

        System.out.println("user = " + user);
        System.out.println("pass = " + pass);


    }


    @AfterAll
    public static void cleapUp(){
        RestAssured.reset();
    }



}




