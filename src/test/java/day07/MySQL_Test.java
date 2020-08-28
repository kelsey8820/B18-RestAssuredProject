package day07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utility.DB_Utility;

public class MySQL_Test {
    
    // add your dependency for mysql driver
    // add your db_utility class 
    // add your configuration reader 
    // DB_Utility class has a method called create connection 
    // and it accept env as string and add .database.. at the end to find correct credenatos
    // for example if we pass library1
    // it will pick up library1.database.url library1.database.username library1.database.password
    @BeforeAll
    public static void initDB(){
        DB_Utility.createConnection("library1");
    }


    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
    }

    
    
}
