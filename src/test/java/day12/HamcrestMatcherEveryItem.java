package day12;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcherEveryItem {

    @Test
    public void testList(){

        List<Integer> nums = Arrays.asList(1,4,5,6,8,9,10) ;
        // greaterThan()
        // lessThan()
        assertThat(nums, everyItem(  greaterThan(0)    )   );

        assertThat(nums, everyItem(  lessThan(20)    )   );
        // assert all numbers between 0-11
        assertThat(nums, everyItem( allOf( greaterThan(0), lessThan(11) )  ) );

        // create a list of String and check each item has length of 4
        String  str = "abc";
        assertThat(str,  hasLength(3) );

        List<String> itemList = Arrays.asList("ello", "than", "come", "ease");
        assertThat( itemList,  everyItem( hasLength(4) ) );

        int num =  65 ;
        //  num is more than 50 or num is less than 0 ;

//        assertThat(num ,  greaterThan(50)     );
//        assertThat(num ,  lessThan(0)     );
        // THIS IS A OR SITUATION    :  anyOf (matcher1, matcher2 , matcher3....)
        assertThat(num ,   anyOf( greaterThan(50), lessThan(0) ) );

        // WHAT ABOUT AND SITUATION  : allOf(matcher1, matcher2 , matcher3....)
        int myAwesomeNumber = 100 ;
        // check the number is in between 0 to 50
        assertThat(myAwesomeNumber, allOf(  greaterThan(0) , lessThan(50)   )    );

        // Now you can try this for your list of numbers
        // check each and every item in your first number list is in between 0-50


    }


}
