
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SampleTest {

    @Test
    public void calculatorTest(){


        System.out.println("Hello World");

        // assert 4 + 5  is 9
        assertEquals(9,4+5);

        // how do we add error message if the assertion fail

        assertEquals(10, 5+4 , "Hey wrong result!!");


    }


}