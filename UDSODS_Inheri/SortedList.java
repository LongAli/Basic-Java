/**
 * Filename: SortedList.java
 * Name: **
 * Login: **
 * Date: Febrary 10, 2020
 * Sources of Help: Lecture note
 *
 * Description: The class extends OrderedDS class. It creates a array from small
 * to large. It also allows users to manipulate the array by making a deep copy,
 * adding, deleting, returning the peek value and size as well as printing the
 * array.
 */

 /**
   * Class header: The class allows users to create and manipulate a array
   * arranged from small to large values
   */
 public class SortedList extends OrderedDS {
   private int maxSize;
   private int indexCounter;

   private static final String EMPTY_ERROR = "SortedList is empty";
   private static final String MAX_SIZE_ERROR =
   "SortedList maximum limit reached";
   private static final String EMPTY = "";
   private static final String FORMAT = "-";

   /**
    * Default construtor that initializes the value
    */
   public SortedList(){
     maxSize = 0;
     array = null;
   }

   /**
    * Construtor that takes in maxSize to create an array with maxSize length
    * @param maxSize
    */
   public SortedList(int maxSize){
     if (maxSize < 0){
       this.maxSize = 0;
       array = null;
     }else{
       this.maxSize = maxSize;
       array = new int[this.maxSize];
     }
   }


   /**
    * Construtor that takes in a SortedList and make a deep copy
    * @param s SortedList to deep copy from
    */
   public SortedList(SortedList s){
     array = new int[s.maxSize];
     for(int i = 0; i < s.maxSize; i++){
       array[i] = s.array[i];
     }
   }


   /**
    * functions that add an int element to the SortedList
    * @param element
    */
   public void add(int element){
     if(array == null || indexCounter >= maxSize){
       System.out.println(MAX_SIZE_ERROR);
     }else{
       //add the first element to the array
       if(indexCounter == 0){
         array[indexCounter] = element;
       }else{
          int c =0; //keep track of values that are larger than element
          //loop for nonzero index backward
          for(int i = indexCounter; i > 0; i--){
            if(array[i - 1] > element){
              c += 1;
            }
          }
          //element is not the largest
          if (c != 0){
            //move values that are larger than element one index next
            for(int j = indexCounter; j > indexCounter - c; j--){
              array[j] = array[j - 1];
            }
            //assign element in the correct index
            array[indexCounter - c] = element;

          //if the element is the largest
          }else{
            array[indexCounter] = element;
          }
        }
      }
      indexCounter += 1;
    }


   /**
    * Function that delete and return the smallest element of the array
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
    * Function that returns the size of the SortedList
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
    * Function that print the SortedList as a string in the specified format
    * @return the SortedList as a string
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
     SortedList f = new SortedList(5);
     f.add(7);
     f.add(9);
     f.add(2);
     f.add(6);
     f.add(3);
     f.add(5);
     System.out.println(f.toString());
     System.out.println(f.delete());
     System.out.println(f.delete());
     System.out.println(f.peek());
     System.out.println(f.toString());
     System.out.println(f.size());
   }

 }
