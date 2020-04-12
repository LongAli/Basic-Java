/**
 * Filename: PokemonStorageSystem.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note;
 * Description: The class serves as a storage system for pokemon
 */

import java.util.ArrayList;
import java.util.List;
/**
 * Class header: The class serves as a storage system for pokemon. It contains
 * array of box where the boxes contain positions. It allows users to deposit,
 * release and swap pokemon.
 */
public class PokemonStorageSystem<T>{
  private static final int MAX_BOXES = 8;
  private static final int MAX_ITEMS = 30;

  private static final String OUT_OF_BOUNDS_EXCEPTION = "Box: %d, Pos: %d";

  private List<Box<T>> storage;
  private T partyMember;

 /**
  * Sole constructor that initialize instance variables
  * @param maxSize of the box
  */
  public PokemonStorageSystem(){
    storage = new ArrayList<Box<T>>(MAX_BOXES);
    for(int i = 0; i < MAX_BOXES; i++){
      storage.add(i, new Box<T>(MAX_ITEMS));
    }
  }

 /**
  * Function that set partyMember
  * @param partyMember to set to
  */
  public void setPartyMember(T partyMember){
    this.partyMember = partyMember;
  }

 /**
  * Function that tries to deposition a new pokemon
  * @param newPokemon to be put into
  */
  public void deposit(T newPokemon) throws NoStorageSpaceException{
    //loop for each box
    for(int i =0; i < MAX_BOXES; i++){
      //use deposit in box class
      Box<T> box = storage.get(i);
      if(box.deposit(newPokemon)){
        return;
      }
    }
    throw new NoStorageSpaceException();
  }

 /**
  * Function that return the pokemon in box at index pos
  * @param box to get position from
  * @param pos to get index from
  * @return T the pokemon
  */
  public T release(int box, int pos) throws OutOfBoundsException{
    if(box > MAX_BOXES || box < 0 || pos > MAX_ITEMS || pos < 0){
      throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
      box, pos));
    }
    //get the pokemon
    T Pokemon_sto = storage.get(box).getPositionAtIndex(pos).getPokemon();
    //set the position to null
    storage.get(box).getPositionAtIndex(pos).setPokemon(null);
    return Pokemon_sto;
  }

  /**
   * Function that swap the pokemon to specific box at index pos
   * @param boxFrom to get position from
   * @param posFrom to get index
   * @param boxTo to put position to
   * @param posTo to put index to
   */
   public void move(int boxFrom, int posFrom, int boxTo, int posTo)
    throws OutOfBoundsException{
     //check for boxFrom and posFrom
     if(boxFrom > MAX_BOXES || boxFrom < 0 || posFrom > MAX_ITEMS ||
     posFrom < 0){
       throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
       boxFrom, posFrom));

     //check for boxTo and posTo
     }else if(boxTo > MAX_BOXES || boxTo < 0 || posTo > MAX_ITEMS ||
     posTo < 0){
       throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
       boxTo, posTo));

     //do the swap after checking
     }else{
       T PokeF = storage.get(boxFrom).getPositionAtIndex(posFrom).getPokemon();
       T PokeT = storage.get(boxTo).getPositionAtIndex(posTo).getPokemon();
       storage.get(boxFrom).getPositionAtIndex(posFrom).setPokemon(PokeT);
       storage.get(boxTo).getPositionAtIndex(posTo).setPokemon(PokeF);
     }
   }

   /**
    * Function that return string represeting box at boxNumber
    * @param boxNumber to get box from
    * @return String representing box at this boxNumber
    */
    public String getBox(int boxNumber) throws OutOfBoundsException{
      if(boxNumber > MAX_BOXES || boxNumber < 0){
        throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
        boxNumber, 0));
      }else{
        return storage.get(boxNumber).toString();
      }
    }

}
