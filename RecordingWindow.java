package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import numbers.Expression;

/**
 * RecordingWindow class to make a new recording window.
 * 
 * @author Stephen Ruhlen
 * @version 11/30/2023
 */
public class RecordingWindow extends JFrame implements ActionListener
{

  private static final long serialVersionUID = 1L;
  private ArrayList<PlayBackButton> buttons;
  private ArrayList<Expression> historyExpressions;
  private HistorySaveFile saveFile;
  private int width, height, numButtons;
  private PlayBackButton start, pause, stop;
  private JLabel recordFileName;
  private String file;
  public Color RECORD_RED = new Color(166, 15, 15);

  public RecordingWindow(final String fileName)
  {
    super();
    file = fileName;
    recordFileName = new JLabel();
    saveFile = new HistorySaveFile();
    buttons = new ArrayList<PlayBackButton>();
    historyExpressions = new ArrayList<Expression>();

    addButton("START", "\u23fa");
    addButton("PAUSE", "| |");
    addButton("STOP", "\u25A0");
    start = buttons.get(0);
    pause = buttons.get(1);
    stop = buttons.get(2);
    start.setFont(new Font(null, Font.PLAIN, 30));
    pause.setFont(new Font("Arial", Font.BOLD, 22));
    stop.setFont(new Font("Arial", Font.PLAIN, 38));
    start.setForeground(RECORD_RED);
    recordFileName = new JLabel("Record to: " + file);
    recordFileName.setFont(new Font("Arial", Font.PLAIN, 20));
    getContentPane().setBackground(CalculatorWindow.BACKGROUND_GREY);

    setupLayout();
    setAlwaysOnTop(true);
    setVisible(true);
  }

  private void setupLayout()
  {
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc;

    setLayout(gbl);

    // Add Label
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 0;
    gbc.weightx = 1;
    gbc.gridwidth = 3;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.fill = GridBagConstraints.BOTH;
    add(recordFileName, gbc);

    // Add record button
    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weighty = 1;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.BOTH;
    add(start, gbc);

    // Add pause button
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weighty = 1;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.BOTH;
    add(pause, gbc);

    // Add stop button
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.weighty = 1;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.BOTH;
    add(stop, gbc);

    setSize(300, 100);
    setResizable(false);
    this.numButtons = 0;
    this.setTitle("Record Dialog");
  }

  private void addButton(String command, String icon)
  {
    PlayBackButton button = new PlayBackButton(icon);
    int buttonWidth = this.width / 3;
    button.setLocation((buttonWidth * this.numButtons), 0);
    button.setSize(button.getPreferredSize());
    button.addActionListener(this);
    button.setActionCommand(command);
    button.setBackground(CalculatorWindow.BACKGROUND_GREY);
    button.setBorderPainted(false);
    button.setFocusable(false);
    buttons.add(button);
    this.numButtons += 1;
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String action = e.getActionCommand();
    if (action.equals("START") && !start.getPressed())
    {
      start.setPressed(true);
      pause.setPressed(false);
      stop.setPressed(false);
    }
    else if (action.equals("PAUSE") && !pause.getPressed())
    {
      start.setPressed(false);
      pause.setPressed(true);
    }
    else if (action.equals("STOP") && !stop.getPressed())
    {
      this.dispose();
      this.setVisible(false);
      this.saveFile.save_txt(file);
      start.setPressed(false);
    }

  }

  /**
   * Method to handle recording behavior.
   */
  public void startRecording(Expression expression)
  {
    if (this.start.getPressed())
    {
      System.out.println("nicenicenicenice");
      // historyExpressions.add(expression);
      this.saveFile.addExpression(expression);
    }
  }

  public boolean getStart()
  {
    return this.start.getPressed();
  }

  public void setStart(boolean start)
  {
    this.start.setPressed(start);
  }
}
