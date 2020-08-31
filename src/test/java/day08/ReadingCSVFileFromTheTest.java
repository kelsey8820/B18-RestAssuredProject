package day08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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


}
