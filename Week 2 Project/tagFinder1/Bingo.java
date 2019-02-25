
/**
 * Write a description of Bingo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class Bingo {
    public String findSimpleGene(String s) {
        String codon;
        int coStart = s.indexOf("ATG");
        int coStop = s.indexOf("TAA", coStart + 3);
        if (coStart >= 0 && coStop > 0 && (coStop - coStart) % 3 == 0) {
            codon = s.substring(coStart, coStop + 3);
        } else {
        codon = "failed";
        }
        //System.out.print(codon);
        return codon;
    }
    public void testSimpleGene()  {
        String dna1 = "CGAGTAAAAAA";
        System.out.println(findSimpleGene(dna1));
        
        String dna2 = "CGATGCAATGACTAGGGGCCGCAAAGAGAC";
        System.out.println(findSimpleGene(dna2));
        
        String dna3 = "CGATCGACTACAGACT";
        System.out.println(findSimpleGene(dna3));
        
        String dna4 = "ATGCACAGCAGTTACGAATAA";
        System.out.println(findSimpleGene(dna4));
        
        String dna5 = "ATGCGAACGATTAA";
        System.out.println(findSimpleGene(dna5));
    }
}
