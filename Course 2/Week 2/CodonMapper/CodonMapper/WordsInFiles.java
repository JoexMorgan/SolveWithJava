
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class WordsInFiles {
  private HashMap<String, ArrayList<String>> wordMap;
  public WordsInFiles () {
    wordMap = new HashMap<String, ArrayList<String>>();    
  }
  private void addWordsFromFile (File f) {
    FileResource fr = new FileResource(f);
    for (String s : fr.words()) {
      ArrayList<String> fileName = new ArrayList<String>();
      if (wordMap.get(s) == null) {
        fileName.add(f.getName());
        wordMap.put(s, fileName);
      } else if (!wordMap.get(s).contains(f.getName())){
        wordMap.get(s).add(f.getName());
      }
    }
  }
  public void buildWordFileMap () {
    wordMap.clear();
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      addWordsFromFile(f);   
    }
  }
  public int maxNumber () {
    int max = 0;
    for (String s : wordMap.keySet()) {
      if (wordMap.get(s).size() > max) {
        max = wordMap.get(s).size();    
      }
    }
    return max;
  }
  public ArrayList<String> wordsInNumFiles (int number) {
    ArrayList<String> wordsInNumFiles = new ArrayList<String>();
    for (String s : wordMap.keySet()) {
      if (wordMap.get(s).size() == number) {
        wordsInNumFiles.add(s);    
      }
    }
    return wordsInNumFiles;
  }
  public void printFilesIn (String word) {
    for (int i = 0; i < wordMap.get(word).size(); i++) {
      System.out.println(wordMap.get(word).get(i));
    }
  }
  public void tester () {
    buildWordFileMap();
    int max = maxNumber();
    for (String s : wordMap.keySet()) {
      if(s.equals("tree")) {
      //if (wordMap.get(s).size() == max) {
        System.out.println(s + ":\t" + wordMap.get(s));  
      }
    }
    //System.out.println(wordMap);
    System.out.println(wordsInNumFiles(7).size());
  }
}
