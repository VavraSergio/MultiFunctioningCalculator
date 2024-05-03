package gui;

import numbers.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.circlegraph.CircleGraphWindow;
import numbers.MixedFraction;

/**
 * This class is meant to display the calculator. This includes keyPad, the text input and output,
 * and the file bar.
 * 
 * @author Alec Vietry
 * @version 10-30-23
 */
public class CalculatorWindow extends JFrame implements ActionListener, ListSelectionListener
{

  public static final Color BACKGROUND_GREY = new Color(204, 204, 204);
  public static final Color DISPLAY_BLUE = new Color(201, 231, 255);
  public static final String NEW_CALCULATOR = "NEW_CALCULATOR";
  public static final String EXIT = "EXIT";
  public static final String REDUCED = "REDUCED";
  public static final String PROPER = "PROPER";
  public static final String PIE_CHART = "PIE_CHART";
  public static final String THOUSANDS = "THOUSANDS";
  public static final String BAR = "BAR";
  public static final String SLASH = "SLASH";
  public static final String SOLIDUS = "SOLIDUS";
  public static final String HELP = "HELP";
  public static final String ABOUT = "ABOUT";
  public static final String EDIT = "EDIT";
  public static final String OPEN = "OPEN";
  public static final String SAVE = "SAVE";
  public static final String OPEN_RECORDING = "OPEN_RECORDING";
  public static final String SAVE_RECORDING = "SAVE_RECORDING";
  public static final String PRINT_SESSION = "PRINT_SESSION";

  static String country = System.getProperty("user.country");
  static String language = System.getProperty("user.language");
  // Use the country and language values to set the application's locale
  static Locale LOCALE = new Locale(country, language);
  // Set your application's locale using 'locale'
  static final ResourceBundle STRINGS;

  private static final String TOGGLE_BUTTON_STATE1 = "toggleButtonState1";
  private static final String TOGGLE_BUTTON_STATE2 = "toggleButtonState2";
  private static final long serialVersionUID = 1L;
  private JPanel keyPad;
  private DisplayPanel display; // Working on this
  private HistoryFrame historyFrame;
  private IntermediateStepsFrame stepsFrame;
  private JCheckBoxMenuItem proper, reduced, thousandsSep, pieChart;
  private JRadioButtonMenuItem bar, slash, solidus;
  @SuppressWarnings("unused")
  private HistorySaveFile saveFile = new HistorySaveFile();
  private RecordingWindow recordingWindow = null;
  private PreferenceWindow preferenceWindow;
  private ExpressionPanel listPanel;

  static
  {
    STRINGS = ResourceBundle.getBundle("gui.Strings", LOCALE);
  }

  /**
   * This constructor creates the entire calculator.
   * 
   */
  public CalculatorWindow()
  {
    super();

    historyFrame = new HistoryFrame(this);
    stepsFrame = new IntermediateStepsFrame(this);
    display = new DisplayPanel(historyFrame, recordingWindow, stepsFrame);
    keyPad = new NumberPad(display);
    preferenceWindow = new PreferenceWindow(display, this);

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    JMenuBar menuBar = createMenuBar();
    JLabel icon;
    JPanel imagePanel = new JPanel();

    setLayout(gbl);
    // Create GUI constraints
    // Create menu bar
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 100;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.anchor = GridBagConstraints.NORTH;
    gbl.setConstraints(menuBar, gbc);
    add(menuBar);

    // For Fragile icon
    // Get Fragile image icon
    URL url = this.getClass().getResource("/icons/fragileIcon.jpg");
    icon = new JLabel(new ImageIcon(url));
    imagePanel.add(icon);

    gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0;
    gbc.weighty = 15;
    gbc.gridwidth = 5;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    gbl.setConstraints(imagePanel, gbc);
    add(imagePanel);

    // For text field
    gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 20, 0, 20);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 100;
    gbc.weighty = 100;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(display, gbc);
    add(display);

