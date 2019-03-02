
/**
 * cgRatio counts the number of times 'C' or 'G' occurs
 * in a codon and returns the count divided by the codon's length
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
}
