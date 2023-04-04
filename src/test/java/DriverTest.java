import org.apache.commons.cli.Option;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DriverTest {
    @Test
    void Test1(){
        assertTrue(1 ==1 );
    }

    @Test
    void TestGetFile() {
        String file = "src/main/java/test.txt";
        Driver.getFileData(file);
        assertEquals("1,2,3,4,5,65" ,Driver.fileData );
    }

    @Test
    void TestEmpty() {
        String file = "src/main/java/testEmpty.txt";
        Driver.getFileData(file);
        assertEquals("", Driver.fileData);
    }

    @Test
    void TestGetParsedData() {
        Driver.fileData = "1,2,3,4,5,65";
        Driver.getParsedData();
        assertEquals("[1, 2, 3, 4, 5, 65]", Driver.parsedData.toString());
    }

    @Test
    void TestGetDataRandom() {
        String addressValue= "-1";

        // once we got data
        Driver.getData("", addressValue, 5);
        assertEquals("[3, 4, 1, 3, 2]", Driver.parsedData.toString());
    }

    @Test
    void TestGetDataFileData() {
        // once we got data
        Driver.getData("src/main/java/test.txt", "", 5);
        assertEquals("[1, 2, 3, 4, 5, 65]", Driver.parsedData.toString());
    }

    @Test
    void TestGetDataArgumentData() {
        // once we got data
        String addressValue = "1,5,7,3,5,9,1";
        Driver.getData("", addressValue, 5);
        assertEquals("[1, 5, 7, 3, 5, 9, 1]", Driver.parsedData.toString());
    }

    @Test
    void TestFindData() {
        //we will add values 1-9 in our LRU, size 9
        // we want to find 5
        // check if the ordering is 1,2,3,4,6,7,8,9
        LRUCache lruCache = new LRUCache(9);
        for (int i = 1; i < 10; i++) {
            lruCache.refer(i);
        }
        lruCache.getValue(5);
        assertEquals("[1, 2, 3, 4, 6, 7, 8, 9]", lruCache.toString());
    }

//
//    @Test
//    void TestNoFile() {
//        String file = "src/main/java/tester.txt";
//        Driver.getFileData(" ");
//        assertEquals(" " ,Driver.fileData );
//        String data = " ";
//        try {Driver.getFileData(" ");
//        } catch (Exception e) {
//            System.out.print("File is empty");
//        }
//    }



}
