
/**
 * Write a description of Part2 here.
 * 
 * @author joexcode
 * @version 96762.92
 */
public class Part2 {
    public int howMany(String a, String b) {
        //find number of times a occurs in b
        //no over lap
        int count = 0;
        int currIndex = 0;
        while (currIndex < b.length() - a.length()) {
        //while (currIndex != -1) {
            String copy = b.substring(currIndex, a.length()+currIndex);
            if (copy.equals(a)) {
                count++;
                currIndex = b.indexOf(copy, currIndex);
            //} else {
            //    currIndex++;
            }
        currIndex++;
        }
    return count;
    }
    public void test() {
        System.out.println(howMany("zoo","forest"));
        //expected output: 0
        System.out.println(howMany("an","manzana"));
        //expected output: 2
        System.out.println(howMany("wood","how much wood would a woodchuck chuck if a woodchuck could chuck wood?"));
        //expected output: 4
    }
}
