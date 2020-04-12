/**
 * Filename: Position.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note
 * Description: The class is a generic class. It contains one instance variable,
 * one constructor and three methods
 */


/**
 * Class header: The class can get, set position of pokemon and can check if
 * the position is open or not
 */
public class Position<T>{
  private T pokemon;


 /**
  * Sole constructor that initialize instance variables
  * @param pokemon that passed into the constructor
  */
  public Position(T pokemon){
    this.pokemon = pokemon;
  }

 /**
  * Function that returns the pokemon
  * @return T, the Pokemon at this position
  */
  public T getPokemon(){
    return pokemon;
  }

 /**
  * Function that set newPokemon as the pokemon at this position
  * @param newPokemon to be set to
  */
  public void setPokemon(T newPokemon){
    this.pokemon = newPokemon;
  }

 /**
  * Function that check if the position if open or not
  * @return True if position is open
  */
  public boolean isOpen(){
    if(pokemon == null){
      return true;
    }else{
      return false;
    }
  }

}
