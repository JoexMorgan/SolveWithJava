
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
  private String alphabet;
  private String shiftOne;
  private String shiftTwo;
  private int mainKeyOne;
  private int mainKeyTwo;
  private String encrypted;
  private String halfOne;
  private String halfTwo;
  public CaesarCipherTwo(int keyOne, int keyTwo) {
    mainKeyOne = keyOne;
    mainKeyTwo = keyTwo;
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shiftOne = alphabet.substring(keyOne) + alphabet.substring(0, keyOne);
    shiftTwo = alphabet.substring(keyTwo) + alphabet.substring(0, keyTwo);
  }
  public String encrypt(String input) {
    StringBuilder doubleEncrypted = new StringBuilder(input);
    for (int i = 0; i < doubleEncrypted.length(); i++) {
      char currChar = doubleEncrypted.charAt(i);
      int idx = alphabet.indexOf(Character.toUpperCase(currChar));
      if (idx != -1) {
      char newChar1 = shiftOne.charAt(idx);
      char newChar2 = shiftTwo.charAt(idx);
        if (i % 2 == 0) {
          if (Character.isLowerCase(currChar)) {
            doubleEncrypted.setCharAt(i, Character.toLowerCase(newChar1));
          } else {
            doubleEncrypted.setCharAt(i, newChar1);
          }
        } else {
          if (Character.isLowerCase(currChar)) {
            doubleEncrypted.setCharAt(i, Character.toLowerCase(newChar2));    
          } else {
            doubleEncrypted.setCharAt(i, newChar2);
          }
        }
      }
    }
    return doubleEncrypted.toString();
  }
  public String decrypt(String input) {
    //System.out.println("keyOne: " + keyOne + "\n" + "keyTwo: " + keyTwo);
    CaesarCipherTwo bc = new CaesarCipherTwo(26-mainKeyOne, 26-mainKeyTwo);
    return bc.encrypt(input);
  }
}
