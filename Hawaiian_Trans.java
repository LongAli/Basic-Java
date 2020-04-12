/**
 * Filename: Hawaiian_Trans.java
 * Name: ***
 * Login: ***
 * Date: January 21, 2020
 * Sources of Help: Given Week2 discussion worksheet
 *
 * Description: Pronounce allows users to get the intuitive pronunciation
 * of Hawaiian words. It also allows users to upload and convert their text
 * file in English into text file in Hawaiian.
 */

import java.io.*;
import java.util.Scanner;

/**
 * Class header: Pronounce allows users to get the intuitive pronunciation
 * of Hawaiian words
 */
public class Pronounce {

    /** DO NOT MODIFY THESE CONSTANTS */
    private static final char[] alphabet = {'a','e','i','o','u','p','k','h',
        'l','m','n','w',' ', '\''};
    private static final char[] vowels = {'a','e','i','o','u'};
    private static final String INPUT_USAGE = "i: convert single word\nf: " +
        "convert file\nq: quit";
    private static final String PROMPT_STR = "Enter a command: ";
    private static final String CONVERT_WORD_PROMPT = "Enter word(s) " +
        "to convert: ";
    private static final String IN_FILE_PROMPT = "Enter the name of the " +
        "file to convert: ";
    private static final String OUT_FILE_PROMPT = "Enter the name of the " +
        "file to dump the converted strings: ";
    private static final String USAGE = "`java Pronounce -h` to print help\n" +
        "`java Pronounce -f [infilename] [outfilename]` to translate file\n" +
        "`java Pronounce` to read and translate user input" ;
    private static final String PADDING = " ";
    private static final String EMPTY = "";
    private static final int PAIR_LENGTH = 2;
    private static final char SPACE_CHAR = ' ';
    private static final char QUOTE_CHAR = '\'';
    private static final char DASH_CHAR = '-';
    private static final char A_CASE = 'a';
    private static final char E_CASE = 'e';
    private static final char I_CASE = 'i';
    private static final char F_CASE = 'f';
    private static final char Q_CASE = 'q';
    private static final char O_CASE = 'o';
    private static final char U_CASE = 'u';
    private static final String AI_CASE = "ai";
    private static final String AE_CASE = "ae";
    private static final String AO_CASE = "ao";
    private static final String AU_CASE = "au";
    private static final String EI_CASE = "ei";
    private static final String EU_CASE = "eu";
    private static final String IU_CASE = "iu";
    private static final String OI_CASE = "oi";
    private static final String OU_CASE = "ou";
    private static final String UI_CASE = "ui";
    private static final String HELP_CASE = "-h";
    private static final String FILE_CASE = "-f";

    /** Declare any constants that you may need here */

    /**
     * Helper function that checks whether a given character is a vowel
     *
     * @param ch Character to check whether it is a vowel
     * @return True if a vowel else false
     */
    protected boolean isVowel(char ch)
    {
        for (int i = 0; i < vowels.length; i++){
          if (ch == vowels[i]){
            return true;
          }
        }
        return false;
    }

    /**
     * Helper function that checks whether a given character is valid
     *
     * @param ch Character to check whether it is a valid character
     * @return True if it is valid else false
     */
    protected boolean isValidChar(char ch)
    {
      for (int i = 0; i < alphabet.length; i++){
        if (ch == alphabet[i]){
          return true;
        }
      }
        return false;
    }

    /**
     * Helper function that checks whether a given string contains only valid
     * character
     *
     * @param str String to check whether it is valid
     * @return True if it contains only valid characters else false
     */
    protected boolean isValidString(String str)
    {
      //check if the string is null
      if(str == null){
        return false;
      }

      //base case
      if(str.equals(EMPTY)){
        return true;
      }

      //recursively checking if character is valid
      if(isValidChar(str.charAt(0))){
        return isValidString(str.substring(1));
      }else{
        return false;
      }
    }

    /**
     * Functions that convert a hawaiian word and returns its pronunciation in
     * words as output. It will call a helper recursive function to convert
     * the words.
     *
     * @param str String to convert into its pronunciation
     * @return String that represents its pronunciation
     */
    public String convert(String str)
    {
      //check if the str is null or empty string
      if (str == null || str.equals(EMPTY)){
        return str;
      }

      //convert all the valid string and capitalized the first character
      if (isValidString(str.toLowerCase())){
        String convert = convertHelper(str.toLowerCase());
        return Character.toUpperCase(convert.charAt(0)) + convert.substring(1);
      }else{
        return str;
      }
    }