    // For keyPad
    gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 20, 20, 20);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 100;
    gbc.weighty = 100;
    gbc.anchor = GridBagConstraints.NORTH;
    gbc.fill = GridBagConstraints.BOTH;
    gbl.setConstraints(keyPad, gbc);
    add(keyPad);

    // Set frame components
    try
    {
      CalculatorFunctions.deleteFileContents("calculator_results.txt");
    }
    catch (FileNotFoundException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    setupFrames();

    setVisible(true);
  }

  /**
   * Listens for user action on the menu bar.
   * 
   * @param e
   *          the action that took place
   */
  public void actionPerformed(final ActionEvent e)
  {
    String action = e.getActionCommand();
    if (action.equals(STRINGS.getString(NEW_CALCULATOR)))
    {
      new CalculatorWindow();
    }
    else if (action.equals(STRINGS.getString(EXIT)))
    {
      dispose();
      historyFrame.dispose();
      stepsFrame.dispose();
    }
    else if (action.equals(STRINGS.getString(REDUCED)))
    {
      display.toggleReduced(reduced.isSelected());
      reduced.setSelected(!reduced.isSelected());
    }
    else if (action.equals(STRINGS.getString(PROPER)))
    {
      display.toggleProper(proper.isSelected());
      proper.setSelected(!proper.isSelected());
    }
    else if (action.equals(STRINGS.getString(PIE_CHART)))
    {
      MixedFraction leftOp = display.getExpression().getLeftOp();
      MixedFraction rightOp = display.getExpression().getRightOp();
      Operation operator = display.getExpression().getOperator();
      try
      {
        new CircleGraphWindow(leftOp, operator, rightOp);
      }
      catch (IllegalArgumentException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      catch (IOException e1)
      {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

    }
    else if (action.equals(STRINGS.getString(THOUSANDS)))
    {
      display.setThousandsSep(thousandsSep.isSelected());
    }
    else if (action.equals(STRINGS.getString(BAR)))
    {
      display.setFractionsStyle(FractionStyle.BAR);
      bar.setSelected(!bar.isSelected());
    }
    else if (action.equals(STRINGS.getString(SLASH)))
    {
      display.setFractionsStyle(FractionStyle.SLASH);
      slash.setSelected(!slash.isSelected());
    }
    else if (action.equals(STRINGS.getString(SOLIDUS)))
    {
      display.setFractionsStyle(FractionStyle.SOLIDUS);
      solidus.setSelected(!solidus.isSelected());
    }
    else if (action.equals(STRINGS.getString(HELP)))
    {
      new HTMLPage(LOCALE);
    }
    else if (action.equals(STRINGS.getString(ABOUT)))
    {
      new AboutDialog(this);
    }
    else if (action.equals(STRINGS.getString(EDIT)))
    {
      preferenceWindow.setVisible(true);
    }
    else if (action.equals(STRINGS.getString(OPEN)))
    {
      display.openKeyBinding(this);
    }
    else if (action.equals(STRINGS.getString(SAVE)))
    {
      display.saveKeyBinding();
    }
    else if (action.equals(STRINGS.getString(PRINT_SESSION)))
    {
      PrintHistory history = new PrintHistory(historyFrame);
      history.actionPerformed(e);
    }
    else if (action.equals(STRINGS.getString(OPEN_RECORDING)))
    {
      JFileChooser fileChooser = new JFileChooser();
      if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        try
        {
          new PlaybackControlsDialog(fileChooser.getSelectedFile().getName(), display);
        }
        catch (FileNotFoundException e1)
        {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
    }
    else if (action.equals(STRINGS.getString(SAVE_RECORDING)))
    {
      JFileChooser fileChooser = new JFileChooser();
      if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        recordingWindow = new RecordingWindow(fileChooser.getSelectedFile().getName());
        display.setRecordingWindow(recordingWindow);
      }
    }
  }

  /**
   * Gets the proper location of the thingymo bob
   * 
   * @param exp
   */
  public void valueSelected(final MixedFraction mf)
  {
    display.placeFraction(mf);
  }

  /**
   * Listens for list selection for history window.
   * 
   * @param e
   *          the list selection event
   */
  public void valueChanged(final ListSelectionEvent e)
  {
    ExpressionPanel ep = (ExpressionPanel) ((JList<?>) e.getSource()).getSelectedValue();
    listPanel = ep;
  }

  /**
   * This method creates the menu bar. Could move to its own class.
   * 
   * @return menu bar
   */
  private JMenuBar createMenuBar()
  {
    JMenuBar menuBar = new JMenuBar();

    // The first layer of the Jmenu
    JMenu file = new JMenu(STRINGS.getString("FILE"));
    JMenu pref = new JMenu(STRINGS.getString("PREFERENCES"));
    JMenu mode = new JMenu(STRINGS.getString("MODE"));
    JMenu view = new JMenu(STRINGS.getString("VIEW"));
    JMenu style = new JMenu(STRINGS.getString("STYLE"));
    JMenu help = new JMenu(STRINGS.getString(HELP));

    // Menu items for file
    JMenuItem openRecording = new JMenuItem(STRINGS.getString(OPEN_RECORDING));
    JMenuItem saveRecording = new JMenuItem(STRINGS.getString(SAVE_RECORDING));
    JMenuItem print = new JMenuItem(STRINGS.getString(PRINT_SESSION));
    JMenuItem newCalc = new JMenuItem(STRINGS.getString(NEW_CALCULATOR));
    JMenuItem exit = new JMenuItem(STRINGS.getString(EXIT));

    // Menu items for preferences
    JMenuItem edit = new JMenuItem(STRINGS.getString(EDIT));
    JMenuItem open = new JMenuItem(STRINGS.getString(OPEN));
    JMenuItem save = new JMenuItem(STRINGS.getString(SAVE));

    // Menu items for mode
    proper = new JCheckBoxMenuItem(STRINGS.getString(PROPER));
    reduced = new JCheckBoxMenuItem(STRINGS.getString(REDUCED));
    proper.setSelected(true);
    reduced.setSelected(true);
    loadToggleButtonState(proper, TOGGLE_BUTTON_STATE1);
    loadToggleButtonState(reduced, TOGGLE_BUTTON_STATE2);
    addListenerButton(proper, TOGGLE_BUTTON_STATE1);
    addListenerButton(reduced, TOGGLE_BUTTON_STATE2);
    display.toggleProper(proper.isSelected());
    display.toggleReduced(reduced.isSelected());

    // Menu items for view
    pieChart = new JCheckBoxMenuItem(STRINGS.getString(PIE_CHART));
    thousandsSep = new JCheckBoxMenuItem(STRINGS.getString(THOUSANDS));
    pieChart.setSelected(true);

    // Menu items for style
    bar = new JRadioButtonMenuItem(STRINGS.getString(BAR));
    slash = new JRadioButtonMenuItem(STRINGS.getString(SLASH));
    solidus = new JRadioButtonMenuItem(STRINGS.getString(SOLIDUS));
    ButtonGroup menuRadioButtonGroup = new ButtonGroup();
    bar.setSelected(true);
    menuRadioButtonGroup.add(bar);
    menuRadioButtonGroup.add(slash);
    menuRadioButtonGroup.add(solidus);

    // Menu items for help
    JMenuItem about = new JMenuItem(STRINGS.getString(ABOUT));
    JMenuItem helpItem = new JMenuItem(STRINGS.getString(HELP));

    // Adding actionListeners to JMenuItems
    openRecording.addActionListener(this);
    saveRecording.addActionListener(this);
    print.addActionListener(this);
    newCalc.addActionListener(this);
    exit.addActionListener(this);
    edit.addActionListener(this);
    open.addActionListener(this);
    save.addActionListener(this);
    proper.addActionListener(this);
    reduced.addActionListener(this);
    pieChart.addActionListener(this);
    thousandsSep.addActionListener(this);
    bar.addActionListener(this);
    slash.addActionListener(this);
    solidus.addActionListener(this);
    about.addActionListener(this);
    helpItem.addActionListener(this);

    // Add each JMenuItem to the appropriate JMenu
    file.add(openRecording);
    file.add(saveRecording);
    file.add(print);
    file.add(newCalc);
    file.add(exit);

    pref.add(edit);
    pref.add(open);
    pref.add(save);

    mode.add(proper);
    mode.add(reduced);

    view.add(pieChart);
    view.add(thousandsSep);

    style.add(bar);
    style.add(slash);
    style.add(solidus);

    help.add(about);
    help.add(helpItem);

    // Add each JMenu to the JMenuBar
    menuBar.add(file);
    menuBar.add(pref);
    menuBar.add(mode);
    menuBar.add(view);
    menuBar.add(style);
    menuBar.add(help);

    return menuBar;
  }

  /**
   * Adds a listener for the included button.
   * 
   * @param j
   *          to add listener to.
   * @param key
   *          to listen for.
   */
  private void addListenerButton(final JCheckBoxMenuItem j, final String key)
  {
    j.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(final ActionEvent e)
      {
        saveToggleButtonState(j, key);
      }
    });
  }

  /**
   * This method sets the locations and layouts for all the frames, including this one.
   */
  private void setupFrames()
  {
    setTitle("Fragile Mixed Fraction");
    setSize(450, 650);
    setMinimumSize(new Dimension(450, 650));
    getContentPane().setBackground(BACKGROUND_GREY); // Custom color
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setAutoRequestFocus(true);
    setAlwaysOnTop(true);
    historyFrame.setSize(50, (int) (this.getHeight() / 1.5));
    stepsFrame.setSize(50, (int) (this.getHeight() / 1.5));
    // Add the junk for matching the frames together
    addComponentListener(new ComponentAdapter()
    {
      @Override
      public void componentMoved(final ComponentEvent e)
      {
        updateHistoryPosition();
        updateStepsPosition();
      }

      @Override
      public void componentResized(final ComponentEvent e)
      {
        updateHistoryPosition();
        updateStepsPosition();
      }
    });
    updateHistoryPosition();
    updateStepsPosition();
  }

  /**
   * Tries to update the position of the history frame.
   */
  private void updateHistoryPosition()
  {
    Point mainFrameLocation = this.getLocation();
    int attachedFrameX = mainFrameLocation.x + this.getWidth() - 8;
    int attachedFrameY = mainFrameLocation.y + 100;
    historyFrame.setLocation(attachedFrameX, attachedFrameY);
    historyFrame.setSize(historyFrame.getWidth(), this.getHeight() - 200);
    historyFrame.updateListUI();
  }

  /**
   * Updates the position of the steps frame based on the location of this window.
   */
  public void updateStepsPosition()
  {
    Point mainFrameLocation = this.getLocation();
    int attachedFrameX = mainFrameLocation.x - stepsFrame.getWidth() + 12;
    int attachedFrameY = mainFrameLocation.y + 100;
    stepsFrame.setLocation(attachedFrameX, attachedFrameY);
    stepsFrame.setSize(stepsFrame.getWidth(), this.getHeight() - 110);
  }

  /**
   * Saves the state of the toggled button.
   * 
   * @param button
   *          to save state.
   * @param key
   *          to listen for
   */
  private static void saveToggleButtonState(final AbstractButton button, final String key)
  {
    Preferences prefs = Preferences.userNodeForPackage(CalculatorWindow.class);
    prefs.putBoolean(key, button.isSelected());
  }

  /**
   * Loads the state of the toggled button.
   * 
   * @param button
   *          to load state
   * @param key
   *          to listen for.
   */
  private static void loadToggleButtonState(final AbstractButton button, final String key)
  {
    Preferences prefs = Preferences.userNodeForPackage(CalculatorWindow.class);
    boolean savedState = prefs.getBoolean(key, false);
    button.setSelected(savedState);
  }
}
