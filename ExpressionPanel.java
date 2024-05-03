package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import numbers.Expression;

/**
 * Creates a panel that dispays the expression. Mainly used for history window. Re-uses a ton of
 * stuff from DisplayPanel beacsue I am lazy and not object oriented.
 * 
 * @author Alec Vietry
 * @version 11-15-23
 */
public class ExpressionPanel extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Expression expression;
  private FractionPanel leftOperand, rightOperand, expressionAnswer;
  private JPanel operator, equals;

  public ExpressionPanel(final Expression expression)
  {
    super();

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    this.expression = expression;

    JLabel operatorLabel = new JLabel();
    operatorLabel.setText(DisplayPanel.operationToString(expression.getOperator()));
    operatorLabel.setFont(new Font("Courier", Font.PLAIN, 25));
    operator = new JPanel(new FlowLayout());
    operator.setBackground(CalculatorWindow.DISPLAY_BLUE);
    operator.add(operatorLabel);

    leftOperand = new FractionPanel();
    rightOperand = new FractionPanel();
    expressionAnswer = new FractionPanel();

    // leftOperand.setBackground(Color.RED);
    // rightOperand.setBackground(Color.BLUE);
    // expressionAnswer.setBackground(Color.GRAY);

    JLabel equalsLabel = new JLabel();
    equalsLabel.setText("=");
    equalsLabel.setFont(new Font("Arial", Font.PLAIN, 22));
    equals = new JPanel(new FlowLayout());
    equals.setBackground(CalculatorWindow.DISPLAY_BLUE);
    equals.add(equalsLabel);

    equals.setVisible(expression.getAnswer() != null);

    setLayout(gbl);

    // For left expression
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbl.setConstraints(leftOperand, gbc);
    add(leftOperand);

    // For operator
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.insets = new Insets(0, 15, 0, 15);
    gbl.setConstraints(operator, gbc);
    add(operator);

    // For right expression
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbl.setConstraints(rightOperand, gbc);
    add(rightOperand);

    // For the equals sign
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.insets = new Insets(0, 15, 0, 15);
    gbl.setConstraints(equals, gbc);
    add(equals);

    // For expression answer
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 0;
    gbl.setConstraints(expressionAnswer, gbc);
    add(expressionAnswer);

    setFractions();
    updateFractions();
    setBackground(CalculatorWindow.DISPLAY_BLUE);
    setVisible(true);
  }

  /**
   * Sets the fraction panels to the proper fractions from the expression.
   */
  private void setFractions()
  {
    leftOperand.setFraction(expression.getLeftOp());
    rightOperand.setFraction(expression.getRightOp());
    expressionAnswer.setFraction(expression.getAnswer());
    if (expression.getAnswer().isNull())
      expressionAnswer.setNumeratorEmpty(false);
  }

  /**
   * Updates all the fractionPanels to dispaly changes.
   */
  private void updateFractions()
  {
    leftOperand.update();
    rightOperand.update();
    expressionAnswer.update();
  }

  /**
   * Sets the styles of the fractions.
   * 
   * @param style
   *          the style to set.
   */
  public void setFractionStyle(final FractionStyle style)
  {
    leftOperand.setFractionStyle(style);
    rightOperand.setFractionStyle(style);
    expressionAnswer.setFractionStyle(style);
  }

  /**
   * Allows for public access for determining if the equals should be displayed. Mostly used for
   * boolean expressions.
   * 
   * @param bool
   *          state to set
   */
  public void setEquals(final boolean bool)
  {
    equals.setVisible(bool);
  }

  /**
   * Allows leftOp to go out.
   * 
   * @return leftOp
   */
  public FractionPanel getLeftOperand()
  {
    return leftOperand;
  }

  /**
   * Allows leftOp to go out.
   * 
   * @return rightOp
   */
  public FractionPanel getRightOperand()
  {
    return rightOperand;
  }

  /**
   * Allows leftOp to go out.
   * 
   * @return expressionAns
   */
  public FractionPanel getExpressionAnswer()
  {
    return expressionAnswer;
  }

  /**
   * Allows leftOp to go out.
   * 
   * @return operator
   */
  public JPanel getOperator()
  {
    return operator;
  }

  /**
   * Allows leftOp to go out.
   * 
   * @return equals
   */
  public JPanel getEquals()
  {
    return equals;
  }

  /**
   * Allows underlying data to be gotten.
   * 
   * @return expression
   */
  public Expression getExpression()
  {
    return expression;
  }
}
