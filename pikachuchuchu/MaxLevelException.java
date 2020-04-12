/**
 * Filename: MaxLevelException.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note
 * Description: The class catches max level exception and return error message
 */

/**
 * Class header: The class catches max level exception and return error message.
 * It contains a constructor and overrides a toString function
 */

public class MaxLevelException extends Exception{
  private static final String EXCEPT_MSG = "%s can't be greater than level" +
  " 100!\n";
  private String pokemonName;



 /**
  * Constructor that takes in a string and set instance variables
  *
  * @param name to be passed into
  */
  public MaxLevelException(String name){
    super(String.format(EXCEPT_MSG,name));
    pokemonName = name;
  }

 /**
  * Functions that override toString method and print out specific message
  *
  * @return string as out message
  */
  @Override
  public String toString(){
    String str = String.format(EXCEPT_MSG, pokemonName);
    return str;
  }

}
