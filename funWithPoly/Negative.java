/**
 * Filename: Negative.java
 * Name: *
 * Login: *
 * Date: February 18, 2020
 * Sources of Help: Lecture note
 *
 * Description:The class extends from ArithmeticExpression and implements
 * evaluate. The class allows users to make the variable negative.
 */

 /**
 * Class header: The class extends from ArithmeticExpression and contains 1
 * concrete method and 1 constructor
 */

 public class Negative extends ArithmeticExpression{

   /**
    * Constructor that takes in one ArithmeticExpression variable and stores it
    */
    public Negative(ArithmeticExpression b){
      operand1 = b;
    }

   /**
    * Function that implements the evaluate method
    * @return the Negative of the result of ArithmeticExpression
    */
    @Override
    public Value evaluate(){
      //check for null
      if (operand1 == null){
        return null;
      }else{
        //check if input is evaluable
        Value v = operand1.evaluate();
        if (v instanceof IntEvaluable){
          //typecast value v into IntEvaluable
          IntEvaluable o = (IntEvaluable) v;
          //store IntEvaluable as an int
          int temp = o.intEvaluate();
          //convert the valid input
          temp = temp * (-1);
          return new IntegerValue(temp);
         }
       }
       return null;
    }

 }
