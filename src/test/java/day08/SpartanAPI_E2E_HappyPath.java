package day08;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_E2E_HappyPath {

    // We want exact order 1.Add , 2, Read, 3 Update , 4 Delete

    @Order(1)
    @Test
    public void testAddData(){

    }

    @Order(2)
    @Test
    public void testReadData(){

    }

    @Order(3)
    @Test
    public void testUpdateData(){

    }
    @Order(4)
    @Test
    public void testDeleteData(){

    }

}
