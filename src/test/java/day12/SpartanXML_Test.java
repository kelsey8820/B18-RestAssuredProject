package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanXML_Test {

    // boilerplate goes here
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }

    @DisplayName("Test XML Response from GET /Spartans")
    @Test
    public void testSpartanXML(){

        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Mina") )
                .body("List.item[0].gender", is("Male"))
                .body("List.item[0].id", is("424"))

        ;
        // XMLPath xp = response.xmlPath();


    }
    @DisplayName("XMLPath object to extract the data out")
    @Test
    public void testSpartanExtractData(){

        Response response = given()
                .accept(ContentType.XML).
                        when()
                .get("/spartans") ;
        // getting XMLPath object just like we did for JsonPath object
        XmlPath xp = response.xmlPath();

        // get the first spartan id and store it into the int
        int firstID = xp.getInt("List.item[0].id");
        System.out.println("firstID = " + firstID);
        String firstName = xp.getString("List.item[0].name") ;
        System.out.println("firstName = " + firstName);
        long firstPhone = xp.getLong("List.item[0].phone");
        System.out.println("firstPhone = " + firstPhone);

        // get all the ids and store it into the list
        List<Integer> idList = xp.getList("List.item.id" , Integer.class) ;
        System.out.println("idList = " + idList);

        // assert the list size is some number
        // assert above list has items 424 , 592 , 77
        // import static org.hamcrest.MatcherAssert.assertThat;
        // practice hamcrest matcher
        assertThat( idList ,hasSize(485)  ) ;
        // when we got the List<Integer> without specifying what type in getList method
        // somehow it can not decide the in the Hamcrest assertThat method it's a List<Integer>
        // so the fix was to provide class type for the getList method to make it obvious
        // like this  List<Integer> idList = xp.getList("List.item.id", Integer.class) ;
        assertThat(idList, hasItems(424,592,77) );

        // Get a List of Long from the phone numbers
        // first check the size is 479
        // check it has few phone numbers you specified
        // optionally --
        // check every item is greaterThan 1000000000
        List<Long> phoneNums = xp.getList("List.item.phone", Long.class);
        System.out.println("phone Nums = " + phoneNums);

        assertThat(phoneNums, hasSize(485) );
        //9876543210, 7685940321, 7234567890
        assertThat(phoneNums, hasItems(9876543210L, 7685940321L, 7234567890L) );

        // How to check every item in the collection match certain criteria
        // assertThat( 5 , greaterThan(3) );

        assertThat(phoneNums, everyItem( greaterThan(1000000000L)  )  );
        // Get a List of String from the names
        // find out how many unique names you have
        List<String> allNames = xp.getList("List.item.name");
        System.out.println("allNames = " + allNames);
        // Set interface define collection of unique elements
        // creating a HashSet object by copying everything from List , duplicates are auto-removed
        Set<String> uniqueNames = new HashSet<>(  allNames  );
        System.out.println("uniqueNames = " + uniqueNames);

        System.out.println("uniqueNames.size() = " + uniqueNames.size() );
        System.out.println("allNames.size() = " + allNames.size()  );



    }



}
