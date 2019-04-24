
/**
 * Write a description of OOCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class testCaesarCipher {
  private int maxDex;
  private int dKey;
  private int[] freqs;
  public int[] countLetters(String message) {
    String alph = "abcdefghijklmnopqrstuvwxyz";
    int[] counts = new int[26];
    for (int k = 0; k < message.length(); k++) {
      char ch = Character.toLowerCase(message.charAt(k));
      int dex = alph.indexOf(ch);
      if (dex != -1) {
        counts[dex]++;    
      }
    }
    return counts;
  }
  public int maxIndex(int[] arr) {
    int maxDex = 0;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > arr[maxDex]) {
        maxDex = i;    
      }
    }
    return maxDex;
  }
  public void simpleTests () {
    //FileResource fr = new FileResource("romeo.txt");
    CaesarCipher cc = new CaesarCipher(15);
    //String cipher = cc.encrypt(fr.asString());
    System.out.println(cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
    //System.out.println(cipher);
    //System.out.println(breakCaesarCipher(cc.encrypt(fr.asString())));
  }
  public String breakCaesarCipher(String input) {
    freqs = countLetters(input);
    maxDex = maxIndex(freqs); 
    dKey = maxDex - 4;
    if (maxDex < 4) {
      dKey = 26 - (4 - maxDex);   
    }
    CaesarCipher cc = new CaesarCipher(26 - dKey);
    return cc.encrypt(input);
  }
}
