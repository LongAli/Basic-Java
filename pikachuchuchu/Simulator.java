/**
 * Filename: Simulator.java
 * Name: *
 * Login: *
 * Date: March 5, 2020
 * Sources of Help: Lecture note
 * Description: The class simulate the catching and battling of pokemon. It
 * allows users to catch pokemon in the wild, check storage, swap pokemon in
 * the storage, and release a pokemon
 */

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class header: The class allows users to catch pokemon in the wild, check
 * storage, swap pokemon in the storage, and release a pokemon.
 */
public class Simulator {

    // Used if user selects bulbasaur as starter
    private static final int BULBASAUR = 2;

    // Used to parse the level of a Pokemon
    private static final int LEVEL_INDEX = 2;

    // Number of required arguments
    private static final int REQUIRED_ARGS = 2;
    private static final int REQUIRED_ARGS_3 = 3;
    private static final int REQUIRED_ARGS_5 = 5;

    // Used to split pokemon file / user input
    private static final String SEPARATOR = ",";
    private static final String PROMPT_SEP = " ";

    // Used to index through user arguments
    private static final int BOX_INDEX = 1;
    private static final int POS_INDEX = 2;
    private static final int TO_BOX_INDEX = 3;
    private static final int TO_POS_INDEX = 4;

    //Used to loop through index
    private static final int idx_0 = 0;
    private static final int idx_1 = 1;
    private static final int idx_2 = 2;

    // Different choices user can make
    private static final String OPTION_0 = "0";
    private static final String OPTION_1 = "1";
    private static final String OPTION_2 = "2";

    private static final String USER_PROMPT = "> ";

    private static final String USAGE_PROMPT =
        "\nUsage: java Simulator [0|1|2] [filename]\n" +
        "0 - Charmander, 1 - Squirtle, 2 - Bulbasaur\n\n";

    private static final String MAIN_PROMPT =
        "\nWhat would you like to do?\n" +
        "[0] - Go into the wild!\n" +
        "[1] - View your PC!\n\n";

    private static final String PC_PROMPT =
        "\nCurrently viewing someone's PC\n" +
        "[0] b           - View box b (specify a number)\n" +
        "[1] b1 p1 b2 p2 - Move Pokemon at box b1, pos p1 to box b2, p2\n" +
        "[2] b p         - Release Pokemon at box b, pos p\n\n";

    private static final String WILD_PROMPT =
        "\nYou have encountered a level %d %s!\n" +
        "[0] - Catch\n" +
        "[1] - Run\n\n";

    private static final String CAUGHT_PROMPT =
        "\nSuccessfully caught %s!\n";

    private static final String RUN_PROMPT =
        "\nPhew... ran away!\n";

    private static final String BATTLE_INTRO =
        "\nBattling against your rival!\n" +
        "Your rival sent out %s.\n" +
        "Go! %s!\n" +
        "--------------------------------------\n";

    private static final String BATTLE_MAIN =
        "Your rival has dealt %d damage!\n" +
        "You dealt %d damage!\n\n";

    private static final String BATTLE_WIN =
        "You won!\n";

    private static final String BATTLE_LOSE =
        "You lost! Smell you later!\n";

    private static final String EMPTY_WILD =
        "No more pokemon in the wild!\n";

    private static final String SUCCESSFUL_MOVE =
        "Successful move!\n";

    private static final String SUCCESSFUL_RELEASE =
        "Successful release!\n";

    private static final String UNRECOGNIZED_PROMPT =
        "Unrecognized command. Please try again.\n\n";

    private static final String FILE_NOT_FOUND =
        "File: %s could not be found!\n\n";

    // One storage, one scanner (reinitialize scanner as necessary)
    private static PokemonStorageSystem<Pokemon> storage;
    private static Scanner scanner;

    /**
     * Function that handle battle between starter and rival
     * @param starter to user choose
     * @param rival to battle with
     */
    private static void handleBattle(Pokemon starter, Pokemon rival) {
        // Initial battle text
        System.out.printf(BATTLE_INTRO, rival.getName(), starter.getName());

        // Calculate damage done from each pokemon
        int S_damageVal = starter.attack();
        int R_damageVal = rival.attack();

        // Use System.out.printf with BATTLE_MAIN as format string
        System.out.printf(BATTLE_MAIN, R_damageVal, S_damageVal);

        // Battle logic -- you win if your damage > rival damage
        if(S_damageVal > R_damageVal){
          // System.out.printf BATTLE_WIN if you win
          System.out.printf(BATTLE_WIN);

        // else BATTLE_LOSE
        }else{
          System.out.printf(BATTLE_LOSE);
        }
    }

    /**
     * Function that handle situations associated with wild
     * @param wild to interact with
     */
    private static void handleWild(Pokemon wild) {
        // Use the wild pokemon that was passed in
        System.out.printf(WILD_PROMPT, wild.getLevel(), wild.getName());

        // Parse user's next decision
        String line;

        boolean invalid = true;

        try {
            // Keep prompting user until a valid action has been made
            while(invalid) {
                System.out.print(USER_PROMPT);
                line = scanner.nextLine().toUpperCase().trim();

                switch(line) {
                    // catch and deposit the pokemon
                    case OPTION_0:
                        invalid = false;
                        storage.deposit(wild);
                        System.out.printf(CAUGHT_PROMPT, wild.getName());
                        break;
                    // run away from the pokemon
                    case OPTION_1:
                        invalid = false;
                        System.out.printf(RUN_PROMPT);
                        break;
                    default:
                        System.out.printf(UNRECOGNIZED_PROMPT);
                        break;
                }
            }
        }catch(NoStorageSpaceException ex){
          System.out.println(ex.toString());
        }
    }

