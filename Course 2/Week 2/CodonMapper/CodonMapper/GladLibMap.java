import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private ArrayList<String> usedList;
    private ArrayList<String> usedCategories;
    private HashMap<String, ArrayList<String>> gladMap;
    
    private Random myRandom;
    
    private int subCount = 0;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        gladMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        gladMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String category : categories) {
          ArrayList<String> replacements = readIt(source + "/" + category + ".txt");
          gladMap.put(category, replacements);
        }
        usedList = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String category) {
        if (category.equals("number")) {
          return ""+myRandom.nextInt(50)+5;   
        }
        return randomFrom(gladMap.get(category));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if (usedList.indexOf(sub) == -1) {
          usedList.add(sub);
          usedCategories.add(w.substring(first+1,last));
          subCount++;
        } else {
          sub = getSubstitute(w.substring(first+1,last));
        }
        //subCount++;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("\n" + subCount + " words were replaced.");
        System.out.println(totalWordsInMap() + " replacement words were available.");
        //System.out.println(usedList);
        //System.out.println(usedCategories);
        System.out.println(totalWordsConsidered() + " words were considered.");
        usedList.clear();
        usedCategories.clear();
    }
    public void nonRepeatTest () {
      for (int i = 0; i < 50; i++) {
        makeStory();    
      }
    }
    public int totalWordsInMap () {
      int totalWords = 0;
      for (String s : gladMap.keySet()) {
        totalWords += gladMap.get(s).size(); 
      }
      return totalWords;
    }
    public int totalWordsConsidered () {
      int consideredWords = 0;
      ArrayList<String> unique = new ArrayList<String>();
      for (String s : usedCategories) {
        if (unique.indexOf(s) == -1) {
          unique.add(s);      
        }
      }
      for (String s : unique) {
        if (!s.equals("number")) {
        consideredWords += gladMap.get(s).size();
        }
      }
      return consideredWords;
    }
}
