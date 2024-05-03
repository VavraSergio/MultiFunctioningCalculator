package numbers;

/**
 * This class creates an expression stored as three MixedFractions and an Operation. This is purely
 * for data storage.
 * 
 * @author Alec Vietry
 * @version 11-6-23
 */
public class Expression
{
  private MixedFraction leftOp, rightOp, answer;
  private Operation operator;
  
  /**
   * Allows for easy creation of an expression with all null components.
   */
  public Expression()
  {
    this(null, null, null, null);
  }
  
  /**
   * Constructor for boolean expression (no answer).
   * 
   * @param left op
   * @param right op
   * @param op operation
   */
  public Expression(final MixedFraction left, final MixedFraction right, final Operation op)
  {
    this(left, right, null, op);
  }

  /**
   * Will creates an expresison with.
   * 
   * @param left op
   * @param right op
   * @param ans answer to expression
   * @param op operation
   */
  public Expression(final MixedFraction left, final MixedFraction right, final MixedFraction ans,
      final Operation op)
  {
    leftOp = left;
    rightOp = right;
    answer = ans;
    operator = op;
  }

  /**
   * Returns the left operation.
   * 
   * @return left operation
   */
  public MixedFraction getLeftOp()
  {
    return leftOp;
  }

  /**
   * Sets the left operation given a fraction.
   * 
   * @param leftOp
   */
  public void setLeftOp(final MixedFraction leftOp)
  {
    this.leftOp = leftOp;
  }

  /**
   * Returns the right operation.
   * 
   * @return right operation
   */
  public MixedFraction getRightOp()
  {
    return rightOp;
  }

  /**
   * Sets the right operation given a fraction.
   * @param rightOp
   */
  public void setRightOp(final MixedFraction rightOp)
  {
    this.rightOp = rightOp;
  }

  /**
   * Returns the answer.
   * 
   * @return mixedFraction answer
   */
  public MixedFraction getAnswer()
  {
    return answer;
  }

  /**
   * Sets the answer.
   * 
   * @param answer
   */
  public void setAnswer(final MixedFraction answer)
  {
    this.answer = answer;
  }

  /**
   * Returns the operator.
   * 
   * @return operator
   */
  public Operation getOperator()
  {
    return operator;
  }

  /**
   * Sets the operaor.
   * 
   * @param operator
   */
  public void setOperator(final Operation operator)
  {
    this.operator = operator;
  }

}
