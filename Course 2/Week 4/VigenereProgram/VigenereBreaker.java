import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
      StringBuilder sb = new StringBuilder();
      for (int i=whichSlice; i<message.length(); i+=totalSlices) {
        sb.append(message.charAt(i));
      }
      return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for (int i=0; i<klength; i++) {
          StringBuilder sb = new StringBuilder();
          for (int j=i; j<encrypted.length(); j+=klength){
            sb.append(encrypted.charAt(j)); 
          }
          key[i] = cc.getKey(sb.toString());
        }
        return key;
    }
   
    public HashSet<String> readDictionary(FileResource fr) {
      HashSet<String> dictionary = new HashSet<>(); 
      for (String s : fr.lines()) {
        dictionary.add(s.toLowerCase());    
      }
      return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
      int count = 0;
      for (String s : message.split("\\W+")) {
        if (dictionary.contains(s.toLowerCase())) {
          count++;   
        }
      }
      return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
      int max = 0;
      String broken = "";
      for (int i = 1; i <= 100; i++) {
        int[] currKey = tryKeyLength(encrypted, i, 'e');
        VigenereCipher vc = new VigenereCipher(currKey);
        countWords(vc.decrypt(encrypted), dictionary);
        if (countWords(vc.decrypt(encrypted), dictionary) > max) {
          max = countWords(vc.decrypt(encrypted), dictionary); 
          broken = vc.decrypt(encrypted);
          System.out.println("the key is: " + Arrays.toString(currKey));
          System.out.println("key length is: " + currKey.length);
          System.out.println("valid words: " + max);
        }
      }
      System.out.println(max);
      //printed a shortened answer to read first line
      return broken.substring(0, 400);
    }
    
    public void breakVigenere () {
      FileResource fr = new FileResource();
      String message = fr.asString();
      FileResource words = new FileResource("dictionaries/English");
      HashSet<String> wordSet = readDictionary(words);
      System.out.println(breakForLanguage(message, wordSet));
    }
}
