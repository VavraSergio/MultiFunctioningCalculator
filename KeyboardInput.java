package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Takes keyboard inputs.
 * 
 * @author Alec Dean <<<<<<< HEAD
 * @version 11-14-23 =======
 * @version 11-13-23 >>>>>>> branch 'main' of https://github.com/bernstdh/F23Team2C
 */
public class KeyboardInput extends AbstractAction
{

  private static final long serialVersionUID = 1L;
  private DisplayPanel display;
  private CalculatorWindow calc;
  private boolean calcSet;
  private ActionEvent action;

  /**
   * Sets the display to the given one.
   * 
   * @param display
   */
  public KeyboardInput(final DisplayPanel display)
  {
    this.display = display;
    this.calcSet = false;
  }
  public KeyboardInput(final DisplayPanel display, final CalculatorWindow calc, final ActionEvent action)
  {
    this.display = display;
    this.calc = calc;
    this.calcSet = true;
    this.action = action;
  }

  @Override
  /**
   * Passes action to display class Action Listener
   * 
   * @param ActionEvent key press.
   */
  public void actionPerformed(final ActionEvent e)
  {
    display.actionPerformed(e);
    if(calcSet) {
    calc.actionPerformed(action);
    }
  }
  
}
