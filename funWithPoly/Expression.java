/**
 * Filename: Expression.java
 * Name: *
 * Login: *
 * Date: February 18, 2020
 * Sources of Help: Lecture note
 *
 * Description: The abstract class contains 1 abstract method, 2 concrete
 * method
 */

 /**
 * Class header: The abstract class contains 1 abstract method, 2 concrete
 * method
 */
 public abstract class Expression{
   public abstract Value evaluate();


  /**
   * Function that override the toString method
   * @return string that represent the result from evaluate
   */
   @Override
   public String toString(){
     Value res = evaluate();
     if(res == nul l){
       String s = "undefined";
       return s;
     }
     return res.toString();

   }

  /**
   * Function that override the equals method
   * @return true if the toString values of caller and argument are the same
   */
   @Override
   public boolean equals(Object b){
     if (b == null || !(b instanceof Expression) || (this.getClass()
     != b.getClass())){
       return false;
     }
     return b.toString().equals(this.toString());
   }
 }
