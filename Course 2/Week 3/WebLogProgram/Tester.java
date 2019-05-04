
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
        la.readFile("weblog1_log");
        //la.printAll();
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIP () {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog1_log");
      System.out.println(la.countUniqueIPs());
      System.out.println(la.uniqueIPVisitsOnDay("Mar 17").size());
      //System.out.println(la.uniqueIPVisitsOnDay("Sep 30"));
    }
    
    public void testIPRange () {
      LogAnalyzer la = new LogAnalyzer();
      la.readFile("weblog1_log");
      //System.out.println(la.countUniqueIPsInRange(200, 299));
      System.out.println(la.countUniqueIPsInRange(300, 399));
    }
}
