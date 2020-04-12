/**
 * Filename: RatingPredictor.java
 * Name: *
 * Login: *
 * Date: February 27, 2020
 * Sources of Help: Lecture note
 * String array to arraylist: https://stackoverflow.com/questions/19562516/
 * return-an-arraylist-method
 * removestopword: https://stackoverflow.com/questions/50907840/adding-contents
 * -of-text-files-to-a-hash-set
 * output file: https://stackoverflow.com/questions/13258441/saving-a-hashset-
 * to-a-file-in-java
 *
 * Description: The class can take in movie rating reviews and analyzes the
 * reviews by removing unnecessary punctuations, blank space, etc. Then it can
 * return a rating of that review.
 */

import java.util.*;
import java.io.*;
import  java.text.DecimalFormat;
/**
 * Class header: The class can split the reviews, remove punctuations, blank
 * space, stopwords. It can return a cleaned version of the reviews as a file
 * and then perform rating on the file based on reviews with rating.
 */
public class RatingPredictor{
  private HashMap<String, int[]> wordFreqMap;
  private HashSet<String> stopWords;
  public static final String space = " ";
  public static final String empty = "";
  public static final String hyphens = "-";
  public static final String single_quote = "'";
  public static final String punctuations = "\\p{Punct}";
  public static final String newLine = "\r\n";
  public static final String round = "#.#";
  public static final float two = 2.0f;
  public static final float zero = 0.0f;


  public static final String rawRR = "rawReviewRatings.txt";
  public static final String clearRR = "cleanReviewRatings.txt";
  public static final String rawReviews = "rawReviews.txt";
  public static final String clearReviews = "cleanReviews.txt";

  public static final String rawRRBig = "rawReviewRatingsBig.txt";
  public static final String clearRRBig = "cleanReviewRatingsBig.txt";
  public static final String rawReviewsBig = "rawReviewsBig.txt";
  public static final String clearReviewBig = "cleanReviewsBig.txt";


  public static final String stopword = "stopwords.txt";
  public static final String stopword_out = "uniqueStopwords.txt";
  public static final String ratings = "ratings.txt";


 /**
  * Default constructor that initialize the instance variables
  */
  public RatingPredictor(){
    wordFreqMap = new HashMap<String, int[]>();
    stopWords = new HashSet<String>();
  }

 /**
  * Functions that split the entire contents of one review into words
  * @param sentence of review to be rated
  * @return an arraylist of words
  */
  public ArrayList<String> splitLine (String sentence){
    if(sentence == null || sentence.length() == 0){
      return null;
    }else{
      //create a new arraylist to store elements in string array
      ArrayList<String> list = new ArrayList<String>();
      //create a string array for splited string by blank space
      String[] split = sentence.split(space);
      //use for loop to add elements in string array
      for(int i = 0; i < split.length;i++){
        list.add(split[i]);
      }
      return list;
    }
  }

 /**
  * Function that splits words in the arraylist at the hyphens and single quotes
  * @param words that to be splited
  * @return a modified arraylist of words
  */
  public ArrayList<String> splitAtHyphensAndQuotes (ArrayList<String> words){
    if(words == null || words.size() == 0){
      return null;
    }else{
      //create a new arraylist to store new strings
      ArrayList<String> list = new ArrayList<String>();

      String[] str; //store the split string array

      //iterate through the arraylist
      Iterator<String> l = words.iterator();
      while(l.hasNext()){
        String word = l.next();
        //check for hyphen and single quote in each string
        if(word.contains(hyphens)){
          //split at hyphens
          str = word.split(hyphens);
          //add elements in the string array to arraylist
          for(String i: str){
            list.add(i);
          }

        //check for single quote
        }else if(word.contains(single_quote)){
          str = word.split(single_quote);
          for( String i: str){
            list.add(i);
          }
        }else{
          list.add(word);
         }
       }
      return list;
     }
  }

 /**
  * Functions that remove all punctuation from the arraylist of words
  * @param words that contains punctuation to be removed
  * @return a modified arraylist of words
  */
  public ArrayList<String> removePunctuation (ArrayList<String> words){
    if(words == null || words.size() == 0){
      return null;
    }else{
      //create a new arraylist to store new strings
      ArrayList<String> list = new ArrayList<String>();
      //loop through each string in the arraylist to remove punctuation
      for(String str : words){
        str = str.replaceAll(punctuations, empty);
        //store the new string into a new arraylist
        list.add(str);
      }

      return list;
    }
  }

