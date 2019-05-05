
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
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
  
  public void testVigenereBreaker () {
    //String message = "abcdefghijklm";
    VigenereBreaker vb = new VigenereBreaker();
    //FileResource fr = new FileResource();
    System.out.println(
    //vb.breakVigenere();
    //System.out.println(Arrays.toString(vb.tryKeyLength(fr.asString(), 4, 'e')));
  }
}
