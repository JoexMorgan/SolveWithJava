
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
  private String alphabet;
  private String shifted;
  private int mainKey;
  public String encrypt(String input) {
    StringBuilder encrypted = new StringBuilder(input);
    for (int i = 0; i < encrypted.length(); i++) {
      char currChar = encrypted.charAt(i);
      int idx = alphabet.indexOf(Character.toUpperCase(currChar));
      if (idx != -1) {
        char newChar = shifted.charAt(idx);
        if (Character.isLowerCase(encrypted.charAt(i))) {
         encrypted.setCharAt(i, Character.toLowerCase(newChar));   
        } else {
          encrypted.setCharAt(i, newChar);
    }
      }
    }
    return encrypted.toString();
  }
  public CaesarCipher(int key) {
    mainKey = key;
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    shifted = alphabet.substring(key) + (alphabet.substring(0, key));  
  }
  public String decrypt(String input) {
    CaesarCipher cc = new CaesarCipher(26 - mainKey);
    return cc.encrypt(input);
  }
  public void testEncrypt () {
    System.out.println(encrypt("Every pie I eat makes me feel free!")); 
    //System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
    //System.out.println(encrypt("Lujyfwapvu huk zljbypaf hyl mbukhtluahs whyaz vm avkhf'z Pualyula.", 19));   
    
  }
  /*
  public String encryptTwoKeys (String input, int key1, int key2) {
    StringBuilder doubleEncrypted = new StringBuilder(input);
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String shifted1 = encrypt(alphabet, key1);
    String shifted2 = encrypt(alphabet, key2);
    for (int i = 0; i < doubleEncrypted.length(); i++) {
      char currChar = doubleEncrypted.charAt(i);
      int idx = alphabet.indexOf(Character.toUpperCase(currChar));
      if (idx != -1) {
      char newChar1 = shifted1.charAt(idx);
      char newChar2 = shifted2.charAt(idx);
        if (i % 2 == 0) {
          if (Character.isLowerCase(doubleEncrypted.charAt(i))) {
            doubleEncrypted.setCharAt(i, Character.toLowerCase(newChar1));
          } else {
            doubleEncrypted.setCharAt(i, newChar1);
          }
        } else {
          if (Character.isLowerCase(doubleEncrypted.charAt(i))) {
            doubleEncrypted.setCharAt(i, Character.toLowerCase(newChar2));    
          } else {
          doubleEncrypted.setCharAt(i, newChar2);
          }
        }
      }
    }
    return doubleEncrypted.toString();
  }
  public void testEncryptTwoKeys () {
    //System.out.println(encryptTwoKeys("First Legion", 23, 17));    
    System.out.println(encryptTwoKeys("He prefers the perverse French esthetes: Verne, Péret, Genet, Perec – hence, he pens fervent screeds, then enters the street, where he sells these letterpress newsletters, three cents per sheet.", 8, 21));
  }
  */
}
