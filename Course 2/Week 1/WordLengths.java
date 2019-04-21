
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
public class WordLengths {
  public StringBuilder countWordLengths(FileResource fr, int[] counts) {
    //FileResource fr = new FileResource("smallhamlet.txt");
    //int[] counts = new int[50];
    StringBuilder sb = new StringBuilder();
    for (String word : fr.words()) {
      int len = word.length();
      if (Character.isLetter(word.charAt(0)) == false) {
        len--;
      }
      if (Character.isLetter(word.charAt(len-1)) == false) {
        len--;    
      }
      counts[len]++;
    }
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0) {
        sb.append(counts[i] + " words of length " + i + "\n");  
      }
    }
    return sb;
  }
  public int indexOfMax(int[] values) {
    int maxDex = 0;
    for (int i : values) {
      if (values[i] > maxDex) {
        maxDex = i;
      }
    }
    return maxDex;
  }
  public void testCountWordLengths () {
    FileResource f = new FileResource("romeo.txt");
    int[] counts = new int[3000];
    System.out.println(countWordLengths(f, counts));
    System.out.println(indexOfMax(counts));
  }
 
}
