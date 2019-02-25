
/**
 * Write a description of Part2 here.
 * 
 * @author joexcode 
 * @version 96748.83
 */
import edu.duke.*;
import java.io.*;
public class Part2 {
    public String findSimpleGene (String s, String startCodon, String stopCodon) {
        String cased = s.toUpperCase();
        String codon = "";
        int coStart = cased.indexOf("ATG");
        int coStop = cased.indexOf("TAA", coStart + 3);
        if (coStop > 0 && coStart >= 0 && (coStop - coStart) % 3 == 0) {
            codon += cased.substring(coStart, coStop + 3);
        } else {
            return "does not contain codon";
        }
        return codon;
    }
    public void testSimpleGene () {
        String dna1 = "CGAGTAAAAAA";
        System.out.println(findSimpleGene(dna1,"ATG", "TAA"));
        String dna2 = "CGATGCAATGACTAGGGGCCGCAAAGAGAC";
        System.out.println(findSimpleGene(dna2, "ATG", "TAA"));
        String dna3 = "CGATCGACTACAGACT";
        System.out.println(findSimpleGene(dna3, "ATG", "TAA"));
        String dna4 = "ATGCACAGCAGTTACGAATAA";
        System.out.println(findSimpleGene(dna4, "ATG", "TAA"));
        String dna5 = "ATGCGAACGATTAA";
        System.out.println(findSimpleGene(dna5, "ATG", "TAA"));
    }
}
