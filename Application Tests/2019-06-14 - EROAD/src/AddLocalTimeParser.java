import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class AddLocalTimeParser {

    private final String _FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private final double _maxLat = 90;
    private final double _maxLong = 180;

    private double _latitude;
    private double _longitude;
    private ZonedDateTime _zonedDatetime;
    private String _timeZone;
    private LocalDateTime _localDatetime;
    private ZoneId _defaultTimeZone = ZoneId.of("UTC");

    private final String _csvSplitBy = ",";

    public String parse(String line){

        // use comma as separator
        String[] information = line.split(_csvSplitBy);

        //parsing now has an exception catcher, which would return null
        //If it fails here, there's a problem
        try {
            //Parse lat and long from string to double
            _latitude = Double.parseDouble(information[1]);
            _longitude = Double.parseDouble(information[2]);

            if (Math.abs(_latitude) > _maxLat || Math.abs(_longitude) > _maxLong){
                throw new ParseException("Long or Lat invalid numbers", 3);
            }

            //It is assumed that UTC is the default timezone
            _localDatetime = LocalDateTime.parse(information[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        } catch (Exception e){
            System.err.println("Error in parsing information - please check data is valid and formatted correctly");
            System.err.println("Line " + information[0] + _csvSplitBy + information[1] + _csvSplitBy + information[2]
                + " has not been parsed.");
            return line;
        }

        //Retrieve timezone from latitude and longitude
        //This uses the offline class
        //downside is that it isn't going to be always accurate and might require updating from time to time
        //offline chosen compared to online due to lack of free geolocating options that would allow the required amount
        //of calls in a short space of time
        _timeZone = com.skedgo.converter.TimezoneMapper.latLngToTimezoneString(_latitude, _longitude);

        //Adjust time from UTC to localtime
        _zonedDatetime = _localDatetime.atZone(_defaultTimeZone).withZoneSameInstant(ZoneId.of(_timeZone));

        //Returns the result to replace the row in the CSV
        return line + _csvSplitBy + _timeZone + _csvSplitBy + DateTimeFormatter.ofPattern(_FORMAT).format(_zonedDatetime) + "\n";
    }
}