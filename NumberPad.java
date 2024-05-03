package gui;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * This is the NumberPad class for our calculator project.
 * 
 * @author Stephen Ruhlen
 * @version 1.0
 */
public class NumberPad extends JPanel
{

  private static final long serialVersionUID = 1L;
  private ActionListener listener;
  private static final Color PUKE = new Color(138,138,29);
  private static final Color BLUEGREEN = new Color(7,130,130);
  private static final Color PURPLE = new Color(140,34,140);
  private static final Color MAGMA = new Color(66, 101, 227);

  /**
   * Constructor method for the NumberPad class.
   * 
   * @param a
   *          the actionlistener that checks for button clicks
   */
  public NumberPad(final ActionListener a)
  {
    super();
    this.listener = a;
    setupLayout();
  }

  /**
   * The addButton method to add new buttons to the numberpad.
   * 
   * @param name
   *          the name of the button
   * @return constructed button
   */
  private JButton addButton(final String name)
  {
    JButton button = new JButton(name);
    Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 3, false);
    button.setBorder(border);
    button.setBackground(CalculatorWindow.BACKGROUND_GREY); 
    button.addActionListener(this.listener);
    button.setFont(new Font("Courier", Font.BOLD, 24));
    button.setFocusable(false);
    button.setPreferredSize(new Dimension(48,48));
    return button;
  }

  /**
   * Overloaded addButton to allow for colored text on button.
   * 
   * @param name of the button
   * @param color of the button's text
   * @return a button with a new color.
   */
  private JButton addButton(final String name, final Color color)
  {
    JButton button = addButton(name);
    button.setForeground(color);
    return button;
  }

  /**
   * Method to set up the layout of the number pad.
   */
  private void setupLayout()
  {
    Dimension dim = new Dimension(300, 300);
    setMinimumSize(dim);
    setMaximumSize(dim);
    setPreferredSize(dim);
    setBackground(CalculatorWindow.BACKGROUND_GREY);
    GridBagLayout layout = new GridBagLayout();
    setLayout(layout);
    JButton[] buttons = {addButton("R", PUKE), addButton("C", PUKE), addButton("\u2190", PUKE),
        addButton("+", BLUEGREEN), addButton("7"), addButton("8"), addButton("9"),
        addButton("-", BLUEGREEN), addButton("4"), addButton("5"), addButton("6"),
        addButton("x", BLUEGREEN), addButton("1"), addButton("2"), addButton("3"),
        addButton("\u00F7", BLUEGREEN)};
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.insets = new Insets(7, 8, 7, 8);
    c.weightx = 1;
    c.weighty = 1;
    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        c.gridx = j;
        c.gridy = i;
        c.gridwidth = 1;
        add(buttons[(i * 4) + j], c);
      }
    }
    // mediant button
    c.gridx = 5;
    c.gridy = 0;
    add(addButton("med", BLUEGREEN), c);
    // exponent button
    c.gridx = 5;
    c.gridy = 1;
    add(addButton("X\u207F", BLUEGREEN), c);
    // Greater thabn
    c.gridx = 5;
    c.gridy = 2;
    add(addButton(">", MAGMA), c);
    // Lesser than
    c.gridx = 5;
    c.gridy = 3;
    add(addButton("<", MAGMA), c);
    // Not equal to
    c.gridx = 5;
    c.gridy = 4;
    add(addButton("==", MAGMA), c);
    // Inverse button
    c.gridx = 6;
    c.gridy = 1;
    add(addButton("Inv", PURPLE), c);
    // simplify button
    c.gridx = 6;
    c.gridy = 2;
    add(addButton("\u2193", PURPLE), c);
    // positive-negative button
    c.gridx = 6;
    c.gridy = 0;
    add(addButton("\u00B1", PURPLE), c);
    c.gridx = 0;
    c.gridy = 4;
    c.gridwidth = 2;
    add(addButton("0"), c);
    c.gridx = 2;
    c.gridy = 4;
    c.gridwidth = 1;
    // Position button
    add(addButton("P"), c);
    c.gridx = 3;
    c.gridy = 4;
    add(addButton("=", BLUEGREEN), c);
  }
}
