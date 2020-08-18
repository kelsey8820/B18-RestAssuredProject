package day01;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


// Hamcrest library is a assetion library
// to aim make the test more human readable
// using lots of human readable machers like something is(somethingelse)
// Most importantly restassured use hamcrest matcher
    //when we chain multiple rest assured methods

public class Practice2 {

    @Test
    public void test1(){

        // assert 5+4 is 9
        int num1 = 5 ;
        int num2 = 4 ;
    // we need these two import for this to work
//        import static org.hamcrest.MatcherAssert.assertThat;
//        import static org.hamcrest.Matchers.*;
        // Hamcrest already come with RestAssured dependency

        // hamcrest library use the assertThat method for all assertions
        // hamcrest use built in matchers to do assertion
        // couple common ones are :
            //  is( some value )
            // equalTo( some value)
            //  or optionally   is ( equalTo(some value) )
            // not

        assertThat( num1 + num2 ,  is(9)   );
        assertThat( num1 + num2 ,  equalTo(9)   );
        assertThat(num1+num2,    is (equalTo(9) ) ) ;

        // not( value )
        // is( not (some value ) )
        // not( equalTo(11) )
        assertThat(num1+num2 , not(11) );
        assertThat(num1+num2, is( not(11) ) );

        // save your first name and last name into 2 variables
        // test the concatenation result using hamcrest matcher
        String firstName = "Kaisar "; // there is a space in last character here
        String lastName = "Anvar";

        assertThat(firstName+lastName, is("Kaisar Anvar")  );
        assertThat(firstName+lastName, equalTo("Kaisar Anvar")  );
        assertThat(firstName+lastName, is( equalTo("Kaisar Anvar"))  );

        // String matchers
        // equalToIgnoringCase
        // equalToCompressingWhiteSpace
        //containsString, endsWith, startsWith - test string matching

        // how to check in caseInsenstive manner
        assertThat(firstName , equalToIgnoringCase("kaisar ") );
        // how to ignore all whitespaces
        assertThat(firstName, equalToCompressingWhiteSpace("Kaisar"));


    }


}
