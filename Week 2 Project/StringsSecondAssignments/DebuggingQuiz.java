
/**
 * Write a description of DebuggingQuiz here.
 * 
 * @author joexcode
 * @version 96764.41
 */
public class DebuggingQuiz {
    public void findAbc(String input) {
    int index = input.indexOf("abc");
    while (true) {
        if (index == -1 || index >= input.length() - 3) {
            //System.out.println("not again my dude");
            break;
        }
        String found = input.substring(index+1, index+4);
        System.out.println(found);
        index = input.indexOf("abc", index+3);
        //System.out.println(index);
    }
}
   public void test() {
    //findAbc("abcdabc");
    findAbc("abcabcabcabca");
    //findAbc("yabcyabc");
}
}
