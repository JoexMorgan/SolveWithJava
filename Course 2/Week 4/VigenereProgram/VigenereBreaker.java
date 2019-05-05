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

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] key = tryKeyLength(message, 5, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(message));
    }
    
}
