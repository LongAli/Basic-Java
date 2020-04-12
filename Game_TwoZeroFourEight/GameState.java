
/**
 * Filename: GameState.java
 * Name: ***
 * Login: ***
 * Date: February 6, 2020
 * Sources of Help: For using random class: Week 4 DI worksheet
 *
 * Description: This class contains constructor, getters and setters,
 * and movement, which allows users to get and set the board and score as well
 * as manipulate the movement of the game.
 */

import java.util.Random;
/**
 * Class header: This class includes three major parts of the game state:
 * constructor, getters and setters, and movement. The class allows users to
 * interact with the game by setting and getting the game board and score and
 * controling movements such as rotating the board and sliding down.
 */
public class GameState{
  private Random rng;
  private int[][] board;
  private int score;
  public static final int probability = 100;
  public static final String LINE_SEP = "\n";
  public static final String FORMAT1 = "Score: %d\n";
  public static final String FORMAT2 = "    -";
  public static final String FORMAT3 = "%5d";


 /**
  * Functions that displays the game board in the correct format
  *
  * @return String of each number of the game board
  */
  public String toString (){
    StringBuilder outputString = new StringBuilder();
    outputString.append(String.format(FORMAT1, getScore()));
    for (int row = 0; row < getBoard().length; row++) {
        for (int column = 0; column < getBoard()[0].length; column++) {
            outputString.append(getBoard()[row][column] == 0 ? FORMAT2 :
                String.format(FORMAT3, getBoard()[row][column]));
        }
        outputString.append(LINE_SEP);
    }
    return outputString.toString();
  }

  /**
   * Constructor that takes in two parameters to create a game board
   *
   * @param numRows of the board
   * @param numCols of the board
   */
  public GameState (int numRows, int numCols){
    this.board = new int[numRows][numCols];
    score = 0;
  }

  //getters and setters
  /**
   * Functions that return a deep copy of the board
   *
   * @return a deep copy of the calling 2D integer array
   */
  public int[][] getBoard(){
    int[][] copyboard = new int[this.board.length][this.board[0].length];
    for (int r = 0; r < this.board.length; r++){
      for (int c = 0; c < this.board[0].length; c++){
        if (this.board[r][c] == 0){
          copyboard[r][c] = 0;
        }else{
          copyboard[r][c] = this.board[r][c];
        }
      }
    }
    return copyboard;
  }

  /**
   * Functions that make a deep copy of the input board and set the board of
   * the class to be the deep-copied board
   *
   * @param newBoard to deep copy from and set to
   */
  public void setBoard (int[][] newBoard){
    if(newBoard == null){
      return;
    }
    this.board = newBoard;
    this.board = getBoard();
  }

  /**
   * Functions that return the current score
   *
   * @return the score value
   */
  public int getScore(){
    return this.score;
  }

  /**
   * Functions that set the current score to be the input score
   *
   * @param newScore to bet set to
   */
  public void setScore (int newScore){
    this.score = newScore;
  }

  //generating tiles
  /**
   * Helper function that return a random integer between 0 and bound
   *
   * @param bound of the range of random integer
   */
  protected int rollRNG (int bound){
    this.rng = new Random();
    return this.rng.nextInt(bound);
  }

  /**
   * Helper function that decide which kind of tile should be added
   *
   * @return specific integer based on probability
   */
  protected int randomTile(){
    this.rng = new Random();
    int val = this.rng.nextInt(probability);
    if (val < Config.TWO_PROB){
      val = Config.TWO_TILE;
    }else{
      val = Config.FOUR_TILE;
    }
    return val;
  }

  /**
   * Helper function that return the number of empty tiles on the board
   *
   * @return the number of empty tiles on the board
   */
  protected int countEmptyTiles(){
    int count = 0;
    for (int r = 0; r < this.board.length; r++){
      for (int c = 0; c < this.board[0].length; c++){
          if (this.board[r][c] == 0){
            count += 1;
          }
      }
    }
    return count;
  }

  /**
   * Helper functions that add a new tile to the board
   *
   * @return the type of the tile added
   */
  protected int addTile(){
    if(countEmptyTiles() == 0){
      return 0;
    }
    //choose a random set of empty tiles
    int num = rollRNG(countEmptyTiles());
    //count until match with the randomly generated num
    int counter = 0;
    //generate a random tile
    int random = randomTile();

    //loop through each set of empty tiles
    for (int r = 0; r < getBoard().length; r++) {
        for (int c = 0; c < getBoard()[0].length; c++) {
          //if the set is found, perform addition
          if(counter == num && this.board[r][c] == 0){
            this.board[r][c] += random;
            return random;
          //else count for empty tiles
          }else if(this.board[r][c] == 0){
            counter += 1;
          }
        }
    }
    return random;
  }

