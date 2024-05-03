package gui;

import java.io.Serializable;

import javax.swing.ActionMap;
import javax.swing.InputMap;

public class KeyBindings implements Serializable
{
  private static final long serialVersionUID = 1L;
  private InputMap inputMap;
  private ActionMap actionMap;
  
  public KeyBindings(InputMap input, ActionMap action) {
    this.setInputMap(input);
    this.setActionMap(action);
  }

  public InputMap getInputMap()
  {
    return inputMap;
  }

  public void setInputMap(InputMap inputMap)
  {
    this.inputMap = inputMap;
  }

  public ActionMap getActionMap()
  {
    return actionMap;
  }

  public void setActionMap(ActionMap actionMap)
  {
    this.actionMap = actionMap;
  }
  
  

}
