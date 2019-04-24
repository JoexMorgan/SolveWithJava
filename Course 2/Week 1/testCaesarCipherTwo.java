
/**
 * Write a description of testCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
public class testCaesarCipherTwo {
  private String halfOne;
  private String halfTwo;
  //private int keyOne;
  //private int keyTwo;
  
  private String halfOfString(String message, int start) {
    String half;
    StringBuilder sb = new StringBuilder();
    for (int i = start; i < message.length(); i+=2) {
      sb.append(message.charAt(i)); 
    }
    half = sb.toString();
    return half;
  }
  
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
  
  public int getKey(String s) {
    int [] values = countLetters(s);
    int maxDex = maxIndex(values);
    int key = maxDex - 4;
    if (maxDex < 4) {
      key = 26 - (4 - maxDex);
    }
    return key;
  }
  
  public void simpleTests () {
    FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
    String text = fr.asString();
    CaesarCipherTwo cc2 = new CaesarCipherTwo(21, 8);
    String encrypted = cc2.encrypt(text);
    //System.out.println(breakCaesarCipher(text));
    
    System.out.println(cc2.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?"));
    //System.out.println(cc2.decrypt("Hfs cpwewloj loks cd Hoto kyg Cyy."));
    //System.out.println(breakCaesarCipher("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));
    //System.out.println(breakCaesarCipher(encrypted));
    //System.out.println(encrypted);
    //System.out.println(fr.asString());
    //System.out.println(cc2.decrypt(encrypted));
  }
  
  public String breakCaesarCipher(String input) {
    halfOne = halfOfString(input, 0);
    halfTwo = halfOfString(input, 1);
    int keyOne = getKey(halfOne);
    int keyTwo = getKey(halfTwo);
    CaesarCipherTwo cct = new CaesarCipherTwo(26-keyOne, 26-keyTwo);
    System.out.println("keyOne: " + keyOne + "\n" + "keyTwo: " + keyTwo);
    return cct.encrypt(input);
  }
}
