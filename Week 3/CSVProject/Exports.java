
/**
 * 
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
        for (CSVRecord r : parser) {
         String s = r.get("Country");
         System.out.println(s);
        }
        //String s = parser.get("Name");
        //System.out.println(parser.get(0));   
    }
    public String countryInfo(CSVParser parser, String country) {
        return null;
        //return format '${country}: ${export}: ${value}'
    }
}
