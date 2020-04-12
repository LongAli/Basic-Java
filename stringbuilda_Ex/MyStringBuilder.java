/**
 * Filename: MyStringBuilder.java
 * Name: *
 * Login: *
 * Date: February 6, 2020
 * Sources of Help: Lecture note
 *
 * Description: The class allows users to manipulate string similar to string
 * builder. It contains three constructors and various methods that allows
 * users to manipulate and retrieve elements in the MyStringBuilder.
 */

/**
 * Class header: The class contains methods that allows users to get the length,
 * elements at specific index, print out the string, append, insert, remove,
 * delete elements as well as get substring of a MSB object. It also allows to
 * concat two MSB objects
 */
public class MyStringBuilder{
  protected CharNode start;
  protected CharNode end;
  protected int length;
  public static final String MESSAGE = " Problem here ";
  public static final String EMPTY = "";


  /**
   * Constructor that creates a mystringbuilder with single CharNode
   *
   * @param c as an input
   */
   public MyStringBuilder(char c){
     CharNode charnode = new CharNode(c);
     start = charnode;
     end = start;
     length = 1;
   }

  /**
   * Constructor that creates a mystringbuilder from a string
   *
   * @param s to create a mystringbuilder from
   */
   public MyStringBuilder(String s) throws BadInputException{
     if(s == null){
       throw new BadInputException(Constants.FROM_CONSTRUCTOR, MESSAGE);
     }else if (s.length() != 0){
       for(int i =0; i < s.length(); i++){
         this.append(s.charAt(i));
       }
     }
   }

  /**
   * Constructor that make a deep copy of the input
   *
   * @param MSB to be deep copied from
   */
   public MyStringBuilder(MyStringBuilder MSB) throws BadInputException{
     if(MSB == null){
       throw new BadInputException(Constants.FROM_DEEPCOPY_CONSTRUCTOR,MESSAGE);
     }
     //the start charnode of the deep copy
     CharNode COPY_START = null;
     //the temporary position of the deep copy
     CharNode COPY_TEMP = null;
     //the temporary position of the MSB
     CharNode MSB_TEMP = MSB.start;

     //using a for loop to loop through every element
     for(int i = 0; i < MSB.length; i++){
       //store the first Charnode of the MSB as temp
       CharNode temp = new CharNode(MSB_TEMP.getData());
       //for the first charnode of deep copy
       if (COPY_START == null){
         COPY_START = temp;
         COPY_TEMP = COPY_START;

       //for the rest charnode of the deep copy
       }else{
         COPY_TEMP.setNext(temp); //set the current Next to the MSB_temp Next
         COPY_TEMP = COPY_TEMP.getNext(); //reset to the next value position
       }
       length += 1;
       MSB_TEMP = MSB_TEMP.getNext(); //get the next of MSB
     }
     this.start = COPY_START;
     this.end = COPY_TEMP;
   }

  /**
   * Function that return the length
   *
   * @return int representing length
   */
   public int length(){
     return this.length;
    }

  /**
   * Helper method that append a single char to the end of MyStringBuilder
   *
   * @param c to be appended
   * @return the MyStringBuilder object
   */
   public MyStringBuilder append(char c){
     if(length == 0){
       start = new CharNode(c);
       end = start;
     }else{
       CharNode NewCharNode = new CharNode(c);
       end.setNext(NewCharNode);
       end = end.getNext();
     }
     length += 1;
     return this;
   }

  /**
   * Function that append entire string to the end of the current
   * MyStringBuilder object
   *
   * @param str to be appended
   * @return the new mystringbuilder object
   */
   public MyStringBuilder append (String str) throws BadInputException{
     try{
       //append each character in the string one by one
       for(int i = 0; i < str.length(); i++){
         this.append(str.charAt(i));
       }
     }catch(Exception ex){
       throw new BadInputException(Constants.FROM_APPEND_STR, MESSAGE);
     }
     return this;
   }

  /**
   * Function that turns the sequence of char into a string
   *
   * @return string representing the charnode
   */
   public String toString(){
     String str = EMPTY;
     CharNode temp = start;
     //add the character as a string one by one
     for(int i = 0; i < length; i++){
       str += temp.getData();
       temp = temp.getNext();
     }
     return str;
   }

  /**
   * Helper method that insert the char at specific index
   *
   * @param c to be inserted
   * @param index the position to insert to
   * @return a MyStringBuilder object
   */
   public MyStringBuilder insert (char c, int index) throws
   MSBOutOfBoundsException{
     CharNode element = new CharNode(c);
     CharNode temp = start;
     //insert character as a start
     if (index == 0){
        start = element;
        start.setNext(temp);
     //insert in the middle or at the end
     }else if(index <= length && index > 0){
        //loop through to look for index
        for(int i = 0; i < length; i++){
         //keep looping for other elements than the one at the index
         if(i != index-1){
           temp = temp.getNext();
         //the element at the endIndex
         }else{
           CharNode previous_next = temp.getNext(); //store the previous next
           temp.setNext(element); //connect the previous to the insert
           element.setNext(previous_next); //connect to the previous next
           break;
         }
       }
     }else{
       throw new MSBOutOfBoundsException(Constants.FROM_INSERT_CHAR, MESSAGE);
       }
     length += 1;
     return this;
   }

