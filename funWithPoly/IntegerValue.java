/**
 * Filename: IntegerValue.java
 * Name: *

  * Login: *
  * Date: February 18, 2020
  * Sources of Help: Lecture note
  *
  * Description: The class extends from BooleanExpression and implements Value,
  * IntEvaluable, and BoolEvaluable. The class represents an int with extra
  * functionality
  */

  /**
  * Class header: The class extends from ArithmeticExpression and contains 4
  * concrete methods: evaluate, intEvaluable, boolEvaluable, toString
  */

  public class IntegerValue extends ArithmeticExpression implements Value,
  IntEvaluable, BoolEvaluable{
    public int val;

    /**
     * Constructor that takes in an integer and initiate the instance variable
     */
     public IntegerValue(int b){
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
      * Function that override the intEvaluate method
      * @return respective int expression
      */
      @Override
      public int intEvaluate(){
        return this.val;
      }

      /**
       * Function that override the boolEvaluate method
       * @return boolean that the BooleanValue represents
       */
       @Override
       public boolean boolEvaluate(){
         if (val == 0){
           return false;
         }else{
           return true;
         }
       }

      /**
       * Function that override the value method
       * @return true if the toString values of caller and argument are the same
       */
       @Override
       public String toString(){
         String s = String.valueOf(this.val);
         return s;
       }
    }
