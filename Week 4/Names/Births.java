
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
    FileResource fr = new FileResource("yob1980.csv");
    totalBirths(fr);
  }
  public Integer getRank (Integer year, String name, String gender) {
    FileResource fr = new FileResource("yob" + year + ".csv");
    //FileResource fr = new FileResource("yob2012short.csv");
    String answer = "";
    Integer rank = 1;
    for (CSVRecord r : fr.getCSVParser(false)) {
      if (r.get(1).equals(gender)) {
        if (r.get(0).equals(name)) {
          answer = name + " was the #" + rank + " most popular name in " + year;
          return rank;
          //answer = r.get(2) + " babies named " + name + " were born in " + year;    
        }
        rank++;
      }
      //rank++;
    }
    return -1;
  }
  public void testGetRank () {
    System.out.println(getRank(1980, "Zoe", "M"));
    //System.out.print(getRank(1980, "John", "M" ));   
  }
}
