
/**
 * Write a description of Births here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Births {
  public Integer totalBirths (FileResource fr) {
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
    return totalNames;
    //System.out.println("Total births " + totalBirths + " and Total names " + totalNames);
    //System.out.println("Total Boys " + totalBoys + " and boy names " + boyNames);
    //System.out.println("Total Girls " + totalGirls + " and girls names " + girlNames);
  }
  public Integer girlNames (FileResource fr) {
    int girlNames = 0;
    for (CSVRecord r : fr.getCSVParser(false)) {
      if (r.get(1).equals("F")) {
        girlNames++;    
      }
    }
    return girlNames;
  }
  public void testTotalBirths () {
    FileResource fr = new FileResource("yob1980.csv");
    totalBirths(fr);
  }
  public Integer getRank (Integer year, String name, String gender, FileResource fr) {
    //FileResource fr = new FileResource("yob" + year + ".csv");
    //FileResource fr = new FileResource("yob2012short.csv");
    //String answer = "";
    Integer rank = 1;
    for (CSVRecord r : fr.getCSVParser(false)) {
      if (r.get(1).equals(gender)) {
        if (r.get(0).equals(name)) {
          return rank;   
        }
        rank++;
      }
    }
    return -1;
  }
  public void testGetRank () {
    //System.out.println(getRank(1980, "Mason", "F"));
    //System.out.println(getRank(2014, "Mason", "M"));
    //System.out.println(getRank(1980, "John", "M" ));   
  }
  public String getName (Integer year, Integer rank, String gender, FileResource fr) {
    //FileResource fr = new FileResource("yob" + year + ".csv");
    String name = "NO NAME";
    for (CSVRecord r : fr.getCSVParser(false)) {
      //long currRank = parser.getCurrentLineNumber();
      long rCount = r.getRecordNumber();
      int girlCount = girlNames(fr);
      //System.out.println(r);
      if (gender.equals("F")) {
        if (rCount == rank && rCount <= girlNames(fr)) {
          return r.get(0);
        }
        rCount++;
      } else if (gender.equals("M")) {
          rCount = r.getRecordNumber() - girlCount;
          if (rCount == rank) {
          return r.get(0);
          }
          rCount++;
      } else if (r.get(1).equals(gender)) {
        rCount++;
      }
    }
    return name;
  }
  public void testGetName () {
    //System.out.println(getName(1980, 7282, "F"));   
  }
  public String whatIsNameInYear (String name, Integer year, Integer newYear, String gender) {
    String answer = "";
    //Integer rank = getRank(year, name, gender);
    //String newName = getName(newYear, rank, gender);
    //return name + " born in " + year + " would be " + newName + " in " + newYear; 
    return "bling";
  }
  public void testWhatIsNameInYear () {
      System.out.println(whatIsNameInYear("Jessica", 1980, 2012, "F"));
  }
  public int yearOfHighestRank(String name, String gender) {
    DirectoryResource dr = new DirectoryResource();
    int bigYear = 0;
    int highRank = 50000;
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      int yearPos = f.getName().indexOf("yob");
      int currYear = Integer.parseInt(f.getName().substring(yearPos+3, yearPos+7));
      int currRank = getRank(currYear, name, gender, fr);
      if (currRank < highRank) {
        //System.out.println("highRank " + highRank + " and currRank " + currRank);
        highRank = currRank;
        bigYear = currYear;
      }
    }
    if (highRank == -1) {
      return -1;   
    }
    return bigYear;
  }
  public double getAverageRank(String name, String gender) {
    DirectoryResource dr = new DirectoryResource();
    //double average = 0.0;
    double rankSum = 0.0;
    double fileCount = 0.0;
    for (File f : dr.selectedFiles()) {
      FileResource fr = new FileResource(f);
      int yearPos = f.getName().indexOf("yob");
      int currYear = Integer.parseInt(f.getName().substring(yearPos+3, yearPos+7));
      int currRank = getRank(currYear, name, gender, fr);
      if (currRank != -1) {
        rankSum += currRank;    
      }
      fileCount++;
    }
    //average = (rankSum / fileCount);
    return rankSum/fileCount;
  }
}
