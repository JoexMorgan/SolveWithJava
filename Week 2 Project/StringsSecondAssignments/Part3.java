
/**
 * Count how many genes are in a strand of DNA
 * 
 * @author joexcode 
 * @version 96762.96
 */
public class Part3 {
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
    public boolean hasGene(String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int shortStop = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (findGene(dna, where).length() > 0) {
            return true;
        }
        return false;
    }
    public int countGenes(String dna) {
        int currIndex = 0;
        int geneCount = 0;
        //String copy = "";
        for (int i = 0; i < dna.length(); i++) {
            String copy = findGene(dna, i);
            if (copy.length() > 0) {
                geneCount++;
                i += dna.indexOf(copy);
            }
            currIndex++;
        }
        return geneCount;
    }
    public void testCountGenes () {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
        //expected output: 2
        System.out.println(countGenes("ATGTAAATGTGAAATGTAG"));
        //expected output: 3           ^    ^^    ^^    ^
        System.out.println(countGenes("ATGTAAAATTGTGAATGTAG"));
        //expected output: 2
        System.out.println(countGenes("ATAGGTA"));
        //expected output: 0
        System.out.println(countGenes("ATGTAATGATAG"));
        //expected output: 1
    }
    public void findTest() {
        System.out.println(findGene("AATGCTAACTAGCTGACTAAT", 0) + "quiz 1");
        //System.out.println(findGene("ATGTAAGATGCCCTAGT", 0) + "ATGTAA");
        //System.out.println(findGene("ATGTAAGATGCCCTAGT", 6) + "ATGCCCTAG");
    }
}
