
/**
 * Write a description of Part3 here.
 * 
 * @author joexcode
 * @version 96748.84
 */
import edu.duke.*;
import java.io.*;
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int occ = 0;
        for (int i = 0; i < stringb.length(); i++) {
            //int pos = stringb.indexOf(stringa);
       
            if (stringb.substring(i).startsWith(stringa)) {
                occ++;
        }
    }
    return occ >= 2;
    }
    public void testing() {
        String a = "by";
        String b = "A book by Abby Long";
        System.out.println(twoOccurrences(a, b));
        
        String c = "is";
        String d = "Resistance is futile";
        System.out.println(twoOccurrences(c, d));
        
        String e = "base";
        String f = "All your base are belong to us";
        System.out.println(twoOccurrences(e, f));
        
        String g = "an";
        String h = "banana";
        System.out.println(lastPart(g, h));
        
        String i = "zoo";
        String j = "forest";
        System.out.println(lastPart(i, j));
    }
    public String lastPart(String stringa, String stringb) {
        String last = "";
        if (stringb.contains(stringa)) {
            last = stringb.substring(stringb.indexOf(stringa) + stringa.length());
        } else {
            return stringb;
        }
        return last;
    }
}
