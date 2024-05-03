package gui;

import static gui.CalculatorWindow.STRINGS;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PreferenceWindow extends JFrame implements ActionListener
{

  private static final long serialVersionUID = 1L;
  private JMenuBar menu;
  private static DisplayPanel display;
  private static CalculatorWindow calculatorWindow;
  private JComboBox<String> fileBox;
  private JComboBox<String> preferenceBox;
  private JComboBox<String> modeBox;
  private JComboBox<String> viewBox;
  private JComboBox<String> styleBox;
  private JComboBox<String> helpBox;
  private JTextField fileText;
  private JTextField preferenceText;  
  private JTextField modeText;
  private JTextField viewText; 
  private JTextField styleText;
  private JTextField helpText;
  public  static Map<Object, KeyEvent> bindings;
  public static JList<JComboBox<String>> fields;

  public PreferenceWindow(DisplayPanel display, CalculatorWindow calculatorWindow)
  {
    super("Preferences");
    this.display = display;   
    this.calculatorWindow = calculatorWindow;
    bindings = new HashMap<Object, KeyEvent>();
    Container contentPane;

    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());

    contentPane = getContentPane();
    setLayout(new BoxLayout(contentPane, 1));
    String file[] = {
        STRINGS.getString("OPEN_RECORDING"),
        STRINGS.getString("SAVE_RECORDING"),
        STRINGS.getString("PRINT_SESSION"),
        STRINGS.getString("NEW_CALCULATOR"),
        STRINGS.getString("EXIT")};
    String preference[] = {
        STRINGS.getString("EDIT"),
        STRINGS.getString("OPEN"),
        STRINGS.getString("SAVE")};
    String mode[] = {
        STRINGS.getString("PROPER"),
        STRINGS.getString("REDUCED")};
    String view[] = {
        STRINGS.getString("PIE_CHART"),
        STRINGS.getString("THOUSANDS")};
    String style[] = {
        STRINGS.getString("BAR"),
        STRINGS.getString("SLASH"),
        STRINGS.getString("SOLIDUS")};
    String help[] = {
        STRINGS.getString("ABOUT"),
        STRINGS.getString("HELP")};

    fileBox = new JComboBox<String>(file);
    fileBox.addActionListener(this);
    fileBox.setActionCommand("file");
    preferenceBox = new JComboBox<String>(preference);
    preferenceBox.addActionListener(this);
    preferenceBox.setActionCommand("preference");
    modeBox = new JComboBox<String>(mode);
    modeBox.addActionListener(this);
    modeBox.setActionCommand("mode");
    viewBox = new JComboBox<String>(view);
    viewBox.addActionListener(this);
    viewBox.setActionCommand("view");
    styleBox = new JComboBox<String>(style);
    styleBox.addActionListener(this);
    styleBox.setActionCommand("style");
    helpBox = new JComboBox<String>(help);
    helpBox.addActionListener(this);
    helpBox.setActionCommand("help");

    fields = new JList<JComboBox<String>>();
    fields.add(fileBox);
    fields.add(preferenceBox);
    fields.add(modeBox);
    fields.add(viewBox);
    fields.add(styleBox);
    fields.add(helpBox);
    
    fileText = new JTextField(1);
    createInputBox(fileText, fileBox);
    
    preferenceText = new JTextField(1);
    createInputBox(preferenceText, preferenceBox);
    
    modeText = new JTextField(1);
    createInputBox(modeText, modeBox);
    
    viewText = new JTextField(1);
    createInputBox(viewText,viewBox);
    
    styleText = new JTextField(1);
    createInputBox(styleText, styleBox);
    
    helpText = new JTextField(1);
    createInputBox(helpText, helpBox);
    

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(7, 8, 7, 8);
    c.weightx = 1;
    c.weighty = 1;

    c.gridy = 1;
    panel.add(new JLabel("File:"), c);
    panel.add(fileBox, c);
    panel.add(fileText, c);
    c.gridy = 2;
    panel.add(new JLabel("Preferences:"), c);
    panel.add(preferenceBox, c);
    panel.add(preferenceText, c);
    c.gridy = 3;
    panel.add(new JLabel("Mode:"), c);
    panel.add(modeBox, c);
    panel.add(modeText, c);
    c.gridy = 4;
    panel.add(new JLabel("View:"), c);
    panel.add(viewBox, c);
    panel.add(viewText, c);
    c.gridy = 5;
    panel.add(new JLabel("Style:"), c);
    panel.add(styleBox, c);
    panel.add(styleText, c);
    c.gridy = 6;
    panel.add(new JLabel("Help:"), c);
    panel.add(helpBox, c);
    panel.add(helpText, c);
    
    c.gridy = 7;
    JButton clear = new JButton("Clear Bindings");
    panel.add(clear, c);
    clear.addActionListener(this);
    
    JScrollPane pane = new JScrollPane(panel);
    add(pane, BorderLayout.EAST);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setAlwaysOnTop(true);
    setSize(500, 500);
    //setVisible(true);
  }

  private void createInputBox(JTextField textField, JComboBox<String> box) {
    textField.setSize(10, 10);
    textField.addKeyListener(new KeyAdapter()
    {
      public void keyPressed(KeyEvent ke)
      {
        String text = textField.getText();
        if (text.length() > 0)
        {
          textField.setText("");
        }
        if(bindings.containsKey(box.getSelectedItem()) || bindings.containsValue(ke)) {
          display.clearKeyBinding(box.getSelectedItem(), bindings.get(box.getSelectedItem()));
          bindings.remove(box.getSelectedItem());
        }
        else {
        ActionEvent action = new ActionEvent(box.getSelectedItem(), ke.getID(), box.getSelectedItem().toString());
        display.setKeyBinding(ke, action, calculatorWindow);
        bindings.put(box.getSelectedItem(), ke);
        }
        
      }
    });
  }
  
  @Override
  public void actionPerformed(ActionEvent e)
  {
    String action = e.getActionCommand();
    if(action.equals("Clear Bindings")) {
        bindings.clear(); 
        display.clearKeyBindings();
        fileText.setText("");
        preferenceText.setText("");
        modeText.setText("");
        viewText.setText("");
        styleText.setText("");
        helpText.setText(""); 
    }
    else if(action.equals("file")) {
      if(bindings.containsKey(fileBox.getSelectedItem())) {
      fileText.setText(String.valueOf(bindings.get(fileBox.getSelectedItem()).getKeyChar()));
    }
      else {
        fileText.setText("");
      }
    }
    else if(action.equals("preference")) {
      if(bindings.containsKey(preferenceBox.getSelectedItem())) {
        preferenceText.setText(String.valueOf(bindings.get(preferenceBox.getSelectedItem()).getKeyChar()));
      }
        else {
      preferenceText.setText("");
    }
    }
    else if(action.equals("mode")) {
      if(bindings.containsKey(modeBox.getSelectedItem())) {
        modeText.setText(String.valueOf(bindings.get(modeBox.getSelectedItem()).getKeyChar()));
      }
        else {
      modeText.setText("");
    }
    }
    else if(action.equals("view")) {
      if(bindings.containsKey(viewBox.getSelectedItem())) {
        viewText.setText(String.valueOf(bindings.get(viewBox.getSelectedItem()).getKeyChar()));
      }
        else {
      viewText.setText("");
    }
    }
    else if(action.equals("style")) {
      if(bindings.containsKey(styleBox.getSelectedItem())) {
        styleText.setText(String.valueOf(bindings.get(styleBox.getSelectedItem()).getKeyChar()));
      }
        else {
      styleText.setText("");
    }
    }
    else if(action.equals("help")) {
      if(bindings.containsKey(helpBox.getSelectedItem())) {
        helpText.setText(String.valueOf(bindings.get(helpBox.getSelectedItem()).getKeyChar()));
      }
        else {
      helpText.setText("");
    }
    }
    
  }
  
  
}
