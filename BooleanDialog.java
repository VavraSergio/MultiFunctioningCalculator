package gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * A simple class for creating a pop-up which displays the result of a boolean expression.
 * 
 * @author alecv
 * @version 12-10-23
 */
public class BooleanDialog extends JDialog
{
  private static final long serialVersionUID = 7871473638343603853L;

  /**
   * Constructor. The boolean value will be the only thing displayed.
   * 
   * @param frame parent frame
   * @param bool the boolean to express
   */
  public BooleanDialog(final JFrame frame, final boolean bool)
  {
    super(frame, "Boolean Result");
    
    String result = bool ? "True" : "False";   
    JLabel label = new JLabel(result);
    label.setFont(new Font("Arial", Font.PLAIN, 30));

    setLayout(new FlowLayout());
    add(label);
    setSize(200,100);
    setResizable(false);
    setAlwaysOnTop(true);
    setLocationRelativeTo(null); 
    setVisible(true);
  }
}
