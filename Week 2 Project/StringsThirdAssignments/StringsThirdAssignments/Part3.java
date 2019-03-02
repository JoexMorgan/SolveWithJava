
/**
 * Write a description of Part3 here.
 * 
 * @author joexcode
 * @version 96764.85
 */
import edu.duke.*;
public class Part3 {
    public float cgRatio(String dna) {
        int cgCount = 0;
        for (int i = 0; i<dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                cgCount++;
            }
        }
        return (float)cgCount / dna.length();
    }
    public void processGenes(StorageResource sr) {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        int lengthCount = 0;
        int highcgCount = 0;
        int longest = 0;
        for (String s : sr.data()) {
            if (s.length() > 60) {
                lengthCount++;
                System.out.println(s);
            }
            if (cgRatio(s) > 0.35) {
                highcgCount++;
                System.out.println(s);
            }
            if (s.length() > longest) {
                longest = s.length();
            }
        }
        System.out.println("number of genes longer than 60 chars: " + lengthCount);
        System.out.println("number of strings with cgCount > .35: " + highcgCount);
        System.out.println("the longest gene in sr is: " + longest + " chars");
    }
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    public String findGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int shortStop = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (startIndex == -1 || shortStop == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, shortStop + 3);
    }
    public StorageResource getAllGenes(String dna) {
        int currIndex = 0;
        StorageResource resource = new StorageResource();
        while (true) {
            String currGene = findGene(dna, currIndex);
            if (currGene.isEmpty()) {
                break;
            }
            resource.add(currGene);
            currIndex = dna.indexOf(currGene, currIndex) + currGene.length();
        }
        return resource;
    }
    public int countCTG(String dna) {
        int count = 0;
        for (int i = 0; i <= dna.length() - 3; i++) {
            if (dna.substring(i, i+3).equals("CTG")) {
                count++;
            }
        }
        return count;
    }
    public void testCountCTG() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println("CTG count is " + countCTG(dna));
    }
    public void testProcessGenes() {
        //FileResource fr = new FileResource("brca1line.fa");
        //for the quiz
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println("Total number of genes: " + 
                            getAllGenes(dna).size());
        StorageResource genes = getAllGenes(dna);
        processGenes(genes);
        //processGenes(dna.data());
        //five.add("ATGTTTTATTAG");
        //five.add("ATGTAA");
        //five.add("ATGCGCTAA");
        //five.add("ATGTACTAA");
        //five.add("ATGGATTTAGACGCATACGGCTGA");
        
    }
}
