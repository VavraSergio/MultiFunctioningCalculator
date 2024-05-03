package gui;

import java.awt.Color;

import javax.swing.JButton;

/**
 * PlayBackButton class
 * @author Stephen Ruhlen 
 * @version 1.0
 */
public class PlayBackButton extends JButton
{

  private static final long serialVersionUID = 1L;
  private boolean pressed;
  
  /**
   * Default constructor. Creates an empty button.
   */
  public PlayBackButton() {
    super();
    this.pressed = false;
  }
  
  /**
   * Creates a button with text.
   * 
   * @param icon the text to set on the button
   */
  public PlayBackButton(String icon) {
    super(icon);
    this.pressed = false;
  }
  
  /**
   * Allows the state of the button to be set.
   * 
   * @param set the state
   */
  public void setPressed(boolean set) {
    pressed = set;
    Color color = set ? new Color(107, 107, 107) : CalculatorWindow.BACKGROUND_GREY;
    setBackground(color);
  }
  
  /**
   * Allows the state of the button to be retrieved.
   * 
   * @return true if button is pressed, false if not
   */
  public boolean getPressed() {
    return this.pressed;
  }
}
