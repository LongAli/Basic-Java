/**
 * Filename: Equivalence.java
 * Name: *
 * Login: *
 * Date: February 18, 2020
 * Sources of Help: Lecture note
 *
 * Description:The class extends from BooleanExpression and implements evaluate.
 * The class allows users to check whether the two variables are Equivalence.
 */

 /**
 * Class header: The class extends from BooleanExpression and contains 1
 * concrete methods and 1 constructor
 */

 public class Equivalence extends BooleanExpression{

   /**
    * Constructor that takes in twos boolean and stores them
    */
    public Equivalence(BooleanExpression b, BooleanExpression c){
      operand1 = b;
      operand2 = c;
    }

   /**
    * Function that implements the evaluate method
    * @return the Equivalence of the result of BooleanExpression
    */
    @Override
    public Value evaluate(){
      if (operand1 == null || operand2 == null){
        return null;
      }else{
        //create values to store the result from evaluate
        Value v1 = operand1.evaluate();
        Value v2 = operand2.evaluate();
        //check if inputs are evaluable
        if ((v1 instanceof BoolEvaluable) && (v2 instanceof BoolEvaluable)){
          //typecast value v into BoolEvaluable interface
          BoolEvaluable o1 = (BoolEvaluable) v1;
          BoolEvaluable o2 = (BoolEvaluable) v2;
          //store boolEvaluate as booleans
          boolean temp1 = o1.boolEvaluate();
          boolean temp2 = o2.boolEvaluate();

          //check if two booleans are equivalent
          if (temp1 == temp2){
            return new BooleanValue(true);
          }else{
            return new BooleanValue(false);
          }
        }
        return null;
      }
    }
  }
