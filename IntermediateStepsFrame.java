package gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * A frame that attaches to the CalculatorWindow which shows the intermediate steps.
 * 
 * @author alecv
 * @version 12-4-23
 */
public class IntermediateStepsFrame extends SlideoutFrame
{
  private static final long serialVersionUID = 1L;
  private JTextArea textArea;
  private JScrollPane scrollPane;
  private static final String OPEN = "<";
  private static final String CLOSED = ">";

  /**
   * Constructor.
   * 
   * @param mainFrame
   *          allows this frame to be "glued" to the main frame.
   */
  public IntermediateStepsFrame(final CalculatorWindow mainFrame)
  {
    super(mainFrame);

    openButton.setText(OPEN);
    textArea = new JTextArea();
    textArea.setBackground(CalculatorWindow.DISPLAY_BLUE);
    textArea.setEditable(false);
    textArea.setVisible(false);
    
    scrollPane = new JScrollPane(textArea);
    scrollPane.setBackground(CalculatorWindow.DISPLAY_BLUE);
    scrollPane.setBorder(null);
    scrollPane.setVisible(false);

    add(scrollPane, BorderLayout.CENTER);
    add(openButton, BorderLayout.WEST);
    
    setVisible(true);
  }

  /**
   * Reads from the text file and adds that to the display.
   */
  public void setTextArea()
  {
    StringBuilder steps = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(
        new FileReader(System.getProperty("user.dir") + "/calculator_results.txt")))
    {
      String line;
      while ((line = reader.readLine()) != null)
      {
        steps.append(line).append("\n");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    textArea.setText(steps.toString());
  }

  /**
   * Opening the frame to the left requires some absolute foolery.
   */
  @Override
  public void closeFrame()
  {
    Timer timer = new Timer(1, new ActionListener()
    {
      private int targetWidth = 450;
      private int currentWidth = getWidth();

      @Override
      public void actionPerformed(final ActionEvent e)
      {
        if (currentWidth < targetWidth)
        {
          currentWidth += 15;
          setSize(currentWidth, getHeight());
          ((CalculatorWindow) mainFrame).updateStepsPosition();
        }
        else
        {
          setSize(targetWidth, getHeight());
          ((Timer) e.getSource()).stop();
          ((CalculatorWindow) mainFrame).updateStepsPosition();
          openButton.setText(CLOSED);
          scrollPane.setVisible(true);
          textArea.setVisible(true);
        }
      }
    });
    timer.start();
  }

  /**
   * Closing the frame from the left requires some absolute foolery.
   */
  @Override
  public void openFrame()
  {
    Timer timer = new Timer(1, new ActionListener()
    {
      private int targetWidth = 50;
      private int currentWidth = getWidth();

      @Override
      public void actionPerformed(final ActionEvent e)
      {
        if (currentWidth > targetWidth)
        {
          currentWidth -= 15;
          setSize(currentWidth, getHeight());
          ((CalculatorWindow) mainFrame).updateStepsPosition();
        }
        else
        {
          setSize(targetWidth, getHeight());
          ((Timer) e.getSource()).stop();
          ((CalculatorWindow) mainFrame).updateStepsPosition();
          openButton.setText(OPEN);
        }
      }
    });
    timer.start();
    textArea.setVisible(false);
    scrollPane.setVisible(false);
  }
}
