
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
}
