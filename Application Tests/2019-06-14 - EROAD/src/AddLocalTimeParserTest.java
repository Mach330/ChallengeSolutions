import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddLocalTimeParserTest {

    private String _fileLocation = "";

    @BeforeAll
    public void set_fileLocation(){
        //replace this with a testing file location
        _fileLocation = "/home/chrx/Documents/EROAD.csv";
    }

    @Test
    public void testParse() {
        AddLocalTimeParser parser = new AddLocalTimeParser();
        assertEquals(parser.parse("2013-07-10 02:52:49,-44.490947,171.220966"),
                "2013-07-10 02:52:49,-44.490947,171.220966,Pacific/Auckland,2013-07-10T14:52:49");
        assertEquals(parser.parse("2013-07-10 02:52:49,-33.912167,151.215820"),
                "2013-07-10 02:52:49,-33.912167,151.215820,Australia/Sydney,2013-07-10T12:52:49");
    }

    @Test
    public void testDateTimeParse(){
        assertEquals(LocalDateTime.parse("2013-07-10 02:52:49", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")),
                "2013-07-10T02:52:49");
    }

    @Test
    public void testParseWithNulls(){
        AddLocalTimeParser parser = new AddLocalTimeParser();
        assertEquals(parser.parse("2013-07-10 02:52:49,-44.490947,null"),
                "2013-07-10 02:52:49,-44.490947,null");
        assertEquals(parser.parse("null 02:52:49,-44.490947,171.220966"),
                "null 02:52:49,-44.490947,171.220966");
        assertEquals(parser.parse("null,-44.490947,171.220966"),
                "null,-44.490947,171.220966");
    }

    @Test
    public void testParseWithInvalidNumbers(){
        AddLocalTimeParser parser = new AddLocalTimeParser();
        assertEquals(parser.parse("2013-07-10 02:52:49,-44.490947,180.1"),
                "2013-07-10 02:52:49,-44.490947,180.1");
        assertEquals(parser.parse("2013-07-10 02:52:49,-91,171.220966"),
                "2013-07-10 02:52:49,-91,171.220966");
        assertEquals(parser.parse("07-10 02:52:49,-44.490947,171.220966"),
                "07-10 02:52:49,-44.490947,171.220966");
    }

    //tests like this would require accompanying documents in predetermined locations.
    //This tests that the first line contains at least its original contents, for a given file.
    //Note that the test file doesn't need to be updated after every test
    //As it is impossible to dynamically know the expected result of the file without using the algorithm,
    //this tests whether the original line is still present, and whether 2 new bits of information have been added.
    @Test
    public void testFileUpdate(){
        String line = "";
        String newLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(_fileLocation));
            line = br.readLine();
            br.close();

            CSVReadWrite csvrw = new CSVReadWrite();
            csvrw.updateCSVFile(_fileLocation);

            BufferedReader afterBR = new BufferedReader(new FileReader(_fileLocation));

            newLine = afterBR.readLine();

            assertTrue(newLine.contains(line));
            assertTrue(newLine.split(",").length == line.split(",").length + 2);
            br.close();
        } catch (Exception e){
            System.err.println("The file " + _fileLocation + " doesn't exist. Confirm the location of the file.");
            System.exit(1);
        }

    }

}