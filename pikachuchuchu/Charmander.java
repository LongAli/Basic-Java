/**
 * Filename: Charmander.java
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
public class Charmander extends Pokemon{
  private static final String NAME       = "Charmander";
  private static final String DEX_NUMBER = "004";
  private static final int INITIAL_LEVEL = 5;
  private static final int five = 5;


 /**
  * Default constructor that call super method with starter stats
  */
  public Charmander() throws MinLevelException, MaxLevelException{
    super(NAME, DEX_NUMBER, INITIAL_LEVEL);
  }

 /**
  * Function that overrides attack method
  * @return int representing attack value
  */
  @Override
  public int attack(){
    int damageVal = five;
    return damageVal;
  }

}
