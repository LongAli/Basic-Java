/**
 * Filename: Box.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note;
 * Description: The class is a generic class. It contains one constructor and
 * three methods.
 */

import java.util.ArrayList;
import java.util.List;
/**
 * Class header: The class can position pokemon into box, get position index
 * and print out message using overridden tostring method
 */
public class Box<T>{
  private static final String BORDER     = "---------------------";
  private static final String DIVIDER    = "|";
  private static final String NEW_LINE   = "\n";
  private static final String EMPTY_POS  = "   ";
  private static final int MAX_ELEM_LINE = 5;

  private static final String OUT_OF_BOUNDS_EXCEPTION = "Index: %s";

  private List<Position<T>> boxElements;
  private int maxSize;

 /**
  * Sole constructor that initialize instance variables
  * @param maxSize of the box
  */
  public Box(int maxSize){
    this.maxSize = maxSize;
    boxElements = new ArrayList<Position<T>>(maxSize);
    for(int i = 0; i < maxSize; i++){
      boxElements.add(i, new Position<T>(null));
    }
  }

 /**
  * Function that overrides the tostring method
  * @return string that print out the message
  */
  @Override
  public String toString() {
    int counter = 0;

    StringBuilder boxPrintout = new StringBuilder();
    boxPrintout.append(BORDER);

    // Iterate through each element, print 5 at most on a line
    for(Position<T> element : boxElements) {
        if(counter == 0) {
            boxPrintout.append(NEW_LINE);
            boxPrintout.append(DIVIDER);
        }

        // Print EMPTY_POS if the spot is free (null)
        T pokemon = element.getPokemon();
        if(element.isOpen()) {
            boxPrintout.append(EMPTY_POS);
        } else {
            boxPrintout.append(pokemon.toString());
        }
        boxPrintout.append(DIVIDER);

        counter++;

        // Used so we only have 5 elements at most on a line
        if(counter == MAX_ELEM_LINE) {
            boxPrintout.append(NEW_LINE);
            boxPrintout.append(BORDER);
            counter = 0;
        }
    }
    boxPrintout.append(NEW_LINE);

    return boxPrintout.toString();
  }

 /**
  * Function that tries to deposition a new pokemon
  * @param newPokemon to be put into
  * @return true if newPokemon is depositioned
  */
  public boolean deposit(T newPokemon){
    for(int i =0; i < maxSize; i++){
      if(boxElements.get(i).isOpen()){
        boxElements.get(i).setPokemon(newPokemon);
        return true;
      }
    }
    return false;
  }

 /**
  * Function that return the position at index
  * @param index to get position from
  * @return the position at index
  */
  public Position<T> getPositionAtIndex(int index) throws OutOfBoundsException{
    if(index < 0 || index >= maxSize){
      throw new OutOfBoundsException(String.format(OUT_OF_BOUNDS_EXCEPTION,
      index));
    }else{
      return boxElements.get(index);
    }
  }

}
