/**
 * Filename: recursionHG.java
 * Name: ***
 * Login: ***
 * Date: January 24, 2020
 * Sources of Help: Given WeekTwoRecursive worksheet
 *
 * This file contains the functions necessary to draw an hourglass recursively.
 * It includes basic error checking and takes in two command line arguments.
 */

/**
 * This class is used to perform basic error checking and print the hourglass
 * onto the console.
 */

public class Hourglass {

    /** DO NOT MODIFY THESE CONSTANTS */
    // Used to determine if a number is even or odd
    private static final int EVEN_DIVISOR = 2;

    // Number of required arguments
    private static final int REQUIRED_ARGS = 1;

    // Used when width is not valid
    private static final String INVALID_WIDTH_ERR = "Width is not valid!\n";

    // Used when there are more arguments passed in than required
    private static final String EXTRA_ARGS_ERR = "Too many args passed in!\n";

    // Used when no arguments are passed in
    private static final String HELP_STR = "Usage: java Hourglass width\n" +
        "width has to be >= 1 and odd\n";

    // The character the hourglass will be drawn with
    private static final char HOURGLASS_CHAR = '*';

    // The character the padding will be drawn with
    private static final char PADDING_CHAR = ' ';

    /** Declare any constants that you may need here */
    private static final int LineChange = 2;

    /**
     * Main function that will read in command line arguments and start the
     * recursion if valid arguments were passed in
     *
     * @param args An array of all the command line arguments passed in
     */
    public static void main(String[] args) {

        // Print help message if no arguments are passed in
        if (args.length == 0){
          System.out.println(HELP_STR);

        // Check to make sure there is exactly one arg
      }else if(args.length > REQUIRED_ARGS){
          System.out.println(EXTRA_ARGS_ERR);

        // Parse the width and make sure it's valid
      }else if(Integer.parseInt(args[0]) % EVEN_DIVISOR == 0 ||
      Integer.parseInt(args[0]) < 0){
          System.out.println(INVALID_WIDTH_ERR);

        // If arg is valid, call the function
      }else{
          printHourglass(Integer.parseInt(args[0]), 0);
      }
    }

    /**
     * Recursive function that will print the hourglass with the specified width
     *
     * @param width   The width of the hourglass
     * @param padding The amount of padding to use to position the hourglass
     */
    protected static void printHourglass(int width, int padding) {

      if (width == 1){
        printLine(width, padding);
        return;
      }
        printLine(width, padding);
        printHourglass(width - LineChange, padding + 1);
        printLine(width, padding);
    }

    /**
     * Helper function that will print a line of the hourglass
     *
     * @param width   The width of the line in the hourglass
     * @param padding The amount of padding to use to position the line
     */
    protected static void printLine(int width, int padding) {
        // Print the padding character padding times
        for(int i = 0; i < padding; i++) {
            System.out.print(PADDING_CHAR);
        }

        // Print the hourglass character width times
        for(int i = 0; i < width; i++) {
            System.out.print(HOURGLASS_CHAR);
        }

        // Print the padding character padding times
        for(int i = 0; i < padding; i++) {
            System.out.print(PADDING_CHAR);
        }

        // Move to the next line (can also be placed at the top)
        System.out.println();
    }
}
