package gui.circlegraph;

import numbers.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

/**
 * Provides a window for displaying a circle graph representation of arithmetic 
 * operations with fractions.
 */
public class CircleGraphWindow extends JFrame 
{

  private static final long serialVersionUID = 1L;
  JPanel panel;
  String operator;
  MixedFraction leftOperandFraction;
  MixedFraction rightOperandFraction;
  MixedFraction resultFraction;
  Operation operatorOp;
  private static final int circleRadius = 50;
  private static final int spacing = circleRadius / 2; 

  /**
   * Constructs a CircleGraphWindow for displaying a circle graph of a fraction operation.
   *
   * @param leftOperand  The left operand fraction.
   * @param operator     The arithmetic operator.
   * @param rightOperand The right operand fraction.
   * @throws IOException 
   * @throws IllegalArgumentException 
   */
  public CircleGraphWindow(final MixedFraction leftOperand, final Operation operator, 
      final MixedFraction rightOperand) throws IllegalArgumentException, IOException 
  {
    super();
    if (operator == Operation.ADD) 
    {
      this.operator = "+";
    } else if (operator == Operation.SUBTRACT) 
    {
      this.operator = "-";
    } else if (operator == Operation.MULTIPLY) 
    {
      this.operator = "x";
    } else if (operator == Operation.DIVIDE) 
    {
      this.operator = "/";
    } else if (operator == Operation.MEDIANT) 
    {
      // Assuming MEDIANT is some custom operation you handle differently
      this.operator = "med";
    }
    this.operatorOp = operator;
    this.leftOperandFraction = leftOperand;
    this.rightOperandFraction = rightOperand;
    this.resultFraction = calculateResult(leftOperand, operator, rightOperand);
    setVisible(true);
    setupLayout();
    setVisible(true);
  }

  /**
   * Draws a pie chart representation of a fraction.
   *
   * @param g        The Graphics object for drawing.
   * @param operand  The fraction to be represented.
   * @param xCoord   The starting X-coordinate for drawing.
   * @param yCoord   The Y-coordinate for drawing.
   * @return The updated X-coordinate after drawing.
   */
  private int drawPieChart(final Graphics g, final MixedFraction operand, int xCoord, int yCoord) {
    int wholeNumber = Math.abs(operand.getCoefficient());
    int numerator = Math.abs(operand.getNumerator());
    int denominator = operand.getDenominator();

    // Check if the fraction is negative
    boolean isNegative = operand.getCoefficient() < 0 || operand.getNumerator() < 0;

    // Draw the negative sign if the fraction is negative
    if (isNegative) {
      g.setColor(Color.BLACK);
      g.drawString("-", xCoord, yCoord + circleRadius / 2);
      xCoord += g.getFontMetrics().stringWidth("-") + spacing;
    }

    // Draw the whole number part
    if (wholeNumber > 2) 
    {
      g.setColor(Color.BLUE);
      g.fillOval(xCoord, yCoord, circleRadius, circleRadius);
      xCoord += circleRadius + spacing;
      g.setColor(Color.BLACK);
      g.drawString(Integer.toString(wholeNumber), xCoord, yCoord + circleRadius / 2);
      xCoord += g.getFontMetrics().stringWidth(Integer.toString(wholeNumber)) + spacing;
    } else {
      for (int i = 0; i < wholeNumber; i++) 
      {
        g.setColor(Color.BLUE);
        g.fillOval(xCoord, yCoord, circleRadius, circleRadius);
        xCoord += circleRadius + spacing;
      }
    }

    // Draw the fractional part as a pie chart
    if (numerator > 0) 
    {
      g.setColor(Color.RED);
      int startAngle = 0;
      int arcAngle = (int) Math.round(360 * (numerator / (double) denominator));
      g.fillArc(xCoord, yCoord, circleRadius, circleRadius, startAngle, arcAngle);
      xCoord += circleRadius + spacing;
    }

    return xCoord;
  }

  @Override
  public void paint(final Graphics g) 
  {
    super.paint(g);
    int xCoord = 10; // Starting X coordinate
    int spacing = circleRadius / 2; // Spacing between elements

    // Draw the left operand as a pie chart and update xCoord
    xCoord = drawPieChart(g, leftOperandFraction, xCoord, 50) + spacing;

    // Draw the operator with spacing and update xCoord
    g.drawString(operator, xCoord, 100);
    xCoord += g.getFontMetrics().stringWidth(operator) + spacing;

    // Draw the right operand as a pie chart and update xCoord
    xCoord = drawPieChart(g, rightOperandFraction, xCoord, 50) + spacing;

    // Draw equals sign with spacing and update xCoord
    g.drawString("=", xCoord, 100);
    xCoord += g.getFontMetrics().stringWidth("=") + spacing;

    // Calculate the result and draw the result as a pie chart
    try
    {
      this.resultFraction = calculateResult(leftOperandFraction, operatorOp, rightOperandFraction);
    }
    catch (IllegalArgumentException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    drawPieChart(g, resultFraction, xCoord, 50);
  }

  /**
   * Formats a fraction as a string.
   *
   * @param numerator   The numerator of the fraction.
   * @param denominator The denominator of the fraction.
   * @return The formatted fraction string.
   */
  private String formatFractionString( int numerator, int denominator) 
  {
    // Simplify the fraction for display purposes
    int gcd = gcd(numerator, denominator);
    numerator /= gcd;
    denominator /= gcd;
    return numerator + "/" + denominator;
  }

  /**
   * Calculates the greatest common divisor of two numbers using Euclid's algorithm.
   *
   * @param a The first number.
   * @param b The second number.
   * @return The greatest common divisor.
   */
  private int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  private MixedFraction calculateResult(final MixedFraction leftOperand, final Operation operator, 
      final MixedFraction rightOperand) throws IllegalArgumentException, IOException 
  {
    // Assume CalculatorFunctions.calculate() returns a MixedFraction representing the result
    return CalculatorFunctions.calculate(leftOperand, rightOperand, operator, 
        FractionForm.REDUCED_PROPER);
  }

  private void setupLayout() 
  {
    this.setSize(800, 200); // May need to adjust size based on the number of operands
    this.panel = new JPanel();
    this.add(panel);
    this.panel.repaint();
  }
}
