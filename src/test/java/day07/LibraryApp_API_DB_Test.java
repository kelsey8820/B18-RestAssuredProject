package day07;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utility.ConfigurationReader;
import utility.DB_Utility;

public class LibraryApp_API_DB_Test {


    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";
        //TODO add a utility class that contains below method
        //libraryToken = loginAndGetToken("librarian69@library", "KNPXrm3S");
        DB_Utility.createConnection("library1");

    }


    @AfterAll
    public static void destroy(){

        DB_Utility.destroy();
        RestAssured.reset(); // this is for resetting all the values we set for RestAssured itself

    }




}
