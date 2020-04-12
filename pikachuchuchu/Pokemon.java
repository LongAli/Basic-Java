/**
 * Filename: Pokemon.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note
 * Description: The class contains and returns information about this Pokemon
 */

import java.util.Random;

/**
 * Class header: The class can return dexNumber, level, name, and random number
 * representing damage
 */
public class Pokemon{
  private static final int MAX_DAMAGE = 10;
  private static final int MAX_LEVEL  = 100;

  private String dexNumber;
  private String name;
  private int level;
  private Random random;


 /**
  * Constructor that takes in three parameters and initialize instance variables
  * based on certain criterias.
  * @param dexNumber
  * @param name
  * @param level
  */
  public Pokemon(String dexNumber, String name, int level)
    throws MinLevelException, MaxLevelException{
    this.dexNumber = dexNumber;
    this.name = name;
    random = new Random();
    //throw exception when level is invalid
    if (level < 1){
      throw new MinLevelException(name);
    }else if(level > MAX_LEVEL){
      throw new MaxLevelException(name);
    }else{
      this.level = level;
    }
  }

 /**
  * Function that returns the name of this pokemon
  * @return String of the name of this pokemon
  */
  public String getName(){
    return this.name;
  }

 /**
  * Function that returns the level of this Pokemon.
  * @return int representing the level of this pokemon
  */
  public int getLevel(){
    return this.level;
  }

 /**
  * Function that returns the random object of this Pokemon
  * @return random object
  */
  public Random getRandom(){
    return this.random;
  }

 /**
  * Function that overrides string method
  * @return the dexNumber of this Pokemon
  */
  @Override
  public String toString(){
    return this.dexNumber;
  }

 /**
  * Function that return a new integer between 0 and MAX_DAMAGE based on random
  * @return returns the damage value that this Pokemon
  */
  public int attack(){
    random = new Random();
    int damageVal = random.nextInt(MAX_DAMAGE);
    return damageVal;
  }

}
