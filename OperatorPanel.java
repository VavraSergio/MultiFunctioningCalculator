package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import numbers.Operation;

/**
 * Creates a JPanel which holds the operator as data.
 * 
 * @author alecv
 * @version 12-5-23
 */
public class OperatorPanel extends JPanel
{
  private static final long serialVersionUID = -2797602453724413481L;
  private Operation operator;
  private JLabel opLabel;

  /**
   * Constructor.
   * 
   * @param operator
   *          the operator you want to set
   */
  public OperatorPanel(final Operation operator)
  {
    this.operator = operator;
    opLabel = new JLabel(DisplayPanel.operationToString(operator));
    opLabel.setFont(new Font("Courier", Font.PLAIN, 25));

    add(opLabel, BorderLayout.CENTER);

    setVisible(true);
  }

  /**
   * Allows access to the only piece of data stored in this class.
   * 
   * @return the operator
   */
  public Operation getOperation()
  {
    return operator;
  }
}
