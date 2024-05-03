package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import numbers.MixedFraction;

/**
 * Stores the mixed fractions for the GUI.
 */
public class FractionPanel extends JPanel // implements ActionListener
{

  private static final long serialVersionUID = 1L;
  private FractionStyle style;
  private boolean shouldSeperate;
  private boolean keepCoefEmpty;
  protected JLabel numerator, denominator, coefficient, exponent;
  protected JComponent fractionBar;
  protected MixedFraction fraction;

  public static final String BAR = "\u2015" + "\u2015" + "\u2015";
  public static final String SLASH = "/";
  public static final String SOLIDUS = "\u002F";

  /**
   * No arg constructor for making new / empty fractions.
   * 
   * Creates a fraction panel with empty JLabels
   */
  public FractionPanel()
  {
    this(new MixedFraction());
  }

  /**
   * Creates a fraction that can be displayed on the GUI.
   * 
   * @param fraction
   *          the mixed fraction
   */
  public FractionPanel(final MixedFraction fraction)
  {
    super();

    numerator = new JLabel();
    denominator = new JLabel();
    coefficient = new JLabel();
    fractionBar = new JPanel();
    exponent = new JLabel();

    style = FractionStyle.BAR;

    shouldSeperate = false;
    keepCoefEmpty = true;

    this.fraction = fraction;

    updateLayoutStyle();

    setSize(200, 200);
  }

  /**
   * Helper method to see if fraction components should be displayed or not.
   */
  public void emptyFraction()
  {
    if (fraction.getNumerator() == 0)
    {
      numerator.setVisible(false);
      denominator.setVisible(false);
      fractionBar.setVisible(false);
    }
    else
    {
      numerator.setVisible(true);
      denominator.setVisible(true);
      fractionBar.setVisible(true);
    }
  }

  /**
   * Helper method to determine when to display coefficient.
   */
  public void emptyCoefficient()
  {
    coefficient.setVisible(!(fraction.getCoefficient() == 0));
  }

  /**
   * Helper method to determine when to display exponent.
   */
  public void emptyExponent()
  {
    exponent.setVisible(!(fraction.getExponent() == 1));
  }

  /**
   * Basic getter.
   * 
   * @return fraction
   */
  public MixedFraction getFraction()
  {
    return fraction;
  }

  /**
   * Sets the fraction. Updates the panel to display info.
   * 
   * @param fraction
   *          to set
   */
  public void setFraction(final MixedFraction fraction)
  {
    this.fraction = (fraction == null ? new MixedFraction() : fraction);
  }

  /**
   * Allows the form to be set. When it is the layout is changed to match the form.
   * 
   * @param style
   *          to set
   */
  public void setFractionStyle(final FractionStyle style)
  {
    this.style = style;
    updateLayoutStyle();
  }

  /**
   * Updates the panel to the specified form.
   */
  public void updateLayoutStyle()
  {
    setLayout(null);
    removeAll();
    switch (style)
    {
      case BAR:
        setLayoutBar();
        break;
      case SLASH:
        setLayoutSlash();
        break;
      case SOLIDUS:
        setLayoutSolidus();
        break;
    }
  }

  /**
   * Sets the layout as a fraction with a bar.
   */
  private void setLayoutBar()
  {
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    fractionBar = new LinePanel();

    coefficient.setFont(new Font("Arial", Font.PLAIN, 30));
    numerator.setFont(new Font("Arial", Font.PLAIN, 30));
    denominator.setFont(new Font("Arial", Font.PLAIN, 30));
    exponent.setFont(new Font("Arial", Font.PLAIN, 20));
    setFraction(this.fraction);

    setLayout(gbl);

    // For coefficient
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridheight = 3;
    gbc.insets = new Insets(0, 0, 0, 1);
    add(coefficient, gbc);

    // For numerator
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.anchor = GridBagConstraints.SOUTH;
    add(numerator, gbc);

    // For fractionBar
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weighty = 0;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    fractionBar.setMaximumSize(new Dimension(10, 3));
    add(fractionBar, gbc);

    // For denominator
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weighty = 1;
    gbc.anchor = GridBagConstraints.NORTH;
    add(denominator, gbc);

    // For exponent
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.weighty = 1;
    gbc.anchor = GridBagConstraints.CENTER;
    add(exponent, gbc);

    cleanup();
  }

