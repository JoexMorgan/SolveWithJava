
/**
 * This updates the last assignment in two ways:
 * A. Find a gene in a strand of DNA where the stop codon could be 
 * any of the three stop codons “TAA”, “TAG”, or “TGA”.
 *
 * B. Find all the genes (where the stop codon could be 
 * any of the three stop codons) in a strand of DNA.
 * 
 * @author joexcode
 * @version 96754.74
 */
public class Part1 {
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
    public void test() {
        System.out.print("\n");
        System.out.println(findStopCodon("ATGAATGGTATAA",4,"TAA"));
    }
    public void testFindGene() {
        //System.out.println(findGene("ATTAAAGATAAAGGGAAAT"));
        //System.out.println(findGene("AAATTTATGGAGTATGGTTAA"));
        //System.out.println(findGene("AATGGTATAATGATAGAATTAA"));
        //System.out.println(findGene("AATGGTATATTGGAATTATTGGGGGG"));
    }
    public void printAllGenes(String dna) {
        int currIndex = 0;
        while (true) {
            String currGene = findGene(dna, currIndex);
            if (currGene.isEmpty()) {
                break;
            }
            System.out.println(currGene);
            currIndex = dna.indexOf(currGene, currIndex) + currGene.length();
        }
        //System.out.println(printAllGenes("ATGTAATTTATGTGAAAAATGTAG"));
    }
}
