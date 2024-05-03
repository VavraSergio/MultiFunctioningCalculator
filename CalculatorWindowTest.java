package testing;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import gui.*;

public class CalculatorWindowTest
{
  public static void main(String[] args)
  {
    
    JPanel buttons = new JPanel();
    JPanel textField = new JPanel();
    buttons.setSize(400, 400);
    TitledBorder border = BorderFactory.createTitledBorder("Buttons");
    border.setTitleJustification(TitledBorder.LEFT);
    border.setTitlePosition(TitledBorder.CENTER);  
    buttons.setBorder(border);
    TitledBorder border1 = BorderFactory.createTitledBorder("Text");
    border1.setTitleJustification(TitledBorder.LEFT);
    border1.setTitlePosition(TitledBorder.CENTER);
    textField.setBorder(border1);
    textField.setSize(400, 400);
    
    CalculatorWindow cw = new CalculatorWindow();
  }
}
