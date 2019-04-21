
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
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
  public String decrypt(String encrypted) {
    CaesarCipher cc = new CaesarCipher();
    int[] freqs = countLetters(encrypted);
    int maxDex = maxIndex(freqs);
    int dKey = maxDex - 4;
    if (maxDex < 4) {
      dKey = 26 - (4 - maxDex);
    }
    return cc.encrypt(encrypted, 26-dKey);
  }
  public void testDecrypt () {
    System.out.println(decrypt("Bsbov mfb F bxq jxhbp jb cbbi cobb!"));    
  }
  public String halfOfString(String message, int start) {
    String half;
    StringBuilder sb = new StringBuilder();
    for (int i = start; i < message.length(); i+=2) {
      sb.append(message.charAt(i)); 
    }
    half = sb.toString();
    return half;
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
  
  public void test () {
    //System.out.print(countLetters("eeeeeeeeeeeeeeeeeeeeeeeeee"));
    FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
    System.out.println(decryptTwoKeys(fr.asString()));
    //System.out.print(decryptTwoKeys("Pz kzznzzn opz kmmdzznm Nmmikc zaopzbza: Qmmvz, Xézzb, Bmimo, Xzzzk – pzvxm, cm xzvn ammdzvo nkmmzln, bcmi zvomma bcm aozzmo, ecmmm pz nmgtn opzaz gmobzzkzzan imragmobzzn, bczzm kzvoa xzz acmzb."));
  }
  
  public String decryptTwoKeys(String encrypted) {
    CaesarCipher cc = new CaesarCipher();
    String halfOne = halfOfString(encrypted, 0);
    String halfTwo = halfOfString(encrypted, 1);
    int keyOne = getKey(halfOne);
    int keyTwo = getKey(halfTwo);
    System.out.println("Key One: " + keyOne + "\n" + "Key Two: " + keyTwo);

    return cc.encryptTwoKeys(encrypted, 26 - keyOne, 26 - keyTwo);
  }
}
