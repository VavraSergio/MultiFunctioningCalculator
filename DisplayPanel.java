package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import numbers.CalculatorFunctions;
import numbers.CurrentMixedNumber;
import numbers.Expression;
import numbers.FractionForm;
import numbers.MixedFraction;
import numbers.Operation;

/**
 * Display panel for showing fractions. Handles output??
 * 
 * @author Alec Vietry
 * @version 11-3-23
 */
public class DisplayPanel extends JPanel implements ActionListener, Serializable
{
  private static final long serialVersionUID = 1L;
  private CurrentFractionPanel currentFractionPanel;
  private FractionPanel leftOperand, rightOperand, expressionAnswer;
  private JLabel operator, equals;
  private CurrentMixedNumber currentMixedNumber;
  private Operation operate;
  private FractionForm form;
  private boolean isReduced, isProper;
  private ArrayList<Expression> expressions;
  private Expression expression;
  private HistoryFrame historyFrame;
  private IntermediateStepsFrame stepsFrame;
  private RecordingWindow recordingWindow;
  private InputMap inputMap;
  private ActionMap actionMap;
  Map<Object, KeyEvent> bindings;

  /**
   * Default constructor. Creates the display panel for the fraciton calculator. Uses four fraction
   * panels to display the current mixed fraction, and both fractions on the final expression, and
   * the fraction answer. Takes no args.
   */
  public DisplayPanel(final HistoryFrame historyFrame, final RecordingWindow window,
      final IntermediateStepsFrame stepsFrame)
  {
    super();

    this.historyFrame = historyFrame;
    this.recordingWindow = window;
    this.stepsFrame = stepsFrame;

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
    actionMap = this.getActionMap();

    form = FractionForm.REDUCED_PROPER; // Certainly causes a bug

    expressions = new ArrayList<Expression>();

    operator = new JLabel();
    operator.setFont(new Font("Courier", Font.PLAIN, 25));

    isReduced = true;
    isProper = true;

    currentMixedNumber = new CurrentMixedNumber();

    // Creates fraction panels with all null fields: all will be empty.
    currentFractionPanel = new CurrentFractionPanel(currentMixedNumber);
    leftOperand = new FractionPanel();
    rightOperand = new FractionPanel();
    expressionAnswer = new FractionPanel();

    // Creates equals sign
    equals = new JLabel();
    equals.setText("=");
    equals.setFont(new Font("Arial", Font.BOLD, 22));
    equals.setVisible(false);

    setLayout(gbl);

    // For left expression
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.weightx = 0.07;
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(leftOperand, gbc);
    add(leftOperand);

    // For operator
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.weightx = 0.07;
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(operator, gbc);
    add(operator);

    // For right expression
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.weightx = 0.07;
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(rightOperand, gbc);
    add(rightOperand);

    // For the equals sign
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 0;
    gbc.weightx = 0.07;
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(equals, gbc);
    add(equals);

    // For expression answer
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.weightx = 0.07;
    gbc.anchor = GridBagConstraints.WEST;
    gbl.setConstraints(expressionAnswer, gbc);
    add(expressionAnswer);

    // For current expression
    gbc = new GridBagConstraints();
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.gridx = 5;
    gbc.gridy = 1;
    gbc.weighty = 1;
    gbc.weightx = 1;
    gbc.anchor = GridBagConstraints.SOUTHEAST;
    gbl.setConstraints(currentFractionPanel, gbc);
    add(currentFractionPanel);

    setupKeyBindings();

    setBackground(CalculatorWindow.DISPLAY_BLUE);
    setVisible(true);
  }

