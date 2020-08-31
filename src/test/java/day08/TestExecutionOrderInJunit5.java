package day08;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Default test execution order is alphabetical
// if you want to change it , this is one way to do it
// This annotation indicate to run the tests according to the @Order annotation with number
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExecutionOrderInJunit5 {

    @Order(4)
    @Test
    public void testB(){

    }
    @Order(2)
    @Test
    public void testA(){

    }
    @Order(3)
    @Test
    public void testZ(){

    }
    @Order(1)
    @Test
    public void testK(){

    }


}
