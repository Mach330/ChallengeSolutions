import java.io.*;

public class CSVReadWrite {

    //This allows for dependency injection for other files/formats
    private AddLocalTimeParser _parser;
    private String _tempFileLocation = "temp.tmp";

    public void updateCSVFile(String csvFile) {

        //This can be replaced with a parsing interface implementation at a later date
        //Will allow for dependency injection for other files and formats
        _parser = new AddLocalTimeParser();

        File newFile = new File(_tempFileLocation);
        String line = "";

        try (
                BufferedReader br = new BufferedReader(new FileReader(csvFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(_tempFileLocation, true));
        ) {

            while ((line = br.readLine()) != null) {

                //the localTimeParser will parse the information and return the string to replace what's currently there
                //This means that this method won't comply to any formatting of what it calls
                bw.write(_parser.parse(line));

            }
        } catch (IOException e) {
            System.err.println("The file " + csvFile + " doesn't exist. Confirm the location of the file.");
            System.exit(1);
        } finally {
            new File(csvFile).delete();
            File dump = new File(csvFile);
            newFile.renameTo(dump);
        }
    }
}
