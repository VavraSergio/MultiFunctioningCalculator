package gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionListener;

import numbers.Expression;

/**
 * This is the History window that holds the history of the fraction calculator.
 * 
 * @author Alec Vietry
 * @version 11-15-23
 */
public class HistoryFrame extends SlideoutFrame implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private JList<ExpressionPanel> list;
  private DefaultListModel<ExpressionPanel> listModel;
  private JScrollPane scrollPane;
  private Point point;
  private CalculatorWindow frame;

  /**
   * Constructor. Doesn't do anything fancy.
   * 
   * @param window
   *          the window in use
   */
  public HistoryFrame(final JFrame window)
  {
    super(window);

    listModel = new DefaultListModel<>();
    list = new JList<>(listModel);
    list.setCellRenderer(new ExpressionPanelRenderer());
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addListSelectionListener((ListSelectionListener) mainFrame);
    list.setBackground(CalculatorWindow.DISPLAY_BLUE);
    addListener();

    scrollPane = new JScrollPane(list);
    scrollPane.setBackground(CalculatorWindow.DISPLAY_BLUE);
    scrollPane.setBorder(null);

    frame = (CalculatorWindow) window;

    add(scrollPane, BorderLayout.CENTER);
    add(openButton, BorderLayout.EAST);

    scrollPane.setVisible(false);
    setVisible(true);
  }
  
  /**
   * Adds the mouse listener to choose the fracion.
   */
  private void addListener()
  {
    list.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseClicked(MouseEvent e)
      {
        point = e.getPoint();
        if (!listModel.isEmpty())
          chooseCell(list.getSelectedValue());
      }
    });
  }

  /**
   * Lets some magic happen. Takes an expression panel and does some magic to find the location of
   * each little thing.
   * 
   * @param listPanel the list to chop up
   */
  private void chooseCell(final ExpressionPanel listPanel)
  {
    int leftOp = listPanel.getLeftOperand().getWidth();
    int rightOp = listPanel.getRightOperand().getWidth();
    int op = listPanel.getOperator().getWidth() + 30;
    int eq = listPanel.getEquals().getWidth() + 30;
    int loc = (int) point.getX();
    System.out.println(
        leftOp + "," + op + "," + rightOp + "," + eq + "    " + loc + "," + listPanel.getWidth());
    if (loc < leftOp)
      frame.valueSelected(listPanel.getExpression().getLeftOp());
    else if (loc >= (leftOp + op) && loc < (leftOp + op + rightOp))
      frame.valueSelected(listPanel.getExpression().getRightOp());
    else if (loc >= (leftOp + op + rightOp + eq))
      frame.valueSelected(listPanel.getExpression().getAnswer());
  }

  /**
   * Adds the expression to the ExpressionPanel ArrayList. Will automatically display on panel.
   * 
   * @param exp
   *          the expression
   */
  public void addExpression(final Expression exp)
  {
    listModel.addElement(new ExpressionPanel(exp));
  }

  /**
   * Sets the fractions style.
   * 
   * @param style
   *          to set
   */
  public void setFractionStyle(final FractionStyle style)
  {
    for (int i = 0; i < listModel.getSize(); i++)
    {
      listModel.get(i).setFractionStyle(style);
    }
    list.updateUI();
  }

  /**
   * Handels the button press to open the frame.
   * 
   * @param e
   *          button press
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    switch (e.getActionCommand())
    {
      case OPEN:
        openFrame();
        break;
      case CLOSED:
        closeFrame();
        break;
    }
  }

  /**
   * Opens the frame.
   */
  public void openFrame()
  {
    super.openFrame();
    scrollPane.setVisible(true);
  }

  /**
   * Closes the frame.
   */
  public void closeFrame()
  {
    scrollPane.setVisible(false);
    super.closeFrame();
  }

  /**
   * Allows the list to be updated after resizing.
   */
  public void updateListUI()
  {
    scrollPane.repaint();
  }
}
