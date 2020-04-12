
/**
 * Filename: OutOfBoundsException.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note
 * Description: The class catches out of bounds exception and return error
 * message
 */

/**
 * Class header: The class catches out of bounds exception and return error
 * message. It contains a constructor and overrides a toString function
 */

  public class OutOfBoundsException extends Exception{
    private static final String EXCEPT_MSG = "Out of bounds: %s\n";
    private String errorLocation;


   /**
    * Constructor that takes in a string and set instance variable
    *
    * @param name to be passed into
    */
    public OutOfBoundsException(String loc){
      super(String.format(EXCEPT_MSG,loc));
      errorLocation = loc;
    }

   /**
    * Functions that override toString method and print out specific message
    *
    * @return string
    */
    @Override
    public String toString(){
      String str = String.format(EXCEPT_MSG, errorLocation);
      return str;
    }
  }
