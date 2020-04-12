/**
 * Filename: Product.java
 * Name: *
 * Login: *
 * Date: February 18, 2020
 * Sources of Help: Lecture note
 *
 * Description:The class extends from ArithmeticExpression and implements
 * evaluate. The class allows user to calculate the Product of two variables
 */

 /**
 * Class header: The class extends from ArithmeticExpression and contains 1
 * concrete method and 1 constructor
 */

 public class Product extends ArithmeticExpression{

   /**
    * Constructor that takes in two variables and stores them
    */
    public Product(ArithmeticExpression b, ArithmeticExpression c){
      operand1 = b;
      operand2 = c;
    }

   /**
    * Function that implements the evaluate method
    * @return the Product of the result of ArithmeticExpression
    */
    @Override
    public Value evaluate(){
      //check for null
      if (operand1 == null || operand2 == null){
        return null;
      }else{
        //check if inputs are evaluable
        Value v1 = operand1.evaluate();
        Value v2 = operand2.evaluate();
        if (v1 instanceof IntEvaluable && v2 instanceof IntEvaluable){
          //typecast value v into IntEvaluable
          IntEvaluable o1 = (IntEvaluable) v1;
          IntEvaluable o2 = (IntEvaluable) v2;
          //store IntEvaluable as an int
          int temp1 = o1.intEvaluate();
          int temp2 = o2.intEvaluate();
          //calculate the product between the valid inputs
          int result = temp1 * temp2;
          return new IntegerValue(result);
         }
       }
       return null;
    }
 }
