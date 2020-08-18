package day01;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BeforeAfterInJunit5 {

    // this method will run only once before the entire test
    // same idea as @BeforeClass you learned previously
    // these are JUNIT5 Specific annotations
    @BeforeAll
    public static void setUp(){
        System.out.println("This run before All");
    }

    @Test
    public void test1(){
        System.out.println("Test is running");
    }


    @AfterAll
    public static void tearDown(){
        System.out.println("This run all the way at the end");
    }



}
