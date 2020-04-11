/*
 * Filename: PA1.java
 * Name: Yingxi Long
 * Login: cs8bwi20dk
 * Date: January 13, 2020
 * Sources Used:
 * [1]Handling negative numbers by Mod in java:
 * https://stackoverflow.com/questions/5385024/mod-in-
 * java-produces-negative-numbers
 * [2]Convert string to char/char[]:
 * https://docs.oracle.com/javase/6/docs/api/java/lang/String.html#charAt(int)
 *
 * The purpose of this profile is to review concepts in CSE8A.
 * By using four methods, it reviewes the ASCII table, integer intArray
 * 2D integer array, and arithmetic calculations.
 */
import java.util.*;

/**
 * The class PA1 contains the four methods demonstrating concepts in CSE8A
 */
class PA1 {

   /**
    * Main method is for testing all other methods
    */
    public static void main(String[] args) {
        printASCIIValues("William");
        int[] intArray = new int[]{1,2,3,4,5};
        rotate1DArray(intArray, 1);
        int[][] int2DArray = new int[][]{
          {1,2,3},
          {4,5,6},
          {7,8,9}
        };
        rotate2DArray(int2DArray, -1, false);
        printIntroduction("Dope pur");

    }

    /**
     * Prints the ascii value of each letter in input
     *
     * @param input Input to print ASCII values for
     */
    public static void printASCIIValues(String input) {
        for (int i = 0; i < input.length(); i++){
          char c = input.charAt(i);
          System.out.println( c + " " + (int)c);
        }
    }

    /**
     * Returns the rotated 1D Array based on rotations
     *
     * @param input 1D integer array to rotate for
     * @param rotations integer to decide how many times and the direction
     * the rotations occur
     * @return a rotated 1D integer array
     */
    public static int[] rotate1DArray(int[] input, int rotations) {
        int[] rotatedArray = new int[input.length];
        // make sure the remainder by the module is positive (see Reference[1])
        int r = ((rotations % input.length)+input.length) % input.length;
        for (int i = 0; i < input.length; i++){
              rotatedArray[i] = input[(i - r + input.length) % input.length];
              //System.out.println(rotatedArray[i]);
            }
        return rotatedArray;
    }

    /**
     * Returns the rotated the row or col of a 2D Array based on rotations
     *
     * @param input 2D integer array to rotate for
     * @param rotations integer to decide how many times and the direction
     * the rotations occur
     * @param rotateRows condition to check whether rotate rows or columns
     * @return a rotated 2D integer array
     */
    public static int[][] rotate2DArray(int[][] input, int rotations,
    boolean rotateRows) {
        int row = input.length;
        int col = input[0].length;
        int[][]rotated2DArray = new int[row][col];
        int rotate = ((rotations % input.length)+input.length) % input.length;

        //if rotate row
        if (rotateRows){
          for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
              rotated2DArray[r][c] = input[(r - rotate + input.length)
              % input.length][c];
              System.out.print(rotated2DArray[r][c]);
            }
              System.out.println();
          }
        //if rotate column
        }else{
          for(int r = 0; r < row; r ++){
              rotated2DArray[r] = rotate1DArray(input[r], rotate);
          }for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
              System.out.print(rotated2DArray[r][c]);
            }
              System.out.println();
          }
        }
        return rotated2DArray;
    }

    /**
     * print a message along with input name in an asterisk box
     *
     * @param name name to print along with the message
     */
    public static void printIntroduction(String name) {
        int row;
        int col;
        int totalRow = 5;
        String hi;
        String words;
        String ast = "*";
        char cast = ast.charAt(0);
        String spa = " ";
        char cspa = spa.charAt(0);

        //check whether name is null or an empty string
        if (name == null || name.equals("")){
          //decide the size(column specifically) of the array
          col = 29;
          //decide the text to put in the box
          hi = " Hello";
          words = hi + "! Welcome to CSE 8B! ";
        }else{
          int l = name.length();
          col = 30 + l;
          hi = " Hello ";
          words = hi + name + "! Welcome to CSE 8B! ";
        }
        char[] cwords = words.toCharArray();
        char[][] intro = new char[totalRow][col];

        for(row = 0; row < totalRow; row ++){
          if (row == 0 || row == totalRow - 1){
          //creates upper and lower asterisk bound
          for(int column = 0; column < col; column++){
            intro[row][column] = cast;
            }
          //creates the second last upper, lower bound with asterisk and space
          }else if(row == 1 || row == totalRow - 2){
            for(int column = 1; column < col - 1; column++){
              intro[row][column] = cspa;
            }
            intro[row][0] = cast;
            intro[row][col - 1] = cast;
          //fill in the reminding row with message and name
          }else{
            intro[row][0] = cast;
            intro[row][col - 1] = cast;
            for(int column = 1; column < col- 1; column++){
              intro[row][column] = cwords[column - 1];
            }
          }
        }
          for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
              System.out.print(intro[r][c]);
        }
        System.out.println();
      }
    }
}