 /**
  * Functions that remove all the leading and trailing white spaces from the
  * ArrayList of words
  * @param words that contains leading and trailing white spaces to be removed
  * @return a modified arraylist of words
  */
  public ArrayList<String> removeWhiteSpaces (ArrayList<String> words){
    if(words == null || words.size() == 0){
      return null;
    }else{
      //create a new arraylist to store new strings
      ArrayList<String> list = new ArrayList<String>();
      //loop through each string in the arraylist to remove space
      for(String str : words){
        str = str.replace(space, empty);
        //store the new string into a new arraylist
        list.add(str);
      }

      return list;
    }
  }

 /**
  * Functions that remove all empty words from the ArrayList of words
  * @param words that contains empty words to be removed
  * @return a modified arraylist of words
  */
  public ArrayList<String> removeEmptyWords (ArrayList<String> words){
    if(words == null || words.size() == 0){
      return null;
    }else{
      //create a new arraylist to store new strings
      ArrayList<String> list = new ArrayList<String>();

      //iterate through the arraylist
      Iterator<String> l = words.iterator();
      while(l.hasNext()){
        String word = l.next();
        //check for empty word and remove
        if(word.equals(empty)){
          l.remove();
        }else{
          //store the new string into a new arraylist
          list.add(word);
        }
      }
      return list;
    }
  }

 /**
  * Functions that remove all single letter words from the ArrayList of words
  * @param words that contains all single letter words to be removed
  * @return a modified arraylist of words
  */
  public ArrayList<String> removeSingleLetterWords (ArrayList<String> words){
    if(words == null || words.size() == 0){
      return null;
    }else{
      //create a new arraylist to store new strings
      ArrayList<String> list = new ArrayList<String>();

      //iterate through the arraylist
      Iterator<String> l = words.iterator();
      while(l.hasNext()){
        String word = l.next();
        //check for single and letter and remove
        if(word.length() == 1){
          l.remove();
        }else{
          //store the new string into a new arraylist
          list.add(word);
        }
      }
      return list;
    }
  }

 /**
  * Functions that make all the words in the ArrayList to lower case
  * @param words that convert all the words in the ArrayList to lower case
  * @return a modified arraylist of words
  */
  public ArrayList<String> toLowerCase (ArrayList<String> words){
    if(words == null || words.size() == 0){
      return null;
    }else{
      //create a new arraylist to store new strings
      ArrayList<String> list = new ArrayList<String>();

      //iterate through the arraylist
      Iterator<String> l = words.iterator();
      while(l.hasNext()){
        String word = l.next();
        //check for single and letter
        word = word.toLowerCase();
        list.add(word);
      }
      return list;
    }
  }

