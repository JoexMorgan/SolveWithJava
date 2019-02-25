
/**
 * Write a description of Part4 here.
 * 
 * @author joexcode 
 * @version 96749.24
 */
import edu.duke.*;
import java.io.*;
public class Part4 {
    //public method for reading files -- needs r
    public void wordReader() {
        int linkStart = 0;
        int linkStop = 0;
        String linkName = "";
        URLResource ur = new URLResource("http://dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : ur.words()) {
            //System.out.println(s);
            if (s.toLowerCase().contains("youtube.com")) {
              System.out.println(s);
              linkStart = s.indexOf("\"");
              linkStop = s.indexOf("\"", linkStart+1);
              linkName += s.substring(linkStart, linkStop + 1) + "\n";
            }
            //linkName += s.substring(linkStart, linkStop+1);
            //System.out.println(linkName);
        }
        System.out.println(linkName);
    }
}
