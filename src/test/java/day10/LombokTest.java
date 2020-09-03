package day10;

import org.junit.jupiter.api.Test;
import pojo.Category;
import pojo.Countries;

public class LombokTest {

    @Test
    public void test(){


        Category c1 = new Category("12","abc");
        System.out.println("c1 = " + c1);

        Countries ar = new Countries("AR","ARGENITINA",2 );
        System.out.println("ar = " + ar);



    }

}
