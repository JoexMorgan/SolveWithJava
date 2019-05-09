
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
import java.io.*;
public class Tester {
  public void testCipher () {
    //CaesarCracker crack = new CaesarCracker();
    int[] key = new int[5];
    key[0] = 21;
    key[1] = 4;
    key[2] = 17;
    key[3] = 3;
    key[4] = 8;
    //v:21, e:4, r:17, d:3, i:8
    //f:5, l:11, u:20, t:19, e:4
    FileResource fr = new FileResource();
    VigenereCipher vc = new VigenereCipher(key);
    System.out.println(vc.decrypt(fr.asString()));
    
  }
  
  public void testBreakForLanguage () {
    FileResource fr = new FileResource("VigenereTestData/aida_keyverdi.txt");
    FileResource dictionary = new FileResource("dictionaries/Italian");
    VigenereBreaker vb = new VigenereBreaker();
    //System.out.println(Arrays.toString(vb.tryKeyLength(fr.asString(), 5, 'a')));
    System.out.println(vb.breakForLanguage(fr.asString(), vb.readDictionary(dictionary)).substring(0, 100));
  }
  
  public void testVigenereBreaker () {
    //String message = "abcdefghijklm";
    VigenereBreaker vb = new VigenereBreaker();
    //FileResource encryptedFile = new FileResource();
    FileResource encryptedFile = new FileResource("VigenereTestData/aida_keyverdi.txt");
    DirectoryResource dr = new DirectoryResource();
    HashMap<String, HashSet<String>> languages = new HashMap();
    for (File f : dr.selectedFiles()) {
        FileResource fr = new FileResource(f);
        languages.put(f.getName(), vb.readDictionary(fr));
    }
    
    //System.out.println(vb.breakForAllLangs(encryptedFile.asString(), languages).substring(0, 100));
    System.out.println(vb.breakForLanguage(encryptedFile.toString(), languages.get("Italian")));
    //vb.breakVigenere();
    //System.out.println(Arrays.toString(vb.tryKeyLength(fr.asString(), 4, 'e')));
  }
}
