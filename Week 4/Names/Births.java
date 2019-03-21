
/**
 * Write a description of Births here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Births {
  public void totalBirths (FileResource fr) {
    int totalBirths = 0;
    int totalNames = 0;
    int totalBoys = 0;
    int totalGirls = 0;
    int boyNames = 0;
    int girlNames = 0;
      for (CSVRecord r : fr.getCSVParser(false)) {
      int birthCount = Integer.parseInt(r.get(2));
      totalBirths += birthCount;
      if (r.get(1).equals("M")) {
        totalBoys += birthCount;
        boyNames++;
        totalNames++;
      } else {
        totalGirls += birthCount;
        girlNames++;
        totalNames++;
      }
    }
    System.out.println("Total births " + totalBirths + " and Total names " + totalNames);
    System.out.println("Total Boys " + totalBoys + " and boy names " + boyNames);
    System.out.println("Total Girls " + totalGirls + " and girls names " + girlNames);
  }
  public void testTotalBirths () {
    FileResource fr = new FileResource("yob2012short.csv");
    totalBirths(fr);
  }
}