    /**
     * Helper recursive function that will convert the words into the correct
     * pronunciation by following the grammar rules.
     *
     * @param str String to convert into its pronunciation
     * @return String that represents its pronunciation
     */
    protected String convertHelper(String str)
    {
        // base case of empty string returns empty string
        if(str.equals(EMPTY))
        {
          return str;
        }

        // special pronunciation for vowels
        if(isVowel(str.charAt(0)))
        {
            String pronunciation = EMPTY;

            // two consecutive vowel check,
            // fallback to one vowel for two vowels that aren't a pair
            if(str.length()>1 && isVowel(str.charAt(1)))
            {
                String vowelPair = str.substring(0, PAIR_LENGTH);

                switch(vowelPair)
                {
                    case AI_CASE:
                    case AE_CASE:
                        pronunciation = "eye";
                        break;
                    case AO_CASE:
                    case AU_CASE:
                    case OU_CASE:
                        pronunciation = "ow";
                        break;
                    case EI_CASE:
                        pronunciation = "ay";
                        break;
                    case EU_CASE:
                        pronunciation = "eh-oh";
                        break;
                    case IU_CASE:
                        pronunciation = "ew";
                        break;
                    case OI_CASE:
                        pronunciation = "oy";
                        break;
                    case UI_CASE:
                        pronunciation = "ooey";
                        break;
                }

                if(pronunciation.length()!=0)
                {
                    if(str.length()>PAIR_LENGTH &&
                        str.charAt(PAIR_LENGTH)!=SPACE_CHAR &&
                        str.charAt(PAIR_LENGTH)!=QUOTE_CHAR)
                    {
                        pronunciation += DASH_CHAR;
                    }

                    //Recurse when first is vowel pair
                    return pronunciation
                     + convertHelper(str.substring(PAIR_LENGTH));
                }
            }

            char vowel = str.charAt(0);

            switch(vowel)
            {
                case A_CASE:
                    pronunciation = "ah";
                    break;
                case E_CASE:
                    pronunciation = "eh";
                    break;
                case I_CASE:
                    pronunciation = "ee";
                    break;
                case O_CASE:
                    pronunciation = "oh";
                    break;
                case U_CASE:
                    pronunciation = "oo";
                    break;
            }

            if(str.length()>1 &&
                str.charAt(1)!=SPACE_CHAR &&
                str.charAt(1)!=QUOTE_CHAR)
            {
                pronunciation += DASH_CHAR;
            }

            //Recurse when first char is a vowel but not vowel group
            return pronunciation + convertHelper(str.substring(1));
        }
        else
        {
            //Recurse when first char is not a vowel
            return Character.toString(str.charAt(0))
             + convertHelper(str.substring(1));
        }
    }

    /**
     * Read in a file, convert the valid words into hawaiian pronunciation
     * and write it to a new file.
     *
     * @param infilename Filename to read in that contains hawaiian word
     * @param outfilename Filename to write the pronunciation to
     */
    protected void convertFile(String infilename, String outfilename)
    {
        Scanner sc = null;
        PrintWriter pw = null;

        // Try, catch and finally blocks are needed to read and write from file
        try
        {
            // Define a new scanner and print writer
            File inputFile = new File(infilename);
            Scanner sc1 = new Scanner(inputFile);

            File outputFile = new File(outfilename);
            PrintWriter pw1 = new PrintWriter(outputFile);

            // Continue reading if there is more to read
            String line = EMPTY;
            while (sc1.hasNext()){
              String s1 = convert(sc1.nextLine());
              line = line.concat(s1);
            }
            pw1.println(line);

        }
        catch(Exception e)
        {
            // do nothing
        }
        finally
        {
            // Make sure to close the stream for scanner and print writer
            if(sc!=null)
            {
                sc.close();
            }

            if(pw!=null)
            {
                pw.close();
            }
        }
    }

    /**
     * Get the user input and perform actions such as converting a single
     * word, convert all words from a file based on the input
     */
    protected void readUserInput()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(INPUT_USAGE);

        // continually loop
        while(true)
        {
            System.out.print(PROMPT_STR);
            char command = (sc.nextLine()+PADDING).charAt(0); // handle empty

            switch(command)
            {
                case I_CASE:
                    System.out.print(CONVERT_WORD_PROMPT);
                    String word = sc.nextLine(); // the user's input
                    System.out.println(convert(word));
                    break;
                case F_CASE:
                    System.out.print(IN_FILE_PROMPT);
                    String infilename = sc.nextLine();
                    System.out.print(OUT_FILE_PROMPT);
                    String outfilename = sc.nextLine();
                    convertFile(infilename, outfilename);
                    break;
                case Q_CASE:
                    sc.close();
                    System.exit(0); // this exits the program "normally"
                default:
                    System.out.println(INPUT_USAGE);
            }
        }
    }

    /**
     * Main function that will read in command line arguments and start the
     * conversion if valid arguments were passed in
     *
     * @param args An array of all the command line arguments passed in
     */
    public static void main(String[] args)
    {
      Pronounce p = new Pronounce();
      int ArgsLen = 3;

      //print out the instruction of using the program when asked
      if(args.length != 0 && args[0].equals(HELP_CASE)){
        System.out.println(USAGE);
        return;

      //print out either the convert file or the instruction when needed
      }else if(args.length != 0 && args[0].equals(FILE_CASE)){

        //convert file if args are valid
        if(args.length == ArgsLen){
          p.convertFile(args[1], args[ArgsLen - 1]);
        //print out instruction when args are invalid
        }else{
          System.out.println(USAGE);
        }
        return;

      //print out converted arguments
      }else if(args.length != 0){
        for (int i = 0; i < args.length; i++){
          System.out.println(p.convert(args[i]));
        }
        return;

      //print out converted user input
      }else{
          p.readUserInput();
      }
    }
}