  /**
   * Sets the layout to a bar fraction.
   */
  private void setLayoutSlash()
  {
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    fractionBar = new JLabel();
    ((JLabel) fractionBar).setText(SLASH);

    coefficient.setFont(new Font("Arial", Font.PLAIN, 30));
    numerator.setFont(new Font("Arial", Font.PLAIN, 30));
    denominator.setFont(new Font("Arial", Font.PLAIN, 30));
    fractionBar.setFont(new Font("Arial", Font.PLAIN, 30));
    exponent.setFont(new Font("Arial", Font.PLAIN, 30));

    setFraction(fraction);

    setLayout(gbl);

    // For coefficient
    gbc.gridx = 0;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.insets = new Insets(0, 0, 0, 7);
    add(coefficient, gbc);

    // For numerator
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    add(numerator, gbc);

    // For fraction slash
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    add(fractionBar, gbc);

    // For denominator
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    add(denominator, gbc);

    // For exponent
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    add(exponent, gbc);

    cleanup();
  }

  /**
   * Sets the layout to a solidus fraction.
   */
  private void setLayoutSolidus()
  {
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    fractionBar = new JLabel();
    ((JLabel) fractionBar).setText(SOLIDUS);

    coefficient.setFont(new Font("Arial", Font.PLAIN, 30));
    numerator.setFont(new Font("Arial", Font.PLAIN, 24));
    denominator.setFont(new Font("Arial", Font.PLAIN, 24));
    fractionBar.setFont(new Font("Arial", Font.PLAIN, 30));
    exponent.setFont(new Font("Arial", Font.PLAIN, 20));

    setLayout(gbl);

    // For coefficient
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridheight = 3;
    gbc.insets = new Insets(0, 0, 0, 2);
    gbc.fill = GridBagConstraints.VERTICAL;
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(coefficient, gbc);
    add(coefficient);

    // For numerator
    gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbl.setConstraints(numerator, gbc);
    add(numerator);

    // For fraction solidus
    gbc = new GridBagConstraints();
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.gridheight = 3;
    gbc.fill = GridBagConstraints.VERTICAL;
    gbl.setConstraints(fractionBar, gbc);
    add(fractionBar);

    // For denominator
    gbc = new GridBagConstraints();
    gbc.gridx = 3;
    gbc.gridy = 3;
    gbl.setConstraints(denominator, gbc);
    add(denominator);

    // For exponent
    gbc = new GridBagConstraints();
    gbc.gridx = 4;
    gbc.gridy = 1;
    gbc.insets = new Insets(0, 0, 10, 0);
    gbc.anchor = GridBagConstraints.NORTH;
    gbl.setConstraints(exponent, gbc);
    add(exponent);

    cleanup();
  }

  /**
   * Calls all the nescassry methods after setting the layout.
   */
  private void cleanup()
  {
    setBackground(CalculatorWindow.DISPLAY_BLUE);
    emptyFraction();
    emptyCoefficient();
    update();
    setVisible(true);
  }

  /**
   * Will reset the fraction to null.
   */
  public void clearFraction()
  {
    setFraction(null);
  }

  /**
   * Allows the thousands Separator to be set from outside. The default thousands Separator is ",".
   * 
   * @param foo
   *          sets shouldSeperate
   */
  public void setThousandsSeparator(final boolean foo)
  {
    this.shouldSeperate = foo;
    update();
  }

  /**
   * Does logic to see if the string should be seperated.
   * 
   * @param n
   *          the integer to seperate
   * @return a seperated or non-seperated toString of the int
   */
  private String seperate(final int n)
  {
    return shouldSeperate ? String.format("%,d", n) : String.format("%d", n);
  }

  /**
   * Basically allows for a fraciton panel to be set to zero from the outside in case it is the
   * result of an expression and the answer is 0.
   * 
   * @param bool
   *          to set
   */
  public void setNumeratorEmpty(final boolean bool)
  {
    keepCoefEmpty = bool;
  }

  /**
   * Updates the JLabels to show data in fraction.
   */
  public void update()
  {
    numerator.setText(seperate(fraction.getNumerator()));
    denominator.setText(seperate(fraction.getDenominator()));
    coefficient.setText(seperate(fraction.getCoefficient()));
    if (style == FractionStyle.SLASH)
      exponent.setText("^" + seperate(fraction.getExponent()));
    else // Adding carrot for now for improved readability
      exponent.setText("^" + seperate(fraction.getExponent()));
    emptyFraction();
    emptyCoefficient();
    emptyExponent();
    coefficient.setVisible(!(fraction.isNull() && keepCoefEmpty));
  }
}
