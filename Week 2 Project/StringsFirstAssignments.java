public class Part1 {
    public String findSimpleGene (String s) {
        String codon = "";
        int coStart = s.indexOf("ATG");
        int coStop = s.indexOf("TAA", coStart + 3);
        if ((coStop - coStart) % 3 == 0) {
            codon = s.substring(coStart, coStop + 3);
        }
        return codon;
    }
    public void testSimpleGene () {
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
/*public class Part2 {
    public String findSimpleGene (String s) {
        String codon = "";
        int coStart = s.indexOf("ATG");
        int coStop = s.indexOf("TAA", coStart + 3);
        if ((coStop - coStart) % 3 == 0) {
            codon = s.substring(coStart, coStop + 3);
        }
        return codon;
    }
    public void testSimpleGene () {
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
}*/