package gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import numbers.Expression;
import numbers.MixedFraction;
import numbers.Operation;

/**
 * This class is in charge of saving the current expressions history to memory.
 * @author Stephen Ruhlen
 * @version 1.0
 */
public class HistorySaveFile implements Serializable
{
  private static final long serialVersionUID = 1L;
  private ArrayList<Expression> expressions;
  private Expression currExpression;
  
  /**
   * Constructor method for the HistorySaveFile class.
   */
  public HistorySaveFile() {
    this.currExpression = null;
    this.expressions = new ArrayList<Expression>();
  }
  
  /**
   * Constructor method that takes an expression as an argument.
   * @param expression the inputed expression
   */
  public HistorySaveFile(Expression expression) {
    this.currExpression = expression;
    this.expressions = new ArrayList<Expression>();
    this.expressions.add(currExpression);
  }
  
  /**
   * Method to save the current expressions list to a text file.
   * @param filename the name of the text file
   */
  public void save_txt(String filename) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write(this.toString());
      System.out.println("String has been written to the file successfully.");
  } catch (IOException e) {
      System.err.println("Error writing to the file: " + e.getMessage());
  }
  }
  
  /**
   * Method to turn an operator to string format.
   * @param fraction the current fraction
   * @return the string format of the fraction
   */
  private String opToString(MixedFraction fraction) {
    String result;
      result = String.format("%d(%d/%d)^%d", fraction.getCoefficient(), fraction.getNumerator(),
          fraction.getDenominator(), fraction.getExponent()); 
    return result;
  }
  
  /**
   * ToString method for the current history.
   */
  public String toString() {
    //This works now
    String result = "";
    for (Expression expression : this.expressions) {
      MixedFraction leftOp =  expression.getLeftOp();
      MixedFraction rightOp = expression.getRightOp();
      MixedFraction answer = expression.getAnswer();
      Operation coolio = expression.getOperator();
      HashMap<String, String> operatorMap= new HashMap<String, String>();
      operatorMap.put("ADD", "+");
      operatorMap.put("SUBTRACT", "-");
      operatorMap.put("MULTIPLY", "x");
      operatorMap.put("DIVIDE", "/");
      operatorMap.put("MEDIANT", "med");
      operatorMap.put("GREATER", ">");
      operatorMap.put("LESSER", "<");
      String expressionString = String.format("%s %s %s = %s\n", opToString(leftOp),
          operatorMap.get(coolio.toString()),
          opToString(rightOp),
          opToString(answer));
      result += expressionString;
    }
    return result;
  }
  
  /**
   * Method to read a string into an expression.
   * @return true or false
   */
  public boolean stringToExpression() {
    return false;
  }
  
  /**
   * Method to add an expression to the expression history.
   * @param expression the expression to add to history
   */
  public void addExpression(Expression expression) {
    this.currExpression = expression;
    this.expressions.add(expression);
  }
  
  /**
   * Getter method for the most recent expression in history.
   * @return the most recent expression in history
   */
  public Expression getCurrentExpression() {
    return this.currExpression;
  }
  
  /**
   * Getter method for the full expressions list.
   * @return the full expressions list
   */
  public ArrayList<Expression> getExpressions() {
    return this.expressions;
  }
}
