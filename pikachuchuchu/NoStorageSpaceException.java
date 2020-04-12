/**
 * Filename: NoStorageSpaceException.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note
 * Description: The class catches max space exception and return error message
 */

/**
 * Class header: The class catches max space exception and return error message.
 * It contains a constructor and overrides a toString function
 */

  public class NoStorageSpaceException extends Exception{
    private static final String EXCEPT_MSG = "No storage left\n";


   /**
    * Default Constructor that calls super class
    *
    * @param name to be passed into
    */
    public NoStorageSpaceException(){
      super(EXCEPT_MSG);
    }

   /**
    * Functions that override toString method and print out specific message
    *
    * @return string
    */
    @Override
    public String toString(){
      return EXCEPT_MSG;
    }
  }
