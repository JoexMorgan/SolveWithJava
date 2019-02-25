
/**
 * Write a description of quiz6 here.
 * 
 * @author joexcode
 * @version 96749.34
 */
import edu.duke.*;
import java.io.*;
public class quiz6 {
    public void aGetter() {
        URLResource file = new  URLResource("http://dukelearntoprogram.com/course2/data/manylinks.html");
   	for (String item : file.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
           	int beg = item.lastIndexOf("\"",pos);
           	int end = item.indexOf("\"", pos+1);
           	System.out.println(item.substring(beg+1,end));
               }
   	}
    }
}
