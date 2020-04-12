/**
 * Filename: Bulbasaur.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note
 * Description: The class extends Pokemon and contains one Constructor and One
 * method
 */

/**
 * Class header: The class contains default constrcutor and overrides the
 * attack method
 */
public class Bulbasaur extends Pokemon{
  private static final String NAME       = "Bulbasaur";
  private static final String DEX_NUMBER = "001";
  private static final int INITIAL_LEVEL = 5;
  private static final int zero = 0;
  private static final int six = 6;
  private static final int ten = 10;


 /**
  * Default constructor that call super method with starter stats
  */
  public Bulbasaur() throws MinLevelException, MaxLevelException{
    super(NAME,DEX_NUMBER, INITIAL_LEVEL);
  }

 /**
  * Function that overrides attack method
  * @return int representing attack value
  */
  @Override
  public int attack(){
    //create an int list to store the numbers
    int[] arr = {zero, six, ten};
    //get a random index of the list
    int index = getRandom().nextInt(arr.length);
    //return the number on that random index
    int damageVal = arr[index];
    return damageVal;
  }

}
