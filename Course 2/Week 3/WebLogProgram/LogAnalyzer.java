
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String s : fr.lines()) {
           records.add(WebLogParser.parseEntry(s)); 
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num) {
       ArrayList<Integer> uniqueStatusList = new ArrayList<>();
       for (LogEntry le : records) {
         if (le.getStatusCode() > num) {
           //System.out.println(le);  
           if (!uniqueStatusList.contains(le.getStatusCode())) {
             uniqueStatusList.add(le.getStatusCode());    
           }
         }
       }
       System.out.println(uniqueStatusList);
     }
     
     public int countUniqueIPs () {
       ArrayList<String> uniqueIPs = new ArrayList<String>();
       for (LogEntry le : records) {
         if (!uniqueIPs.contains(le.getIpAddress())) {
           uniqueIPs.add(le.getIpAddress());    
         }
       }
       return uniqueIPs.size();
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
       ArrayList<String> uniqueVisitorList = new ArrayList<>();
       for (LogEntry le : records) {
         //System.out.println(le.getAccessTime().toString());
         if (!uniqueVisitorList.contains(le.getIpAddress()) 
         && le.getAccessTime().toString().substring(4, 10).equals(someday)) {
           uniqueVisitorList.add(le.getIpAddress());    
         }
       }
       return uniqueVisitorList;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
       ArrayList<String> rangeList = new ArrayList<>();
       for (LogEntry le : records) {
         if (le.getStatusCode() >= low && le.getStatusCode() <= high
             && !rangeList.contains(le.getIpAddress())) {
           rangeList.add(le.getIpAddress()); 
         }
       }
       return rangeList.size();
     }
}