  /**
   * Action listener for the NumberPad inputs.
   * 
   * @param e
   *          the action
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String action = e.getActionCommand();
    switch (action)
    {
      case "R":
        currentMixedNumber = new CurrentMixedNumber();
        resetFractions();
        equals.setVisible(false);
        break;
      case "C":
        currentMixedNumber = new CurrentMixedNumber();
        currentFractionPanel.setFraction(currentMixedNumber);
        break;
      case "\b":
      case "\u2190": // Backspace button
        currentMixedNumber.focusBackward();
        break;
      case ".":
      case "P":
        currentMixedNumber.focusForward();
        break;
      case "0":
        numberListened(0);
        break;
      case "1":
        numberListened(1);
        break;
      case "2":
        numberListened(2);
        break;
      case "3":
        numberListened(3);
        break;
      case "4":
        numberListened(4);
        break;
      case "5":
        numberListened(5);
        break;
      case "6":
        numberListened(6);
        break;
      case "7":
        numberListened(7);
        break;
      case "8":
        numberListened(8);
        break;
      case "9":
        numberListened(9);
        break;
      case "\u00B1": // +- sign
        currentMixedNumber.setNegative();
        break;
      case "+":
        operate = Operation.ADD;
        setOperator(action);
        break;
      case "-":
        operate = Operation.SUBTRACT;
        setOperator(action);
        break;
      case "x":
      case "X":
        operate = Operation.MULTIPLY;
        setOperator(action);
        break;
      case "/":
      case "\u00F7": // Division
        operate = Operation.DIVIDE;
        setOperator(action);
        break;
      case "med": // Mediant
        operate = Operation.MEDIANT;
        setOperator(action);
        break;
      case "Inv": // Invert
        if (currentMixedNumber.isNull())
          break;
        try
        {
          currentMixedNumber.setFraction(CalculatorFunctions.invert(currentMixedNumber, form));
        }
        catch (IOException e1)
        {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        stepsFrame.setTextArea();
        break;
      case "\u2193": // Simplify
        try
        {
          currentMixedNumber.setFraction(CalculatorFunctions.simplify(currentMixedNumber, form));
        }
        catch (IOException e1)
        {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
        stepsFrame.setTextArea();
        break;
      case "X\u207F": // Exponent
        currentMixedNumber.setFocus(3);
        break;
      case ">": // Greater than
        operate = Operation.GREATER;
        setOperator(action);
        break;
      case "<": // Lesser than
        operate = Operation.LESSER;
        setOperator(action);
        break;
      case "==": // Cool equal to
        operate = Operation.EQUALS;
        setOperator(action);
        break;
      case "\n":
      case "=":
        // I am sorry about this abysmal piece of code
        if (currentMixedNumber.getExponent() != 1)
        {
          doExponentMath();
        }
        else if (isBoolean(operate))
        {
          createRightOperand();
          doBooleanMath();
        }
        else if (!(leftOperand.getFraction().isNull() || operate == null
            || currentMixedNumber.isNull()))
        {
          createRightOperand();
          doMath();
        }
        else if (!(leftOperand.getFraction().isNull() && rightOperand.getFraction().isNull()))
        {
          doMath();
        }

        break;
      default:
        break;
    }
    updateFractions();
  }

  /**
   * Helper method to ease number usage.
   * 
   * @param num
   *          to set cmn
   */
  private void numberListened(final int num)
  {
    currentMixedNumber.setNum(num);
  }

  /**
   * Will set the operator and handle the running calculations.
   * 
   * @param op
   *          the operator to set on screen.
   */
  private void setOperator(final String op)
  {
    // Guard clause
    if (currentMixedNumber.isNull() && leftOperand.getFraction().isNull()
        && rightOperand.getFraction().getExponent() == 1)
      return;

    operator.setText(op);
    // If left operator should be created
    if (leftOperand.getFraction().isNull())
    {
      createLeftOperand();
      return;
    }

    // Handles running calculations.
    if (!expressionAnswer.getFraction().isNull() && currentMixedNumber.isNull()
        || rightOperand.getFraction().getExponent() != 1)
    {
      leftOperand.setFraction(expressionAnswer.getFraction());
      currentMixedNumber = new CurrentMixedNumber();
      currentFractionPanel.setFraction(currentMixedNumber);
      rightOperand.setFraction(null);
      expressionAnswer.setFraction(null);
      equals.setVisible(false);
    }
    else if (!expressionAnswer.getFraction().isNull())
    {
      createLeftOperand();
      rightOperand.setFraction(null);
      expressionAnswer.setFraction(null);
      equals.setVisible(false);
    }
  }

  /**
   * Takes the currentMixedNumber and adds it to the leftOperand.
   */
  public void createLeftOperand()
  {
    leftOperand.setFraction(currentMixedNumber);
    currentMixedNumber = new CurrentMixedNumber();
    currentFractionPanel.setFraction(currentMixedNumber);
  }

  /**
   * Takes the currentMixedNumber and adds it to the rightOperand.
   */
  public void createRightOperand()
  {
    rightOperand.setFraction(currentMixedNumber);
    currentMixedNumber = new CurrentMixedNumber();
    currentFractionPanel.setFraction(currentMixedNumber);
  }