  //movement
  /**
   * Functions that rotate the board counter-clockwise once
   *
   */
  protected void rotateCounterClockwise(){
    int[][] rotatedboard = new int[this.board[0].length][this.board.length];
    for (int r = 0; r < this.board[0].length; r++){
      for (int c = 0; c < this.board.length; c++){
        rotatedboard[r][c] = this.board[c][this.board[0].length - 1 - r];
      }
    }
    this.board = rotatedboard;
  }

  /**
   * Functions that determine whether sliding down is possible
   *
   * @return True if sliding dowm is possible
   */
  protected boolean canSlideDown(){

    //loop through columns
    for (int c = 0; c < this.board[0].length; c++){
      boolean zero = false;
      int previous = 0; //integer to keep track of the previous checked tile

      //loop from the bottom of the column
      for(int r = this.board.length - 1; r >= 0; r--){

        //check if have encountered 0 before
        if(zero){
          //check if there is a number tile above 0
          if(this.board[r][c] != 0){
            return true;
          }

        //no zero before
        }else{
          //check for 0
          if (this.board[r][c] == 0){
            zero = true;
          }else{
            //check if the looping tile is equal to the previous checked tile
            if(this.board[r][c] == previous){
              return true;
            }
            //set the previous checked tile
            previous = this.board[r][c];
          }
        }
      }
    }
    return false;
  }

  /**
   * Functions that determine if movement in any direction is possible
   *
   * @return True if no movement is possible
   */
  public boolean isGameOver(){
    int[][] newboard = getBoard();
    //loop through each side of the grid
    for(int i = 0; i < Config.DEFAULT_SIZE; i++){
      if (this.canSlideDown() == false){
        this.rotateCounterClockwise();
      }else{
        this.board = newboard;
        return false;
      }
    }
    this.board = newboard;
    return true;
  }

  /**
   * Functions that slide the board down and increase the score
   *
   * @return True if sliding down is successful
   */
  protected boolean slideDown(){
   if(isGameOver()== true){
      return false;
    }
    //check if sliddown happens
    boolean slidedown = false;
    //loop through columns
    for (int c = 0; c < this.board[0].length; c++){

      //keep track of previous checked nonzero value
      int previous = 0;
      //keep track of the current nonzero index
      int currindex = 0;
      //count for available slidings
      int zerocounter = 0;

      //loop from the bottom of the column
      for(int r = this.board.length - 1; r >= 0; r--){
        //if the tile is nonzero
        if(this.board[r][c] != 0){
          //keep the current nonzero index
          currindex = r;

          //check if the two tiles can be combined
          if(previous == this.board[r][c]){
            //increase score
            this.score += previous + this.board[r][c];
            //counting zero
            zerocounter += 1;
            //set the previous recorded index to the added value
            this.board[currindex + zerocounter][c] = previous+this.board[r][c];
            //assign zero to the original position after sliding
            this.board[r][c] = 0;
            //reset previous value for next loop
            previous = this.board[currindex][c];
            slidedown = true;

          //check if the tile can move down
          }else if((currindex + zerocounter) != r){
            //slide the tile down based on current index and zerocounter
            this.board[currindex + zerocounter][c] = this.board[r][c];
            //assign zero to the original position after sliding
            this.board[r][c] = 0;
            //reset previous for future use
            previous = this.board[currindex + zerocounter][c];
            slidedown = true;
          //set previous for future use
          }else{
            previous = this.board[currindex][c];
          }

        //if the tile is zero, count it
        }else{
          zerocounter += 1;
        }
       }
     }
     return slidedown;
   }

  /**
   * Functions that slide the board in the specified direction
   * @param dir direction that the board slide to
   * @return True if sliding down is successful
   */
  public boolean move(Direction dir){
    if(dir == null){
      return false;
    }
    //get the time to rotate counterclockwise to slide down
    for(int i = 0; i< dir.getRotationCount();i++){
      rotateCounterClockwise();
    }

    //check if slideDown is possible
    boolean check = slideDown();
    //rotate to the ultimate-wanted position
    for(int i = 0; i < (Config.DEFAULT_SIZE - dir.getRotationCount());i++){
      rotateCounterClockwise();
    }
    //rotate back and add tile
    if(check){
      addTile();
    }
    return check;
  }

/*
  public static void main(String[] args){
    GameState game = new GameState(3,2);
    game.board = new int [][]{{2, 0},{2, 0},{0, 0}};

    boolean ch = game.move(Direction.LEFT);
    for(int r =0; r < game.board.length; r++){
      for (int c =0; c < game.board[0].length; c ++){
        System.out.print(game.board[r][c]);
      }
      System.out.print("\n");

    }
    //System.out.print(ch);
  }
*/
}
