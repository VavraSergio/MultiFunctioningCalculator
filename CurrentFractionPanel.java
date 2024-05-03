package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import numbers.CurrentMixedNumber;
import numbers.MixedFraction;

/**
 * Adds a focus indicator to the fractionpanel.
 * 
 * @author Alec Vietru
 * @version 11-9-23
 */
public class CurrentFractionPanel extends FractionPanel
{
  private static final long serialVersionUID = 1L;
  private static final Border BORDER = BorderFactory.createLineBorder(Color.black);
  private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder();

  /**
   * Creates a CurrentFractionPanel with an empy CurrentMixedNumber.
   */
  public CurrentFractionPanel()
  {
    this(new CurrentMixedNumber());
  }

  /**
   * Creates a FractionPanel that stores a CurrentMixedNumber.
   * 
   * @param cmn
   *          the CMN
   */
  public CurrentFractionPanel(final CurrentMixedNumber cmn)
  {
    super(cmn);
  }

  /**
   * Sets the focus for object.
   */
  public void chooseFocus()
  {
    int focus = fraction != null ? ((CurrentMixedNumber) fraction).getFocus() : 10;

    switch (focus)
    {
      case 0:
        numerator.setBorder(EMPTY_BORDER);
        denominator.setBorder(EMPTY_BORDER);
        coefficient.setBorder(BORDER);
        exponent.setBorder(EMPTY_BORDER);
        if (fraction.getNumerator() == 0)
          positionVisibility(false);
        if (fraction.getCoefficient() == 0)
          emptyCoefficient(true);
        break;
      case 1:
        numerator.setBorder(BORDER);
        denominator.setBorder(EMPTY_BORDER);
        coefficient.setBorder(EMPTY_BORDER);
        exponent.setBorder(EMPTY_BORDER);
        positionVisibility(true);
        if (fraction.getCoefficient() == 0)
          coefficient.setVisible(false);
        break;
      case 2:
        numerator.setBorder(EMPTY_BORDER);
        denominator.setBorder(BORDER);
        coefficient.setBorder(EMPTY_BORDER);
        exponent.setBorder(EMPTY_BORDER);
        positionVisibility(true);
        if (fraction.getCoefficient() == 0)
          coefficient.setVisible(false);
        break;
      case 3:
        numerator.setBorder(EMPTY_BORDER);
        denominator.setBorder(EMPTY_BORDER);
        coefficient.setBorder(EMPTY_BORDER);
        exponent.setBorder(BORDER);
        exponent.setVisible(true);
        break;
      default:
        break;
    }
  }

  /**
   * Will display the fractionBar and borders for the labels in order to make the fraction easier to
   * work with.
   * 
   * @param bool
   *          boolean value if visible
   */
  public void positionVisibility(final boolean bool)
  {
    numerator.setVisible(bool);
    denominator.setVisible(bool);
    fractionBar.setVisible(bool);
  }

  /**
   * Allows the focus to be on an empty coefficient.
   * 
   * @param bool
   *          boolean value if empty
   */
  public void emptyCoefficient(final boolean bool)
  {
    coefficient.setVisible(bool);
  }

  /**
   * Sets fraction as a currnetMixedNumber.
   */
  @Override
  public void setFraction(final MixedFraction fraction)
  {
    this.fraction = fraction == null ? new CurrentMixedNumber() : fraction;
  }

  /**
   * Adds the focus functionality.
   */
  @Override
  public void update()
  {
    super.update();
    chooseFocus();
  }
}
