
/**
 * Write a description of Weather here.
 * 
 * @author joexcode
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Weather {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
                double currentLow = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldest = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentLow < coldest && currentLow != -9999) {
                    coldestSoFar = currentRow;
                }
            }
        }
        return coldestSoFar;
    }
    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temp was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));
    }
}
