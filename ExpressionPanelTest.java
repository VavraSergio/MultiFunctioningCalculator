package testing;

import javax.swing.JFrame;

import gui.ExpressionPanel;
import numbers.Expression;
import numbers.MixedFraction;
import numbers.Operation;

public class ExpressionPanelTest
{

  public static void main(String[] args)
  {
    JFrame frame = new JFrame("ahh");
    MixedFraction mf1 = new MixedFraction(10,10,10);
    MixedFraction mf2 = new MixedFraction(11,11,11);
    MixedFraction mf3 = new MixedFraction(12,12,12);
    Expression ex = new Expression(mf1, mf2, mf3, Operation.ADD);
    ExpressionPanel exp = new ExpressionPanel(ex); 
    frame.add(exp);
    frame.setSize(500, 500);
    frame.setVisible(true);
  }
}
