
/**
 * Filename: workingwithMatrix.java
 * Name: ***
 * Login: ***
 * Date: January 30, 2020
 * Sources of Help: Lecture note and worksheet
 *
 * Description: This program allows users to create and manipulate matrices,
 * such as get the element at specified cell, print the matrix in specified
 * format, add and multiply two matrices, and transpose a matrix, etc..
 */

/**
 * Class header: The class Matrix includes various functions that can create a
 * matrix, copy a matrix, set and get elements in specified cell, print the
 * matrix in specified format, add and multiply two matrices, and transpose
 * a matrix.
 */
public class Matrix {

    private int row;
    private int column;
    private int[][] arr;
    private static final String ADD_ERROR = "Matrices cannot be added";
    private static final String MULTIPLY_ERROR = "Matrices cannot" +
    " be multiplied";

    /**
     * Default construtor that initializes instance variables
     */
    public Matrix(){
      this.row = 0;
      this.column = 0;
      this.arr = null;
    }

    /**
     * Construtor that takes in row and column to create a 2D array
     * @param row of the 2D array
     * @param column of the 2D array
     */
    public Matrix(int row, int column){
      if (row <= 0 || column <= 0){
        this.row = 0;
        this.column = 0;
        this.arr = null;
        return;
      }

      //create a row * col matrix if args are valid
      this.row = row;
      this.column = column;
      this.arr = new int[this.row][this.column];
    }


    /**
     * Construtor that takes in a 2D array and make a deep copy of it
     * @param mat to create a deep copy from
     */
    public Matrix(Matrix mat){

      //assign value to the copied matrix
      this.row = mat.getRows();
      this.column = mat.getColumns();
      this.arr = new int[this.row][this.column];

      //loop through the matrices for deep copy
      for (int r = 0; r < this.row; r++){
        for (int c = 0; c < this.column; c++){
          this.arr[r][c] = mat.arr[r][c];
        }
      }
    }

    /**
     * Functions that check whether the input is invalid and set the value of a
     * particular element in the matrix
     * @param r of the targeted cell
     * @param c of thet argeted cell
     * @param element the value that the cell will be set to
     * @return True if the cell has been successfully set to element
     */
    public boolean setElement(int r, int c, int element){
      if (r < 0 || r >=this.row || c < 0|| c >= this.column){
        return false;
      }

      //set value to the specified cell
      this.arr[r][c] = element;
      return true;
    }
    /**
     * Functions that take in the row and column of the targeted cell and
     * get the value of that targeted cell
     * @param r of the targeted cell
     * @param c of thet argeted cell
     * @return integer of the targeted cell
     */
    public Integer getElement(int r, int c){
      if (r < 0 || r >=this.row || c < 0|| c >= this.column){
        return null;
      }
      return this.arr[r][c];
    }

    /**
     * Functions that return the number of rows in the matrix
     * @return number of rows
     */
    public int getRows(){
      return this.row;
    }

    /**
     * Functions that return the number of columns in the matrix
     * @return number of columns
     */
    public int getColumns(){
      return this.column;
    }

    /**
     * Functions that print the matrix in the specified format
     * @return matrix in specified format
     */
    @Override
    public String toString(){

      String str = "";
      //loop through the matrix to get the value of each cell
      for (int r = 0; r < this.row; r++){
        for (int c = 0; c < this.column; c++){

          //connect all the values and format the output
          String temp = this.arr[r][c] + " ";
          str = str.concat(temp);
        }
        //create a new line for each iteration
        str = str.concat("\n");
      }
      return str;
    }

    /**
     * Functions that add two matrices together
     *@param Matrix to add
     *@return the sum matrix object
     */
    public Matrix add(Matrix y){
      Matrix addresult = new Matrix(row, column);

      //check if the matrices in the appropriate format for addition
      if (y == null){
        return null;
      }

      if (this.row != y.getRows() || this.column != y.getColumns()){
        System.out.println(ADD_ERROR);
        return null;
      }

      for (int r = 0; r < row; r++){
        for (int c = 0; c < column; c++){

          //perform addition for each cell
          addresult.arr[r][c] = this.arr[r][c] + y.arr[r][c];
        }
      }
      return addresult;
    }

    /**
     * Functions that multiply two matrices together
     *@param Matrix to multiply
     * @return the multiplied matrix object
     */
    public Matrix multiply(Matrix y){
      //check if the matrices in the appropriate format for multiplication
      if (y == null){
        return null;
      }
      Matrix mulresult = new Matrix(this.row, y.column);
      if (this.column != y.getRows()){
        System.out.println(MULTIPLY_ERROR);
        return null;
      }
      //loop for the rows of first matrix
      for (int r = 0; r < mulresult.getRows(); r++){
        //loop for the columns of second matrix
        for (int c = 0; c < mulresult.getColumns(); c++){
          //loop for the each number
          for (int k = 0; k < this.getColumns(); k++){
              //perform multiply for each cell
              mulresult.arr[r][c] += this.arr[r][k] * y.arr[k][c];
          }
        }
      }
      return mulresult;
    }

    /**
     * Functions that perform matrix transpose
     * @return the transposed version of the matrix
     */
    public Matrix transpose(){
      Matrix tresult = new Matrix(this.column, this.row);

      //transpose the matrix
      for (int r = 0; r < tresult.getRows(); r++){
        for (int c = 0; c < tresult.getColumns(); c++){
          tresult.arr[r][c] = this.arr[c][r];
        }
      }
      return tresult;
    }




}