 /**
  * Functions that remove all the stop words
  * @param words that contains stop words to be removed
  * @return a modified arraylist of words
  */
  public ArrayList<String> removeStopWords (ArrayList<String> arrList){
    if(arrList == null || arrList.size() == 0){
      return null;
    }else{
      try{
        //use scanner to import text file
        Scanner sc = new Scanner(new File(stopword));
        stopWords = new HashSet<String>();
        //fill the hashset with elements in file
        while(sc.hasNext()){
          stopWords.add(sc.next());
        }
      }catch(FileNotFoundException e){
        e.printStackTrace();
      }

      //create a new arraylist to store new strings
      ArrayList<String> list = new ArrayList<String>();

      //iterate through the arraylist
      Iterator<String> l = arrList.iterator();
      while(l.hasNext()){
        String word = l.next();
        //check if string is in stopword. If true, skip the word
        if(stopWords.contains(word)){
          continue;
        }else{
          list.add(word);
        }
      }
      return list;
    }
  }

// methods to hep with the rating prediction task
 /**
  * Functions that contain one stop word in each line and should not have any
  * duplicate stop words.
  * @param inFile to be created a hashset from
  * @param outFile to store the output hashset
  */
  public void createStopWordsSet (String inFile, String outFile){
    try{
      stopWords = new HashSet<String>();

      //use scanner to import text file
      Scanner sc = new Scanner(new File(inFile));
      //fill the hashset with elements in file
      while(sc.hasNext()){
        stopWords.add(sc.next());
      }

      //print the hashset to the outfile
      PrintWriter out = new PrintWriter(new File(outFile));
      for(String str : stopWords){
        out.println(str);
      }
      out.close();

    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

 /**
  * Functions that takes in an infile and output a outfile and boolean
  * @param inFile to be created a hashset from
  * @param outFile to store the cleaned data
  * @param ratingIncluded to differentiate between files that have rating and
  * reviews and files that only have reviews
  */
  public void cleanData(String inFile, String outFile, boolean ratingIncluded){
    try{
      //create scanner and printwriter
      Scanner sc = new Scanner(new File(inFile));
      PrintWriter out = new PrintWriter(new File(outFile));

      while(sc.hasNextLine()){
        String line = sc.nextLine();
        ArrayList<String> sL = new  ArrayList<String> ();

        if(ratingIncluded == true){
          out.print(line.charAt(0) + space);
        }

        //clean the data
        sL = splitLine(line);
        sL = splitAtHyphensAndQuotes(sL);
        sL = removePunctuation(sL);
        sL = removeWhiteSpaces(sL);
        sL = removeEmptyWords(sL);
        sL = removeSingleLetterWords(sL);
        sL = toLowerCase(sL);
        sL = removeStopWords(sL);

        for(String str : sL){
          out.print(str + space);
        }
        out.println();
      }
      out.close();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

 /**
  * Function that takes in the cleaned data file and use it to update HashMap
  * @param inCleanFile to be created a hashset from
  */
  public void updateHashMap(String inCleanFile){
    try{
      wordFreqMap = new HashMap<String, int[]>();

      //create scanner
      Scanner sc = new Scanner(new File(inCleanFile));
      while(sc.hasNextLine()){
        int count = 0;
        int scoreforLine = 0;
        String line = empty;

        //print the line one by one
        line = sc.nextLine();

        //store each word in one line into string array
        String[] words = line.split(space);

        //store the score for elements in the line
        for(int i = 0; i < words.length; i++){
          if(i == 0){
            scoreforLine = Integer.parseInt(words[i]);
          }else{
              //check if map contain the string
              if(wordFreqMap.containsKey(words[i])){
                //update the map
                int[] arr = wordFreqMap.get(words[i]);
                arr[0] += scoreforLine;
                arr[1] += 1;
                wordFreqMap.put(words[i], arr);
              //if the string is a new one
              }else{
                //add the string to the map
                int[] arr = new int[] {scoreforLine, 1};
                wordFreqMap.put(words[i], arr);
              }
             }
            }
         }
      }catch(FileNotFoundException e){
        e.printStackTrace();
      }
   }

 /**
  * Function that predicts the ratings for the reviews in this cleaned file
  * @param inCleanFile to be created a hashset from
  * @param outRatingsFile to be created a hashset from
  */
  public void rateReviews (String inCleanFile, String outRatingsFile){
    try{
      Scanner sc = new Scanner(new File(inCleanFile));
      PrintWriter out = new PrintWriter(new File(outRatingsFile));
      DecimalFormat value = new DecimalFormat(round);
      while(sc.hasNextLine()){
        float sum = zero;
        String line = empty;

        //print the line one by one
        line = sc.nextLine();
        //store each word in one line into string array
        String[] words = line.split(space);
        for(String str: words){

          //check if map contain the string
          if(wordFreqMap.containsKey(str)){
            //update the sum
            int[] arr = wordFreqMap.get(str);
            float temp = (float)arr[0]/arr[1];
            sum += temp;
          //if the map does not contain the string, assign default value
          }else{
            sum += two;
          }
        }
        out.println(value.format((sum/words.length)));

      }
      out.close();
    }catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public static void main (String[] args) throws FileNotFoundException{
    RatingPredictor r = new RatingPredictor();
    String s = "The Jungle-Book is a fantastic movie! It's the best!!";
    //System.out.println(r.splitLine(s));
      //System.out.println(punctuation);
    ArrayList<String> new1 = new ArrayList<String> ();
    ArrayList<String> new2 = new ArrayList<String> ();
    ArrayList<String> new3 = new ArrayList<String> ();
    ArrayList<String> new4 = new ArrayList<String> ();
    ArrayList<String> new5 = new ArrayList<String> ();
    ArrayList<String> new6 = new ArrayList<String> ();
    ArrayList<String> new7 = new ArrayList<String> ();
    ArrayList<String> new8 = new ArrayList<String> ();
    new1 = r.splitLine(s);
    //new1 = r.splitLine("-       - -");
    new2 = r.splitAtHyphensAndQuotes(new1);
    new3 = r.removePunctuation(new2);
    new4 = r.removeWhiteSpaces(new3);
    new5 = r.removeEmptyWords(new4);
    new6 = r.removeSingleLetterWords(new5);
    new7 = r.toLowerCase(new6);
    new8 = r.removeStopWords(new7);
    //System.out.println(new1);
    //System.out.println(new8);
    r.cleanData(rawRR, clearRR,true);
    r.updateHashMap(clearRR);
    r.rateReviews(clearReviews,ratings);
  }
 }
