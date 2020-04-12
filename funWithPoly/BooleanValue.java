/**
 * Filename: BooleanValue.java
 * Name: *
 * Login: *
 * Date: February 18, 2020
 * Sources of Help: Lecture note
 *
 * Description: The class extends from BooleanExpression and contains 1
 * instance variable, 1 constructor and 4 concrete methods
 */

 /**
 * Class header: The class extends from BooleanExpression and contains 4
 * concrete methods: evaluate, intEvaluable, boolEvaluable, toString. The Class
 * represent a boolean with extra functionality.
 */

 public class BooleanValue extends BooleanExpression implements Value,
 IntEvaluable, BoolEvaluable{
   public boolean val;

   /**
    * Constructor that takes in a boolean and initiate the instance variable
    */
    public BooleanValue (boolean b){
        this.val = b;
    }

   /**
    * Function that override the evaluate method
    * @return a reference to this instance
    */
    @Override
    public Value evaluate(){
      return this;
    }

   /**
    * Function that implement intEvaluate method
    * @return respective int expression
    */
    @Override
    public int intEvaluate(){
      if (val == true){
        return 1;
      }else{
        return 0;
      }
    }

    /**
     * Function that override the boolEvaluate method
     * @return boolean that the BooleanValue represents
     */
     @Override
     public boolean boolEvaluate(){
       return this.val;
     }

    /**
     * Function that override the equals method
     * @return true if the toString values of caller and argument are the same
     */
     @Override
     public String toString(){
       String s;
       if (this.val == true){
         s = "true";
       }else{
         s = "false";
       }
        return s;
     }
  }