  /**
   * Function that append entire string to the specific index
   *
   * @param str to be appended
   * @param index to insert
   * @return MyStringBuilder object
   */
   public MyStringBuilder insert (String str, int index)
   throws BadInputException, MSBOutOfBoundsException{
     //check for valid input
     if(index < 0 && index > length){
       throw new MSBOutOfBoundsException(Constants.FROM_INSERT_STR, MESSAGE);
     }
     if(str == null){
       throw new BadInputException(Constants.FROM_INSERT_STR, MESSAGE);
     }
     for(int i = 0; i < str.length(); i++){
       insert(str.charAt(i), index+i);
     }
     return this;
   }

   /**
    * Function that find character at the index
    *
    * @param index to be found
    * @return a charnode object containing that character
    */
    protected CharNode findIndex(int index) throws MSBOutOfBoundsException{
      //check for valid input
      if(index < 0 || index >= length || length == 0){
        throw new MSBOutOfBoundsException(Constants.FROM_FIND_INDEX, MESSAGE);
      }

      CharNode temp = start;
      //loop through to find the index
      for(int i = 0; i < length; i++){
        if(i < index){
          temp = temp.getNext();
        //return the charnode at the index
        }else if (i == index){
          temp = temp;
        }
      }
      return temp;
    }

  /**
   * Function that removes the charnode at specific index
   *
   * @param index to remove
   * @return a MyStringBuilder object
   */
   public MyStringBuilder remove (int index) throws MSBOutOfBoundsException{
     if(index < 0 || index >= length){
       throw new MSBOutOfBoundsException(Constants.FROM_REMOVE, MESSAGE);
     }
     //remove the first element
     if (index == 0){
       start = start.getNext();
     // remove element other than the first
     }else{
       CharNode temp = start;
       //loop before the index
       for(int i = 0; i < index-1; i++){
         temp = temp.getNext();
       }
       //emit the targeted index(removing the charnode at that index)
       temp.setNext(temp.getNext().getNext());

      //if the element if the last one
      if (index == length-1){
        end = temp;
      }
     }
     length -= 1;
     return this;
   }

  /**
   * Function that deletes a range of character starting from and including
   * startIndex
   *
   * @param startIndex to start deleting
   * @return a MyStringBuilder object
   */
   public MyStringBuilder delete (int startIndex) throws
   MSBOutOfBoundsException{
     if(startIndex < 0 || startIndex >= length){
       throw new MSBOutOfBoundsException(Constants.FROM_DELETE_STARTINDEX,
       MESSAGE);
     }
     //if startindex equals zero
     if(startIndex == 0){
       start = null;
       end = null;
       length = 0;
     }else{
       CharNode temp = start;
       //for elements before the startindex
       for(int i = 0; i < startIndex; i++){
         //set the one before startIndex as the end
         if(i == startIndex - 1){
           end = temp;
           temp.setNext(null); //set the next as null
           length = i + 1; //update the length
         //loop through every element before startIndex
         }else{
           temp = temp.getNext();
         }
       }
     }
     return this;
   }

  /**
   * Function that delete the characters starting from startIndex to endIndex
   *
   * @param startIndex starting index
   * @param endIndex ending index
   * @return a MyStringBuilder object
   */
   public MyStringBuilder delete (int startIndex, int endIndex) throws
   BadInputException, MSBOutOfBoundsException{
     if(endIndex < startIndex){
       throw new BadInputException(Constants.FROM_DELETE_STARTINDEX_ENDINDEX,
       MESSAGE);
     }
     if(startIndex < 0 || startIndex >= length || endIndex > length){
       throw new MSBOutOfBoundsException(
       Constants.FROM_DELETE_STARTINDEX_ENDINDEX, MESSAGE);
     }
     // find one before start
     CharNode temp1 = start;
     for(int i = 0; i < startIndex - 1; i++){
       temp1 = temp1.getNext();
     }
     // find end
     CharNode temp2 = start;
     for(int i = 0; i < endIndex; i++){
       temp2 = temp2.getNext();
     }
     //if startIndex equals 0
     if (startIndex == 0){
       //keep the rest starting from endIndex
       start = temp2;
     //connect the one before startIndex to the one at endIndex
     }else{
       temp1.setNext(temp2);
     }

     length -= (endIndex - startIndex);
     return this;
   }

