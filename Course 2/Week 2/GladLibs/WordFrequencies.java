
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class WordFrequencies {
  private ArrayList<String> myWords;
  private ArrayList<Integer> myFreqs;
  
  public WordFrequencies () {
    myWords = new ArrayList<String>();
    myFreqs = new ArrayList<Integer>();
  }
  
  public void findUnique () {
    myWords.clear();
    myFreqs.clear();
    FileResource fr = new FileResource();
    for (String s : fr.words()) {
      s = s.toLowerCase();
      int idx = myWords.indexOf(s);
      if (idx == -1) {
        myWords.add(s);
        myFreqs.add(1);
      } else {
        int value = myFreqs.get(idx);
        myFreqs.set(idx, value + 1);
      }
    }
  }
  
  public void tester () {
    findUnique();
    int maxDex = findIndexOfMax();
    System.out.println("Number of unique words: " + myWords.size());
    for (int i = 0; i < myWords.size(); i++) {
      if (myFreqs.get(i) > 100) {
      System.out.println(myFreqs.get(i) + "\t" + myWords.get(i)); 
      }
    }
    System.out.println("The word that occurs most often and its count are: " + myWords.get(maxDex) + " " + myFreqs.get(maxDex));
  }
  
  public int findIndexOfMax () {
    int maxVal = 0;
    int maxDex = 0;
    for (int i = 0; i < myFreqs.size(); i++) {
      if (myFreqs.get(i) > maxVal) {
        maxVal = myFreqs.get(i);
        maxDex = i;
      }
    }
    return maxDex;
  }
}
