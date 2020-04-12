/**
 * Filename:Negation.java
 * Name: *
 * Login: *
 * Date: February 18, 2020
 * Sources of Help: Lecture note
 *
 * Description:The class extends from BooleanExpression and implements evaluate
 * The class allows users to perform negation that converts false to true and
 * vice versa.
 */

 /**
 * Class header: The class extends from BooleanExpression and contains 1
 * concrete methods that perform the conversion nd 1 constructor that initialize
 * the instance variable
 */

 public class Negation extends BooleanExpression{

   /**
    * Constructor that takes in a boolean and stores it
    */
    public Negation(BooleanExpression b){
        operand1 = b;
    }

   /**
    * Function that implements the evaluate method
    * @return the negation of the result of BooleanExpression
    */
    @Override
    public Value evaluate(){
      //check for null
      if (operand1 == null){
        return null;
      }else{
        //check if input is evaluable
        Value v = operand1.evaluate();
        if (v instanceof BoolEvaluable){
          //typecast value v into BooleanValue
          BoolEvaluable o = (BoolEvaluable) v;
          //store boolEvaluate as a boolean
          boolean temp = o.boolEvaluate();
          //convert the valid input
          if (temp){
            return new BooleanValue(false);
          }else{
            return new BooleanValue(true);
          }
        }
        return null;
      }
    }
}
