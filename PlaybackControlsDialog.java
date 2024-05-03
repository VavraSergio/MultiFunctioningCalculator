package gui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Creates the buttons for controlling the recording.
 * 
 * @author Alec Vietry
 * @version 12-2-23
 */
public class PlaybackControlsDialog extends JDialog implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private PlayBackButton pauseButton, stopButton, startButton;
  private JLabel recordFileName;
  private JSlider slider;
  private Timer timer;
  private String file;
  private DisplayPanel display;
  private int delay = 2000;

  /**
   * Class constructor. Creates and adds the buttons to layout.
   * 
   * @throws FileNotFoundException
   */
  public PlaybackControlsDialog(final String fileName, final DisplayPanel display) throws FileNotFoundException
  {
    super();
    
    this.display = display;
    
    file = fileName;

    pauseButton = new PlayBackButton();
    startButton = new PlayBackButton("\u25b6");
    stopButton = new PlayBackButton("\u25A0");
    slider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
    slider.setMajorTickSpacing(10);

    recordFileName = new JLabel("Playback from: " + fileName);

    pauseButton.setFont(new Font("Arial", Font.BOLD, 22));
    startButton.setFont(new Font(null, Font.PLAIN, 22));
    stopButton.setFont(new Font("Arial", Font.PLAIN, 38));
    recordFileName.setFont(new Font("Arial", Font.PLAIN, 20));

    pauseButton.setBorderPainted(false);
    startButton.setBorderPainted(false);
    stopButton.setBorderPainted(false);

    pauseButton.setFocusable(false);
    startButton.setFocusable(false);
    stopButton.setFocusable(false);

    pauseButton.addActionListener(this);
    startButton.addActionListener(this);
    stopButton.addActionListener(this);

    pauseButton.setBackground(CalculatorWindow.BACKGROUND_GREY);
    startButton.setBackground(CalculatorWindow.BACKGROUND_GREY);
    stopButton.setBackground(CalculatorWindow.BACKGROUND_GREY);
    slider.setBackground(CalculatorWindow.BACKGROUND_GREY);
    getContentPane().setBackground(CalculatorWindow.BACKGROUND_GREY);

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    setLayout(gbl);

    // Add label
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 0;
    gbc.weightx = 1;
    gbc.gridwidth = 4;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    add(recordFileName, gbc);

    // Add record button
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 1;
    gbc.weightx = 0;
    gbc.fill = GridBagConstraints.BOTH;
    add(startButton, gbc);

    // Add pause button
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weighty = 1;
    gbc.weightx = 0;
    gbc.fill = GridBagConstraints.BOTH;
    add(pauseButton, gbc);

    // Add stop button
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.weighty = 1;
    gbc.weightx = 0;
    gbc.fill = GridBagConstraints.BOTH;
    add(stopButton, gbc);

    // Add slider
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 1;
    gbc.weighty = 1;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.BOTH;
    add(slider, gbc);

    timer = createTimer();

    setSize(300, 100);
    pauseButton.setText("| |");
    pauseButton.setActionCommand("P");
    setResizable(false);
    setAlwaysOnTop(true);
    setVisible(true);
  }

  /**
   * Allows the timer to start and stop and what not.
   * 
   * @param e
   *          the event from the buttons.
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String action = e.getActionCommand();
    if (action.equals("\u25b6"))
    {
      timer.start();
      startButton.setPressed(true);
      pauseButton.setPressed(false);
    }
    else if (action.equals("\u25A0"))
    {
      timer.stop();
      timer = null;
      dispose();
    }
    else if (action.equals("P"))
    {
      timer.stop();
      startButton.setPressed(false);
      pauseButton.setPressed(true);
    } 
  }
  
  /**
   * Reads the fractions from the file.
   * 
   * @param myReader the file reader
   * @return a single mixed fraction in string form
   */
  private String readFile(final Scanner myReader)
  {
    String fraction = null;
    if (myReader.hasNext())
      fraction = myReader.next();
    return fraction;  
  }

  /**
   * Creates the timer and links its speed with the slider.
   * 
   * @return the linked timer.
   * @throws FileNotFoundException
   */
  private Timer createTimer() throws FileNotFoundException
  {
    File myObj = new File(file);
    Scanner myReader = new Scanner(myObj);
    Timer timer = new Timer(delay, new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        String frac = readFile(myReader);
        if (frac != null)
          display.placeFraction(frac);
      }

    });
    slider.addChangeListener(new ChangeListener()
    {
      @Override
      public void stateChanged(ChangeEvent e)
      {
        int sliderValue = slider.getValue();
        delay = 2000 - ((sliderValue - 50) * 30); // Mess around to find optimal speed
        timer.setDelay(delay);
      }
    });

    return timer;
  }
}
