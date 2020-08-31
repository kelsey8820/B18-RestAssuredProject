package day08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadingCSVFileFromTheTest {


    // This is to specify this test is now parameterized test
    @ParameterizedTest
    // This line is to specify , we are using csv as source
    // and provide the path for csv file under resources folder
    // and we skipped the first line which is the header in our case
    @CsvFileSource(resources = "/data.csv",numLinesToSkip = 1)
    public void simpleRead(int num1, int num2){

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

    }

    // Please add another csv file called numbers.csv
    // num1,num2, additionResult
    // 5,4,9
    // 4,7,11
    // 3,8,11
    // 6,10,16
    /// Please add a @ParameterizedTest
    /// specify the file source as numbers.csv
    ///  in the meantime add 3rd parameter to your test called int result
    /// assert that num1 + num2  = result
    @ParameterizedTest(name = "Row {index} --> {0} + {1} = {2} ")
    @CsvFileSource(resources = "/numbers.csv", numLinesToSkip = 1)
    public void testAddition(int n1, int n2, int result){
        //import static org.junit.jupiter.api.Assertions.assertEquals;
        assertEquals(result,n1+n2 ) ;

    }



}
