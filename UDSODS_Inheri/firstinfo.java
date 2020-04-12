/**
 * Filename: FifoList.java
 * Name: **
 * Login: **
 * Date: Febrary 10, 2020
 * Sources of Help: Lecture Note
 *
 * Description: The class extends OrderedDS class. It creates a first in first
 * out array and allows users to manipulate the array by making a deep copy,
 * adding, deleting, returning the peek value and size as well as printing the
 * array.
 */


/**
 * Class header: The class allows users to create and manipulate a first in
 * first out array.
 */
public class FifoList extends OrderedDS {
  private int maxSize;
  private int indexCounter;

  private static final String EMPTY_ERROR = "FifoList is empty";
  private static final String MAX_SIZE_ERROR = "FifoList maximum limit reached";
  private static final String EMPTY = "";
  private static final String FORMAT = "-";

  /**
   * Default construtor that initializes the value
   */
  public FifoList(){
    maxSize = 0;
    array = null;
  }

  /**
   * Construtor that takes in maxSize to create an array with maxSize length
   * @param maxSize
   */
  public FifoList(int maxSize){
    if (maxSize < 0){
      this.maxSize = 0;
      array = null;
    }else{
      this.maxSize = maxSize;
      array = new int[this.maxSize];
    }

  }


  /**
   * Construtor that takes in a fifolist and make a deep copy
   * @param q fifolist to deep copy from
   */
  public FifoList(FifoList q){
    array = new int[q.maxSize];
    for(int i = 0; i < q.maxSize; i++){
      array[i] = q.array[i];
    }
  }


  /**
   * functions that add an int element to the fifolist
   * @param element
   */
  public void add(int element){
    if(array == null || indexCounter >= maxSize){
      System.out.println(MAX_SIZE_ERROR);

    //add the element and increase the index by 1
    }else{
      array[indexCounter] = element;
      indexCounter += 1;
    }
  }

  /**
   * Function that delete the top element of the array and return the array
   * @return int of the number deleted
   */

  @Override
  public int delete(){
    if(array == null || array.length == 0){
      System.out.println(EMPTY_ERROR);
      return -1;
    }

    int temp = array[0];

    for(int i = 0; i < array.length; i++){
      //check for all elements except for the last one
      if(i < array.length - 1){
        //move each element one index up
        if(array[i] != 0 && array[i + 1] != 0){
          array[i] = array[i + 1];
        //check if the value is the last nonzero value in the array
        }else if(array[i + 1] == 0){
          array[i] = 0;
        }
      //place zero for the last element of the array
      }else{
        array[i] = 0;
      }
    }
    return temp;
  }

  /**
   * Function that returns the top element
   * @return integer that is the top element
   */
  @Override
  public int peek(){
    if(array == null || array.length == 0){
      System.out.println(EMPTY_ERROR);
      return -1;
    }
    return array[0];
  }

  /**
   * Function that returns the size of the FifoList
   * @return integer that represents the size of the array
   */
  public int size(){
    int index = 0;
    for(int i = 0; i < array.length; i++){
      if(array[i] != 0){
        index += 1;
      }
    }
    return index;
  }


  /**
   * Function that print the Fifolist as a string in the specified format
   * @return the FifoList as a string
   */
  public String toString(){
    if(array == null || array.length == 0){
      return EMPTY;
    }

    StringBuilder output = new StringBuilder();
    for(int i =0; i < array.length; i++){
      //make sure only nonzero index are printed
      if(array[i] != 0){
        output.append(array[i]);
        output.append(FORMAT);
      }
    }
    //delete the last dash
    output.deleteCharAt(output.length() -1);

    return output.toString();
  }


  public static void main(String[] args){
    FifoList f = new FifoList(5);
    f.add(7);
    f.add(9);
    f.add(2);
    f.add(6);
    f.add(3);
    //f.add(5);
    System.out.println(f.delete());
    System.out.println(f.delete());
    System.out.println(f.peek());
    System.out.println(f.toString());
    System.out.println(f.size());

  }

}
