package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public abstract class SlideoutFrame extends JFrame implements ActionListener
{
  private static final long serialVersionUID = 1L;
  protected JFrame mainFrame;
  protected JButton openButton;
  protected static final String OPEN = ">";
  protected static final String CLOSED = "<";

  public SlideoutFrame(final JFrame window)
  {
    mainFrame = window;
    openButton = new JButton(OPEN);
    openButton.addActionListener(this);
    openButton.setBackground(CalculatorWindow.DISPLAY_BLUE);
    openButton.setBorderPainted(false);
    openButton.setFocusable(false);

    setAutoRequestFocus(true);
    setWindowListeners();
    getContentPane().setBackground(CalculatorWindow.DISPLAY_BLUE);
    setUndecorated(true);
  }

  /**
   * This method links the actions such as minimizing and maximizing the other main window with this
   * frame.
   */
  public void setWindowListeners()
  {

    mainFrame.addWindowListener(new WindowAdapter()
    {
      // Links the closing
      @Override
      public void windowClosing(WindowEvent e)
      {
        dispose();
      }

      // Links the minimizing
      @Override
      public void windowIconified(WindowEvent e)
      {
        setState(JFrame.ICONIFIED);
      }

      // Links the deminizing
      @Override
      public void windowDeiconified(WindowEvent e)
      {
        setState(JFrame.NORMAL);
      }

      @Override
      public void windowGainedFocus(WindowEvent e)
      {
        setState(JFrame.NORMAL);
      }

      @Override
      public void windowLostFocus(WindowEvent e)
      {
        setState(JFrame.ICONIFIED);
      }
    });
  }

  /**
   * Handels the button press to open the frame.
   * 
   * @param e
   *          button press
   */
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
    Timer timer = new Timer(1, new ActionListener()
    {
      private int targetWidth = 300;
      private int currentWidth = getWidth();

      @Override
      public void actionPerformed(final ActionEvent e)
      {
        if (currentWidth < targetWidth)
        {
          currentWidth += 15;
          setSize(currentWidth, getHeight());
        }
        else
        {
          setSize(targetWidth, getHeight());
          ((Timer) e.getSource()).stop();
          openButton.setText(CLOSED);
        }
      }
    });
    timer.start();
  }

  /**
   * Closes the frame.
   */
  public void closeFrame()
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
        }
        else
        {
          setSize(targetWidth, getHeight());
          ((Timer) e.getSource()).stop();
          openButton.setText(OPEN);
        }
      }
    });
    timer.start();
  }

}
