
/**
 * Get info returns the exports and their dollar values of the country input
 * 
 * @author joexcode
 * @version 96770.73
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Exports {
    public void tester() {
        FileResource fr = new FileResource("exports_small.csv");
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "United States"));
        //for (CSVRecord r : parser) {
         //String s = r.get("Country");
         //System.out.println(s);
        //}
        //String s = parser.get("Name");
        //System.out.println(parser.get(0));   
    }
    public String countryInfo(CSVParser parser, String country) {
        String info = "";
        for (CSVRecord r : parser) {
           if (r.get("Country").equals(country)) {
              String name = r.get("Country");
              String export = r.get("Exports");
              String values = r.get("Value (dollars)");
              return name + ": " + export + ": " + values;
           }
        }
        return "NOT FOUND";
    }
}
