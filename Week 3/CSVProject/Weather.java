
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
    public String coldData(CSVParser parser) {
        String data = "";
        for (CSVRecord r : parser) {
            data += r.get("DateUTC") + ": " + r.get("TemperatureF") + "\n";
        }
        return data;
    }
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser) {
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    public void testColdestHourInFile () {
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temp was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));
    }
    public CSVRecord coldestInManyDays () {
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar);
        }
        return coldestSoFar;
    }
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar) {
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
        return coldestSoFar;
    }
    public void testColdestInManyDays () {
        CSVRecord lowest = coldestInManyDays();
        System.out.println("coldest temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("DateUTC"));
    }
    public String fileWithColdestTemperature () {
        String fileName = "";
        String fileTemps = null;
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = currentRow;
            }
            else {
                double currentLow = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldest = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentLow < coldest && currentLow != -9999) {
                    coldestSoFar = currentRow;
                    fileName = f.getName();
                }
            }
        }
        return fileName;
    }
    public void testFileWithColdestTemperature () {
        String fileName = fileWithColdestTemperature();
        FileResource coldestFile = new FileResource("./nc_weather/2014/" + fileName);
        //CSVRecord coldest = fr.get("TemperatureF");
        System.out.println("Coldest day was in file " + fileName);
        System.out.println("Coldest temperature on that day was " + coldestHourInFile(coldestFile.getCSVParser()).get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: \n" + coldData(coldestFile.getCSVParser()));
    }
}