    /**
     * Function that handle storage, release, swap pokemon
     */
    private static void handlePC() {
        System.out.printf(PC_PROMPT);

        String line;
        String[] splitLine;

        // Keep looping until we have a valid input
        boolean invalid = true;

        try {
            while(invalid) {
                System.out.print(USER_PROMPT);
                line = scanner.nextLine().trim();
                splitLine = line.split(PROMPT_SEP);

                // Check to ensure number of required args is correct
                // If so, then parse accordingly
                // Assumes that inputs are numbers; Not handling invalid cases
                switch(splitLine[0].toUpperCase()) {
                    // check box
                    case OPTION_0: {
                        if(splitLine.length != REQUIRED_ARGS) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }

                        invalid = false;
                        /* Pase argument and pass in getBox */
                        String str = storage.getBox(Integer.parseInt(splitLine[
                        BOX_INDEX]));

                        /* System.out.printf output of getBox */
                        System.out.printf(str);
                        break;
                    }
                    //move/ swap pokemon
                    case OPTION_1: {
                        if(splitLine.length != REQUIRED_ARGS_5) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }

                        invalid = false;
                        /* Parse arguments and pass into move */
                        storage.move(Integer.parseInt(splitLine[BOX_INDEX]),
                        Integer.parseInt(splitLine[POS_INDEX]),
                        Integer.parseInt(splitLine[TO_BOX_INDEX]),
                        Integer.parseInt(splitLine[TO_POS_INDEX]));
                        System.out.printf(SUCCESSFUL_MOVE);
                        break;
                    }
                    //release pokemon
                    case OPTION_2: {
                        if(splitLine.length != REQUIRED_ARGS_3) {
                            System.out.printf(UNRECOGNIZED_PROMPT);
                            break;
                        }

                        invalid = false;
                        //Parse arguments and pass into release
                        storage.release(Integer.parseInt(splitLine[BOX_INDEX]),
                        Integer.parseInt(splitLine[POS_INDEX]));

                        System.out.printf(SUCCESSFUL_RELEASE);
                        break;
                    }
                    default:
                        System.out.printf(UNRECOGNIZED_PROMPT);
                        break;
                }
            }
        }catch(OutOfBoundsException ex1){
          System.out.println(ex1.toString());
        }
    }

    /**
     * Function that parse the pokemon file and create pokemon objects
     * @param filename to be parsed in
     */
    private static List<Pokemon> parsePokemonFile(String filename){

      try{
        //read through file
        File file = new File(filename);
        scanner = new Scanner(file);

        ArrayList<Pokemon> list_p = new ArrayList<Pokemon>();
        //read through lines
        while (scanner.hasNextLine()){
          String line = scanner.nextLine();
          String [] str = line.split(SEPARATOR);

          //create pokemon object
          Pokemon pokemon = new Pokemon(str[idx_0], str[idx_1],
          Integer.parseInt(str[idx_2]));
          //add each new pokemon object to arraylist
          list_p.add(pokemon);
        }
        return list_p;
      }catch(FileNotFoundException E){
        System.out.printf(String.format(FILE_NOT_FOUND, filename));
        return null;
      }catch(MinLevelException min){
        System.out.printf(min.toString());
        return null;
      }catch(MaxLevelException max){
        System.out.printf(max.toString());
        return null;
      }
    }
    /**
     * Main method to perform actual interacting with the pokemon system
     * @param args that users use to interact with the system
     */
    public static void main(String[] args) {

        if(args.length != REQUIRED_ARGS) {
            System.out.printf(USAGE_PROMPT);
            return;
        }

        /**Initialize global pokemon storage */
        storage = new PokemonStorageSystem<Pokemon>();

        // Choose your starter pokemon
        int choice = Integer.parseInt(args[0]);
        Pokemon starter = null;
        Pokemon rival = null;

        /**Initialize the starter and rival variables accordingly */
        try{
          //check for choice 0
          if(choice == idx_0){
            starter = new Charmander();
            rival = new Squirtle();
          }else if(choice == idx_1){
            starter = new Squirtle();
            rival = new Bulbasaur();
          }else if(choice == idx_2){
            starter = new Bulbasaur();
            rival = new Charmander();
          }

        }catch(MinLevelException min){
          System.out.printf(min.toString());
          return;
        }catch(MaxLevelException max){
          System.out.printf(max.toString());
          return;
        }

        storage.setPartyMember(starter);

        /**  Start battle against the opposing rival pokemon */
        handleBattle(starter, rival);

        // Retrieve the filename of all the Pokemon that can be attainable
        List<Pokemon> allPokemon = parsePokemonFile(args[1]);
        if(allPokemon == null) {
            return;
        }

        // Used to index through allPokemon
        int currIndex = 0;

        // Interactive mode
        System.out.printf(MAIN_PROMPT);
        System.out.print(USER_PROMPT);

        scanner = new Scanner(System.in);
        String line;

        // Keep looping until ctrl+D
        while(scanner.hasNextLine()) {
            // Decide whether to go into the wild or view PC
            line = scanner.nextLine().toUpperCase().trim();

            switch(line) {
                case OPTION_0:
                    /* Handle case for no more wild pokemon */
                    if(currIndex == allPokemon.size()) {
                        System.out.printf(EMPTY_WILD);
                    } else {
                        /*Call on handleWild */
                        handleWild(allPokemon.get(currIndex));
                        currIndex += 1;
                    }
                    break;
                case OPTION_1:
                    /* Call on handlePC */
                    handlePC();
                    break;
                default:
                    System.out.printf(UNRECOGNIZED_PROMPT);
                    break;
            }

            System.out.printf(MAIN_PROMPT);
            System.out.print(USER_PROMPT);
        }
    }
}
