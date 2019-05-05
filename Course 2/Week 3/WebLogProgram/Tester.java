
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        la.printAll();
        //la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIP () {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog2_log");
      System.out.println("unique IPs: " + la.countUniqueIPs());
      System.out.println(la.uniqueIPVisitsOnDay("Sep 27").size());
      //System.out.println(la.uniqueIPVisitsOnDay("Sep 30"));
    }
    
    public void testIPRange () {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog2_log");
      //System.out.println(la.countUniqueIPsInRange(200, 299));
      System.out.println(la.countUniqueIPsInRange(400, 499));
    }
    
    public void testCountVisitsPerIP () {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog2_log");
      HashMap<String, Integer> visitorLog = la.countVisitsPerIP();
      System.out.println(la.countVisitsPerIP());
      //System.out.println("Number of most visits is: " + la.mostNumberVisitsByIP(visitorLog));
      //System.out.println("Most frequent visitor(s) are: " + la.iPsMostVisits(visitorLog));
      //System.out.println("Visitors for each day are: " + la.iPsForDays());
      //System.out.println("Most visits occurred on: " + la.dayWithMostIPVisits(la.iPsForDays()));
      System.out.println("Ips that visited most on Sep 30 are: " + la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }
    
}
