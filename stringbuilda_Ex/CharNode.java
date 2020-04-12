/**
 * Filename: CharNode.java
 * Name: *
 * Login: *
 * Date: March 7, 2020
 * Sources of Help: None
 *
 * Description: The class contains two private variables. It allows users to
 * get and/or set the current data and the CharNode object pointing to the next
 */

/**
 * Class header: The class contains one constructor, two getter methods and two
 * setter methods.
 */
public class CharNode{
  private char data;
  private CharNode next;


 /**
  * Constructor that initialize the instance variables
  *
  * @param c to initialize with
  */
  public CharNode(char c){
    data = c;
    next = null;
  }

 /**
  * Function that get the data
  *
  * @return the data
  */
  public char getData(){
    return this.data;
  }

 /**
  * Function that get the CharNode
  *
  * @return the CharNode next
  */
  public CharNode getNext(){
     return this.next;
   }

 /**
  * Function that update the data to newData and return the carnode
  *
  * @param newData to be updated to
  * @return the this CharNode
  */
  public CharNode setData(char newData){
    data = newData;
    return this;
  }

 /**
  * Function that update the next to newNext and return the carnode
  *
  * @param newNext to be updated to
  * @return the next CharNode
  */
  public CharNode setNext(CharNode newNext){
    next = newNext;
    return next;
   }

}
