package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 * I have no idea how this works but it allows for custom JPanels (like ExpressionPanel) to be
 * implemented on JLists.
 * 
 * @author Alec Vietry
 * @version 11-16-23
 */
public class ExpressionPanelRenderer extends JPanel implements ListCellRenderer<ExpressionPanel>
{
  private static final long serialVersionUID = 1L;

  /**
   * Basic constructor. Sets layout to default flow layout.
   */
  public ExpressionPanelRenderer()
  {
    setLayout(new FlowLayout());
  }
  
  /**
   * No idea what this does but it needs to be called by something for everything to work.
   * 
   * @param list the JList items
   * @param value the expression panel
   * @param index the place in the list
   * @param isSelected if the current JList item is selected
   * @param cellHasFocus if the current cell has focus
   */
  @Override
  public Component getListCellRendererComponent(final JList<? extends ExpressionPanel> list,
      final ExpressionPanel value, final int index, final boolean isSelected, 
      final boolean cellHasFocus)
  {
    removeAll();
    setLayout(new FlowLayout(FlowLayout.LEFT));
    add(value);
    setBackground(isSelected ? Color.LIGHT_GRAY : CalculatorWindow.DISPLAY_BLUE);
    setBorder(null);  
    return this;
  }
}
