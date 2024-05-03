package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Hopefully this class makes the fractionbar look good.
 * 
 * @author alecv
 * @version 12-7-23
 */
public class LinePanel extends JPanel
{
  private static final long serialVersionUID = -8572733082696401690L;
  
  /**
   * Allows the line to be painted.
   */
  @Override
  protected void paintComponent(final Graphics g)
  {
    super.paintComponent(g);
    setBackground(CalculatorWindow.DISPLAY_BLUE);

    int width = getWidth();
    int height = getHeight();

    g.drawLine(0, height / 2, width, height / 2);
  }
}
