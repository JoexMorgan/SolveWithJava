
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
     
     public HashMap<String, Integer> countVisitsPerIP () {
      HashMap<String, Integer> visitorLog = new HashMap<>();
      for (LogEntry le : records) {
        String visitor = le.getIpAddress();
        if (!visitorLog.containsKey(visitor)) {
          visitorLog.put(visitor, 1);   
        } else {
          visitorLog.replace(visitor, visitorLog.get(visitor) + 1);    
        }
      }
      return visitorLog;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> records) {
      //HashMap<String, Integer> visits = countVisitsPerIP();
      int mostVisits = 0;
      for (String s : records.keySet()) {
        if (records.get(s) > mostVisits) {
          mostVisits = records.get(s);   
        }
      }
      return mostVisits;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> records) {
      ArrayList<String> mostFrequentUsers = new ArrayList<>();
      int mostVisits = mostNumberVisitsByIP(records); 
      for (String s : records.keySet()) {
        if (records.get(s) == mostVisits) {
          mostFrequentUsers.add(s);   
        }
      }
      return mostFrequentUsers;
    }
    
    public HashMap<String, ArrayList<String>> iPsForDays () {
      HashMap<String, ArrayList<String>> visitorsOnDays = new HashMap<>();
      String day;
      for (LogEntry le : records) {
        ArrayList<String> visitorList = new ArrayList<>();
        day = le.getAccessTime().toString().substring(4, 10);
        if (!visitorsOnDays.containsKey(day)) {
          visitorList.add(le.getIpAddress());
          visitorsOnDays.put(day, visitorList);   
        } else {
          visitorList = visitorsOnDays.get(day);
          visitorList.add(le.getIpAddress());
          visitorsOnDays.replace(day, visitorList);   
        }
      }
      return visitorsOnDays;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> visitorsOnDays) {
      int mostVisits = 0;
      String popularDay = "";
      for (String s : visitorsOnDays.keySet()) {
        if (visitorsOnDays.get(s).size() > mostVisits) {
          mostVisits = visitorsOnDays.get(s).size();   
          popularDay = s;
        }
      }
      return popularDay;
    }
    
    public HashMap<String, Integer> visitorFrequencyOnDay(ArrayList<String> visitorsOnDay) {
      HashMap<String, Integer> visitsOnDay = new HashMap<>();
      for (String s: visitorsOnDay) {
        if (!visitsOnDay.containsKey(s)) {
          visitsOnDay.put(s, 1);
        } else {
          visitsOnDay.replace(s, visitsOnDay.get(s)+1);   
        }
      }
      return visitsOnDay;
    }
    
    public ArrayList<String> mostFrequentIPs(HashMap<String, Integer> visitsOnDay) {
      ArrayList<String> frequentIPs = new ArrayList<>();
      int max = 0;
      for (String s: visitsOnDay.keySet()) {
        if (visitsOnDay.get(s) > max) {
          max = visitsOnDay.get(s);      
        }
      }
      for (String s: visitsOnDay.keySet()) {
        if (visitsOnDay.get(s) == max && !frequentIPs.contains(s)) {
          frequentIPs.add(s);
        }
      }
      return frequentIPs;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String day) {
      ArrayList<String> mostFrequentVisitors = new ArrayList<>();
      for (String s : iPsForDays.keySet()) {
        if (s.equals(day)) {
          //mostFrequentVisitors = getMostFrequentIPsForDay(iPsForDays.get(s)); 
          //mostFrequentVisitors = iPsForDays.get(s);
          mostFrequentVisitors = mostFrequentIPs(visitorFrequencyOnDay(iPsForDays.get(s)));
          //visitorFrequencyOnDay(ArrayList) returns the HashMap needed for most FrequentIPs
          //mostFrequentIPs(HashMap) returns the answer for this function
        }  
      }
      return mostFrequentVisitors;
    }
}
