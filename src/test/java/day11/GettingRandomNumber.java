package day11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GettingRandomNumber {


    @Test
    public void testRandom(){

        // Random class is coming from java.util package and can provide some random numbers in different type
        // first we need to create an object
        Random r = new Random();
        // This will give us random number from 0-6
        int randomNumber = r.nextInt(6) ;

        List<String> names = Arrays.asList("Anna", "Vincent","zuleyha", "Emrah", "Natalllia", "Zuura" ) ;

        System.out.println("randomNumber = " + randomNumber);
        // get a random name from the list each time

        System.out.println("Lucky name is  = " + names.get(randomNumber)   );





    }



}
