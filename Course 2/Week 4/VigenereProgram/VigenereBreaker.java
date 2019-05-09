import java.util.*;
import edu.duke.*;
import java.io.*;

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
        CaesarCracker cc = new CaesarCracker(mostCommon);
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
      for (String s : message.split("\\W")) {
        if (dictionary.contains(s.toLowerCase())) {
          count++;   
        }
      }
      return count;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
      int max = 0;
      String broken = "";
      String key = "";
      char mostCommon = mostCommonCharIn(dictionary);
      for (int i = 1; i <= 100; i++) {
        int[] currKey = tryKeyLength(encrypted, i, mostCommon);
        VigenereCipher vc = new VigenereCipher(currKey);
        String message = vc.decrypt(encrypted);
        if (countWords(message, dictionary) > max) {
          max = countWords(message, dictionary); 
          broken = message;
          key = Arrays.toString(currKey);
          System.out.println("the key is: " + Arrays.toString(currKey));
          //System.out.println("decoded message is: " + broken);
          //System.out.println("key length is: " + currKey.length);
          System.out.println("valid words: " + max);
        }
      }
      return broken;
    }
    
    public void breakVigenere () {
      FileResource encrypted = new FileResource("SecretData/secretmessage4.txt");
      //FileResource encrypted = new FileResource("VigenereTestData/athens_keyflute.txt");
      DirectoryResource dr = new DirectoryResource();
      HashMap<String, HashSet<String>> languages = new HashMap();
      for (File f : dr.selectedFiles()) {
        FileResource fr = new FileResource(f);
        languages.put(f.getName(), readDictionary(fr));
      }
      System.out.println(breakForAllLangs(encrypted.asString(), languages));
      
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
      HashMap<Character, Integer> letterCounts = new HashMap<>();
      int max = 0;
      char mostCommon = ' ';
      for (String s : dictionary) {
        for (int i=0; i<s.length(); i++) {
          char currChar = s.toLowerCase().charAt(i);
          if (!letterCounts.containsKey(s.charAt(i))) {
            letterCounts.put(currChar, 1);
          } else {
            letterCounts.replace(currChar, letterCounts.get(currChar)+1);   
          }
        }
      }
      for (char ch : letterCounts.keySet()) {
        if (letterCounts.get(ch) > max) {
          max = letterCounts.get(ch);
          mostCommon = ch;
        }
      }
      return mostCommon;
    }
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
      int max = 0;
      String answer = "";
      String encryptionLanguage = "";
      HashMap<String, Integer> counts = new HashMap<>();
      for (String s : languages.keySet()) {
        String decrypted = breakForLanguage(encrypted, languages.get(s));
        int wordCount = countWords(decrypted, languages.get(s));
        counts.put(s, wordCount);
      }
      for (String lang: counts.keySet()) {
        if (counts.get(lang) > max) {
          max = counts.get(lang); 
          answer = breakForLanguage(encrypted, languages.get(lang));
          encryptionLanguage = lang;
        }
      }
      System.out.println("The file is originally in " + encryptionLanguage);
      return answer;
    }
}
