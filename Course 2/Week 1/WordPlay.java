
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class WordPlay {
  public boolean isVowel (Character ch) {
    Character vowels[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
    
    boolean vowel = false;
    for (int i = 0; i < vowels.length; i++) {
      if (vowels[i].equals(ch)) {
        vowel = true;    
      }
    }
    return vowel;
  }
  public void testIsVowel () {
    System.out.println(isVowel('a'));    
  }
  public StringBuilder replaceVowels(String phrase, Character ch) {
    StringBuilder replacement = new StringBuilder(phrase);
    for (int i = 0; i < phrase.length(); i++) {
      if (isVowel(phrase.charAt(i))) {
        replacement.setCharAt(i, ch);
      }
    }
    return replacement;
  }
  public void testReplaceVowels () {
    System.out.println(replaceVowels("Wick Wick Wiggity Wack", '1'));
  }
  public StringBuilder emphasize(String phrase, Character ch) {
    StringBuilder emphasized = new StringBuilder(phrase);
    for (int i = 0; i < phrase.length(); i++) {
      if (emphasized.charAt(i) == ch) {
        if (i % 2 == 0) {
          emphasized.setCharAt(i, '+');   
        } else {
          emphasized.setCharAt(i, '*');   
        }
      }
    }
    return emphasized;
  }
  public void testEmphasize () {
    System.out.println(emphasize("Black bread baked daily", 'a')); 
    System.out.println(emphasize("Elk eat eleven leaves every minute", 'e'));
  }
}
