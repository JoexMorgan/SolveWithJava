
/**
 * cgRatio counts the number of times 'C' or 'G' occurs
 * in a codon and returns the count divided by the codon's length
 * countCTG counts the occurences of the codon CTG in a given dna string
 * 
 * @author joexcode
 * @version 96764.81
 */
public class Part2 {
    public float cgRatio(String dna) {
        int cgCount = 0;
        for (int i = 0; i<dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                cgCount++;
            }
        }
        return (float)cgCount / dna.length();
    }
    public void cgTest() {
        System.out.println(cgRatio("ATGCGCTAA"));
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
    public void countTest() {
        System.out.println("ATGATTTAA " + countCTG("ATGATTTAA"));
        //expected output: 0
        System.out.println("ATGCTGTAG " + countCTG("ATGCTGTAG"));
        //expected output: 1
        System.out.println("CTGCTGCTGCTG " + countCTG("CTGCTGCTGCTG"));
        //expected output: 4
    }
}