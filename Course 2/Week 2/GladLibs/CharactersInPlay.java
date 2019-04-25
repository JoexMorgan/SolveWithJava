
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
  private ArrayList<String> myNames;
  private ArrayList<Integer> myLines;
  public void update (String person) {
    int index = myNames.indexOf(person);
    if (index == -1) {
      myNames.add(person); 
      myLines.add(1);
    } else {
      int value = myLines.get(index);
      myLines.set(index, value + 1);
    }
  }
  public void findAllCharacters () {
    myNames = new ArrayList<String>();
    myLines = new ArrayList<Integer>();
    //myNames.clear();
    //myLines.clear();
    FileResource fr = new FileResource();
    for (String s : fr.lines()) {
      if (s.contains(".")) {
        String name = s.substring(0, s.indexOf("."));
        update(name);
      }
    }
  }
  public void tester () {
    findAllCharacters();
    charactersWithNumParts(31,42);
    /*for (int i = 0; i < myNames.size(); i++) {
      if (myLines.get(i) > 21) {
        System.out.println(myNames.get(i) + " speaks " + myLines.get(i) + " times in this play.");    
      }
    }*/
  }
  public void charactersWithNumParts(int num1, int num2) {
    for (int i = 0; i < myNames.size(); i++) {
      if (myLines.get(i) >= num1 && myLines.get(i) <= num2) {
        System.out.println(myNames.get(i) + "\t" + myLines.get(i));    
      }
    }
  }
}
