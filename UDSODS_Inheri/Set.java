/**
 * Filename: Set.java
 * Name: **
 * Login: **
 * Date: Febrary 10, 2020
 * Sources of Help: Lecture note
 *
 * Description: The class extends UnorderedDS class. The set store elements not
 * in any order, and no repeated values are stored
 */

 /**
   * Class header: The class allows users to store elements not in any order,
   * and no repeated values are stored.
   */
 public class Set extends UnorderedDS {
   private int maxSize;
   private int indexCounter;

   private static final String DUPLICATE_ERROR = "Element already exists";
   private static final String MAX_SIZE_ERROR="Set maximum limit reached";


  /**
   * Default construtor that initializes the value
   */
   public Set(){
     maxSize = 0;
     array = null;
   }

   /**
    * Construtor that takes in maxSize to create an array with maxSize length
    * @param maxSize
    */
   public Set(int maxSize){
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
    * @param s set to deep copy from
    */
   public Set(Set s){
     array = new int[s.maxSize];
     for(int i = 0; i < s.maxSize; i++){
       array[i] = s.array[i];
     }
   }

   /**
    * Function that add an element, if not repeated, in no particular order
    * @param element to add to the list
    */
   public void add(int element){
     if(array == null || indexCounter >= maxSize){
       System.out.println(MAX_SIZE_ERROR);
       return;
     }
     //check if there is repeated value in the array
     for(int i = 0; i < indexCounter; i++){
       if(array[i] == element){
         System.out.println(DUPLICATE_ERROR);
         return;
       }
    }
      //add the element at the last index
      array[indexCounter] = element;
      indexCounter += 1;
     }

   /**
    * Function that returns the size of the set
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
    * Function that converts the set to sorted list and return the object
    * @return sortedlist object
    */
   @Override
   public SortedList toSortedList(){
     SortedList s = new SortedList(maxSize);
     //add every element in the array from small to large
     for (int i = 0; i < indexCounter; i ++){
       s.add(this.array[i]);
     }
     return s;
   }

     public static void main(String[] args){
       Set f = new Set(5);
       f.add(13);
       f.add(6);
    //   f.add(3);
    //   f.add(3);
    //   f.add(7);
    //   f.add(9);
            //  f.add(19);
              //       f.add(4);
       System.out.println(f.toSortedList());
      // System.out.println(f.delete());
      // System.out.println(f.peek());
       System.out.println(f.size());
     }

 }
