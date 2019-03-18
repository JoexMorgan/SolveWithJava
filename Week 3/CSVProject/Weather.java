
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
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord driestSoFar = null;
        for (CSVRecord currentRow : parser) {
            if (driestSoFar == null) {
                driestSoFar = currentRow;
            }
            else {
                double currentLow = Double.parseDouble(currentRow.get("Humidity"));
                double driest = Double.parseDouble(driestSoFar.get("Humidity"));
                if (currentLow < driest && currentRow.get("Humidity") != "N/A") {
                    driestSoFar = currentRow;
                    //fileName = f.getName();
                }
            }
        }
        return driestSoFar;
    }
    public void testLowestHumidityInFile () {
        FileResource fr = new FileResource();
        CSVRecord driest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest humidity was " + driest.get("Humidity") + " at " + driest.get("DateUTC"));
    }
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord driestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            driestSoFar = getDriestOfTwo(currentRow, driestSoFar);
        }
        return driestSoFar;
    }
    public CSVRecord getDriestOfTwo(CSVRecord currentRow, CSVRecord driestSoFar) {
        if (driestSoFar == null) {
                driestSoFar = currentRow;
        }
        else {
            double currentLow = Double.parseDouble(currentRow.get("Humidity"));
            double driest = Double.parseDouble(driestSoFar.get("Humidity"));
            if (currentLow < driest) {
                driestSoFar = currentRow;
            }
        }
        return driestSoFar;
    }
    public void testLowestHumidityInManyFiles () {
        CSVRecord dryGuy = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + dryGuy.get("Humidity") + " at " + dryGuy.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser) {
        int tempCount = 0;
        double average = 0;
        for (CSVRecord r : parser) {
            average += Double.parseDouble(r.get("TemperatureF"));
            tempCount++;
        }
        return average / tempCount;
    }
    public void testAverageTemperatureInFile () {
        FileResource fr = new FileResource();
        Double averagest = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is: " + averagest);
    }
}
