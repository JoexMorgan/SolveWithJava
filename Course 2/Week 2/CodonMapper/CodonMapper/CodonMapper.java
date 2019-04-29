
/**
 * Write a description of CodonMapper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class CodonMapper {
  private HashMap<String, Integer> codons;
  public CodonMapper ()  {
    codons = new HashMap<String, Integer>();    
  }
  
  public void buildCodonMap (int start, String dna) {
    codons.clear();
    for (int i = start; i < dna.length()-2; i+=3) {
      String codon = dna.substring(i, i+3); 
      if (codons.get(codon) == null && Character.isLetter(codon.charAt(2))) {
      //System.out.println(codons.get("blub"));
        codons.put(codon, 1);     
      } else if (codons.get(codon) != null) {
         codons.put(codon, codons.get(codon) + 1);   
      }
    }
    //System.out.println(codons.toString());
  }
  public String getMostCommonCodon () {
    int max = 0;
    String codon = "";
    for (String s : codons.keySet()) {
      if (codons.get(s) > max) {
        max = codons.get(s);
        codon = s;
      }
    }
    return codon;
  }
  public void printCodonCounts (int start, int end) {
    for (String s : codons.keySet()) {
      if (codons.get(s) >= start && codons.get(s) <= end) {
        System.out.println(s + "\t" + codons.get(s));      
      }
    }
  }
  public void tester () {
    FileResource fr = new FileResource();
    //String dna = fr.toString().toUpperCase();
    for (int i = 0; i < 3; i++) {
      buildCodonMap(i, fr.asString().toUpperCase());
      printCodonCounts(1, 2);
      System.out.println("Most common codon is: " + getMostCommonCodon() + " with count " + codons.get(getMostCommonCodon()));
      
    }
  }
  /*
  public void printCodonCounts (int start, int end) {
    FileResource fr = new FileResource("smalldna.txt");
    String dna = fr.asString();
    for (int i = 0; i <= 2; i++) {
      buildCodonMap(i, dna);  
      for (String s : codons.keySet()) {
        if (codons.get(s) >= start && codons.get(s) <= end) {
          System.out.println(s + "\t" + codons.get(s));
          //System.out.println(getMostCommonCodon());
        }
      }
    }
  }*/
}