  /**
   * Crunches the numbers for the calculations.
   */
  public void doMath()
  {
    equals.setVisible(true);

    try
    {
      expressionAnswer.setFraction(CalculatorFunctions.calculate(leftOperand.getFraction(),
          rightOperand.getFraction(), operate, form));
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    expression = new Expression(leftOperand.getFraction(), rightOperand.getFraction(),
        expressionAnswer.getFraction(), operate);

    expressions.add(expression);
    historyFrame.addExpression(expression);
    if (this.recordingWindow != null)
    {
      recordingWindow.startRecording(expression);
    }
    expressionAnswer.setNumeratorEmpty(false);
    stepsFrame.setTextArea();
  }

  /**
   * Resets the entire DisplayPanel.
   */
  public void resetFractions()
  {
    currentMixedNumber = new CurrentMixedNumber();
    currentFractionPanel.setFraction(currentMixedNumber);
    leftOperand.setFraction(null);
    rightOperand.setFraction(null);
    expressionAnswer.setFraction(null);
    expressionAnswer.setNumeratorEmpty(true);
    operator.setText("");
  }

  /**
   * Updates all the fractions after each input.
   */
  public void updateFractions()
  {
    currentFractionPanel.update();
    leftOperand.update();
    rightOperand.update();
    expressionAnswer.update();
    updateUI();
  }

  /**
   * Sets all the fractions into the specified form. Fractions are automatically updated.
   * 
   * @param style
   *          to set
   */
  public void setFractionsStyle(final FractionStyle style)
  {
    currentFractionPanel.setFractionStyle(style);
    leftOperand.setFractionStyle(style);
    rightOperand.setFractionStyle(style);
    expressionAnswer.setFractionStyle(style);
    historyFrame.setFractionStyle(style);
    updateFractions();
  }

  /**
   * If this works im the GOAT.
   */
  public void doExponentMath()
  {
    equals.setVisible(true);
    rightOperand.setFraction(currentMixedNumber);
    try
    {
      expressionAnswer.setFraction(CalculatorFunctions.power(currentMixedNumber, form));
    }
    catch (IOException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    expression = new Expression(leftOperand.getFraction(), rightOperand.getFraction(),
        expressionAnswer.getFraction(), operate);
    expressions.add(expression);
    expression.setOperator(null);
    expression.setLeftOp(null);
    operator.setText(null);
    operate = null;
    leftOperand.setFraction(null);
    historyFrame.addExpression(expression);
    stepsFrame.setTextArea();
  }

  /**
   * Does math on the boolean expression. Creates a dialog box.
   */
  public void doBooleanMath()
  {
    String op = operator.getText();
    Boolean ans;
    switch (op)
    {
      case ">":
        ans = CalculatorFunctions.greaterThan(leftOperand.getFraction(),
            rightOperand.getFraction());
        break;
      case "<":
        ans = CalculatorFunctions.lessThan(leftOperand.getFraction(), rightOperand.getFraction());
        break;
      case "==":
        ans = CalculatorFunctions.equalTo(leftOperand.getFraction(), rightOperand.getFraction());
        break;
      default:
        ans = null;
    }

    new BooleanDialog(historyFrame, ans);
    expression = new Expression(leftOperand.getFraction(), rightOperand.getFraction(), operate);
    historyFrame.addExpression(expression);
  }

  /**
   * Creates a MixedFraction from a string.
   * 
   * @param frac
   *          the string fraction
   * @return a MixedFraction
   */
  public static MixedFraction parseFraction(final String frac)
  {
    MixedFraction mf = new MixedFraction();
    int indexOfOpen = frac.indexOf("(");
    int indexOfClose = frac.indexOf(")");
    int indexOfExp = frac.indexOf("^");
    String[] fraction = frac.substring(indexOfOpen + 1, indexOfClose).split("/");

    mf.setCoefficient(Integer.parseInt(frac.substring(0, indexOfOpen)));
    mf.setNumerator(Integer.parseInt(fraction[0]));
    mf.setDenominator(Integer.parseInt(fraction[1]));
    mf.setExponent(Integer.parseInt((String.valueOf(frac.charAt(indexOfExp + 1))))); // Lol
    return mf;
  }

  /**
   * Adds a fraction to the outside. Will put the fraction in a spot based on how many full
   * fractions there are.
   * 
   * If the left most fraction is empty, the fraction will go there. If the left fraction is full,
   * the right fraction will be set. If both operands and the answer are already set, it will reset
   * the entire expression and place the fraction into the left operand.
   * 
   * @param frac
   *          to place.
   */
  public void placeFraction(final String frac)
  {
    // Have to make some gaurd clauses to handle operators and equals.
    if (stringToOperation(frac) != null)
    {
      setOperator(frac);
      return;
    }
    if (frac.equals("="))
    {
      equals.setVisible(true);
      return;
    }

    MixedFraction mf = parseFraction(frac);
    placeFraction(mf);
  }

  public void placeFraction(final MixedFraction mf)
  {
    if (leftOperand.getFraction().isNull())
      leftOperand.setFraction(mf);
    else if (rightOperand.getFraction().isNull())
      rightOperand.setFraction(mf); // This does not work for anything besides the basic expression.
    else if (expressionAnswer.getFraction().isNull())
      expressionAnswer.setFraction(mf);
    else
    {
      resetFractions();
      equals.setVisible(false);
      leftOperand.setFraction(mf);
    }
    // currentMixedNumber = new CurrentMixedNumber();
    updateFractions();
  }

  /**
   * Basic getter for the expression. New expressions are only made when the equals sign is
   * selected.
   * 
   * @return
   */
  public Expression getExpression()
  {
    return expression;
  }

  /**
   * Basic getter for expression.
   * 
   * @return an aliased ArrayList.
   */
  public ArrayList<Expression> getExpressions()
  {
    return expressions;
  }

  public void setRecordingWindow(RecordingWindow window)
  {
    this.recordingWindow = window;
  }

  /**
   * Allows the expression to be set and updates the panel accordingly.
   * 
   * @param exp
   */
  public void setExpression(final Expression exp)
  {
    expression = exp;
    leftOperand.setFraction(exp.getLeftOp());
    rightOperand.setFraction(exp.getRightOp());
    expressionAnswer.setFraction(exp.getAnswer());
    operator.setText(operationToString(exp.getOperator()));
    equals.setVisible(!isBoolean(exp.getOperator()));
    updateFractions();
  }

  /**
   * Allows the reduced property to be toggled.
   */
  public void toggleReduced(final boolean bool)
  {
    isReduced = bool;
    setFractionForm();
  }

  /**
   * Allows the proper property to be toggled.
   */
  public void toggleProper(final boolean bool)
  {
    isProper = bool;
    setFractionForm();
  }

  /**
   * Updates the fraction form based on.
   */
  public void setFractionForm()
  {
    if (isProper && isReduced)
      form = FractionForm.REDUCED_PROPER;
    else if (isProper && !isReduced)
      form = FractionForm.IRREDUCED_PROPER;
    else if (!isProper && isReduced)
      form = FractionForm.REDUCED_IMPROPER;
    else if (!isProper && !isReduced)
      form = FractionForm.IRREDUCED_IMPROPER;
  }

  /**
   * Gross helper method because I am scared of enums. Public and static because I am also scared of
   * extending.
   * 
   * @param op
   *          the operator
   * @return a string of the operator.
   */
  public static String operationToString(final Operation op)
  {
    if (op == null)
      return null;
    switch (op)
    {
      case ADD:
        return "+";
      case SUBTRACT:
        return "-";
      case MULTIPLY:
        return "x";
      case DIVIDE:
        return "\u00F7";
      case MEDIANT:
        return "med";
      case GREATER:
        return ">";
      case LESSER:
        return "<";
      case EQUALS:
        return "\u225D";
      default:
        return null;
    }
  }

  /**
   * Allows for conversion from string to operation.
   * 
   * @param op
   *          the string to convert
   * @return the operation of that string
   */
  public static Operation stringToOperation(final String op)
  {
    if (op == null)
      return null;
    switch (op)
    {
      case "+":
        return Operation.ADD;
      case "-":
        return Operation.SUBTRACT;
      case "x":
        return Operation.MULTIPLY;
      case "/":
        return Operation.DIVIDE;
      case ">":
        return Operation.GREATER;
      case "<":
        return Operation.LESSER;
      case "\u225D":
        return Operation.EQUALS;
      default:
        return null;
    }
  }

  /**
   * Helper method for determining if an operation is a boolean op.
   * 
   * @param op
   *          to check
   * @return true if yes, false if no.
   */
  public boolean isBoolean(final Operation op)
  {
    return (op == Operation.GREATER || op == Operation.LESSER || op == Operation.EQUALS);
  }

  /**
   * Allows for the thousands separator to be turned off and set.
   * 
   * @param foo
   *          if thousands separators should be shown
   */
  public void setThousandsSep(final boolean foo)
  {
    leftOperand.setThousandsSeparator(foo);
    rightOperand.setThousandsSeparator(foo);
    expressionAnswer.setThousandsSeparator(foo);
    currentFractionPanel.setThousandsSeparator(foo);
  }

  /**
   * Stops the screen from resizing all the time while entering numbers.
   */
  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(275, 70);
  }

  /**
   * Creates key bindings for input.
   * 
   */
  private void setupKeyBindings()
  {

    // adds all necessary key bindings
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), "0");
    actionMap.put("0", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "1");
    actionMap.put("1", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "0");
    actionMap.put("2", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "0");
    actionMap.put("3", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "0");
    actionMap.put("4", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "0");
    actionMap.put("5", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "0");
    actionMap.put("6", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "0");
    actionMap.put("7", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "0");
    actionMap.put("8", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0), "0");
    actionMap.put("9", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "\n");
    actionMap.put("\n", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0), "=");
    actionMap.put("=", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0), "/");
    actionMap.put("/", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.SHIFT_DOWN_MASK), "+");
    actionMap.put("+", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), "-");
    actionMap.put("-", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "x");
    actionMap.put("x", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "\b");
    actionMap.put("\b", new KeyboardInput(this));
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0), ".");
    actionMap.put(".", new KeyboardInput(this));
  }

  public void setKeyBinding(KeyEvent ke, ActionEvent action, CalculatorWindow calc)
  {

    inputMap.put(KeyStroke.getKeyStroke(ke.getKeyCode(), 0), ke.getKeyChar());
    actionMap.put(ke.getKeyChar(), new KeyboardInput(this, calc, action));
  }

  public void setKeyBindings(InputMap input, ActionMap action)
  {
    this.inputMap = input;
    this.actionMap = action;
  }

  public void clearKeyBinding(Object item, KeyEvent key)
  {
    inputMap.remove(KeyStroke.getKeyStroke(key.getKeyCode(), 0));
    actionMap.remove(key);

  }

  public void clearKeyBindings()
  {
    inputMap.clear();
    actionMap.clear();
    setupKeyBindings();
  }

  public void saveKeyBinding()
  {
    try
    {
      FileOutputStream out;
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
      fileChooser.setFileFilter(filter);
      int result = fileChooser.showSaveDialog(null);

      if (result == JFileChooser.APPROVE_OPTION)
      {
        this.bindings = PreferenceWindow.bindings;
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".xml"))
        {
          out = new FileOutputStream(new java.io.File(filePath + ".xml"));
        }
        else
        {
          out = new FileOutputStream(fileChooser.getSelectedFile());
        }
        Properties properties = new Properties();
        Iterator<Object> it = bindings.keySet().iterator();
        while (it.hasNext())
        {
          Object keyPressed = it.next();
          String str = keyPressed.toString().trim().replaceAll(" ", "_").toUpperCase();
          properties.setProperty(CalculatorWindow.STRINGS.getString(str),
              String.valueOf(bindings.get(keyPressed).getKeyChar()));
        }
        properties.store(out, "key bindings");
        out.close();
      }
    }
    catch (IOException i)
    {
      i.printStackTrace();
    }
  }

  public void openKeyBinding(CalculatorWindow calc)
  {
    try
    {
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
      fileChooser.setFileFilter(filter);
      int result = fileChooser.showOpenDialog(null);

      if (result == JFileChooser.APPROVE_OPTION)
      {
        FileInputStream streamIn = new FileInputStream(fileChooser.getSelectedFile());
        Properties properties = new Properties();
        properties.load(streamIn);
        inputMap.clear();
        actionMap.clear();
        setupKeyBindings();
        for (String key : properties.stringPropertyNames())
        {
          KeyEvent ke = new KeyEvent(calc, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,
              KeyEvent.getExtendedKeyCodeForChar(properties.getProperty(key).charAt(0)),
              properties.getProperty(key).charAt(0));
          ActionEvent action = new ActionEvent(key, ke.getID(), key);
          inputMap.put(KeyStroke.getKeyStroke(properties.getProperty(key)),
              properties.getProperty(key));
          actionMap.put(properties.getProperty(key), new KeyboardInput(this, calc, action));

          setKeyBinding(ke, action, calc);
        }
      }
    }
    catch (IOException i)
    {
      i.printStackTrace();
    }

  }
}
