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

        assertThat( num1 + num2 ,  is(9)   );
        assertThat( num1 + num2 ,  equalTo(9)   );


    }


}