  /**
   * Function that return a substring made up of char starting from startIndex
   *
   * @param startIndex to be appended
   * @return a string
   */
   public String substring(int startIndex) throws MSBOutOfBoundsException{
     if(startIndex < 0 || startIndex >= length){
       throw new MSBOutOfBoundsException(
       Constants.FROM_SUBSTRING_STARTINDEX, MESSAGE);
     }
     String str = EMPTY;
     CharNode temp = start;
     for(int i = 0; i < length; i++){
       //loop through until the startIndex
       if(i < startIndex){
         temp = temp.getNext();
       }else{
         //add the str one by one
         str += temp.getData();
         temp = temp.getNext();
       }
     }
     return str;
    }

  /**
   * Function that returns a string starting from startIndex to endIndex
   *
   * @param startIndex starting index
   * @param endIndex ending index
   * @return a string
   */
   public String substring(int startIndex, int endIndex) throws
   MSBOutOfBoundsException, BadInputException{
     if(endIndex < startIndex){
       throw new BadInputException(Constants.FROM_DELETE_STARTINDEX_ENDINDEX,
       MESSAGE);
     }
     if(startIndex < 0 || startIndex >= length || endIndex > length){
       throw new MSBOutOfBoundsException(
       Constants.FROM_SUBSTRING_STARTINDEX_ENDINDEX, MESSAGE);
     }
     String str = EMPTY;
     CharNode temp = start;
     if(endIndex == length){
       endIndex += 1;
     }
     for(int i = 0; i < length; i++){
       //loop through until the startIndex
       if(i < startIndex){
         temp = temp.getNext();
       }else if(i < endIndex){
         //add the str one by one
         str += temp.getData();
         temp = temp.getNext();
       }else{
         break;
       }
     }
     return str;
   }

  /**
   * Function that concat to MyStringBuilder objects together
   *
   * @param rightOperand to concat as the second part
   */
   public MyStringBuilder concat(MyStringBuilder rightOperand) throws
   BadInputException{
     if(rightOperand == null){
       throw new BadInputException(Constants.FROM_CONCAT, MESSAGE);
     }
     //append the second object to the first
     this.append(rightOperand.toString());
     return this;
   }


   public static void main(String[] args) throws MSBOutOfBoundsException,
   BadInputException{
     MyStringBuilder SB1 = new MyStringBuilder('c');
     MyStringBuilder SB2 = new MyStringBuilder("abc");
     //MyStringBuilder SB3 = new MyStringBuilder((String)null);
     //MyStringBuilder SB4 = new MyStringBuilder((MyStringBuilder)null);
     //MyStringBuilder SB5 = new MyStringBuilder(SB2);

     //System.out.println(SB2.append('d'));
     //System.out.println(SB2.append("def"));
     //System.out.println(SB2.append(""));
     //System.out.println(SB2.append(null));

     //System.out.println(SB3.append('d'));
     //System.out.println(SB3.append("def"));
     //System.out.println(SB3.append(""));
     //System.out.println(SB3.append(null));

    // System.out.println(SB2.insert('d', 0));
    // System.out.println(SB2.insert('d', 3));
     //System.out.println(SB2.insert('d', -2));
     //System.out.println(SB2.insert("def", 0));
     //System.out.println(SB2.insert("def", 2));
     //System.out.println(SB2.insert("def", 3));
     //System.out.println(SB2.insert("def", -2));
     //System.out.println(SB2.insert("", 0));
     //System.out.println(SB2.insert((String)null, 0));

     //System.out.println(SB2.remove(1));
     //System.out.println(SB2.remove(0));
     //System.out.println(SB2.remove(0));
     //System.out.println(SB2.remove(0));
     //System.out.println(SB2.remove(4));
     //System.out.println(SB2.remove(-1));

     //System.out.println(SB2.delete(1));
     //System.out.println(SB2.delete(0));
     //System.out.println(SB2.delete(3));
     //System.out.println(SB2.delete(2));
     //System.out.println(SB2.delete(4));
     //System.out.println(SB2.delete(-1));

     //System.out.println(SB2.delete(1, 2));
     //System.out.println(SB2.delete(0, SB2.length()));
     //System.out.println(SB2.delete(0, 2));
     //System.out.println(SB2.delete(2));
     //System.out.println(SB2.delete(4));
     //System.out.println(SB2.delete(-1));
       //SB2 = SB2.substring(5);
       //SB2.substring(6);
    // System.out.println(SB2.findIndex(0).getData());

    //System.out.println(SB2.substring(1));
     //System.out.println(SB2.substring(0, SB2.length()));
     //System.out.println(SB2.substring(1, 2));
    // System.out.println(SB2.substring(1, SB2.length()));
     //System.out.println(SB1);
     //SB2.concat(SB5);
     //System.out.println(SB2);




     //SB5.toString();
     //System.out.println(SB1);
     //SB2.delete(1);
   }

}
