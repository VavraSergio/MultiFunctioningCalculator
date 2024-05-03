package numbers;

import java.util.ArrayList;

import gui.FractionPanel;

/**
 * Subclass of MixedFraction contains info about the current mixed number and the focus.
 *
 * 
 * @author Alec Dean
 * @version 11-1-23
 */
public class CurrentMixedNumber extends MixedFraction
{
  private ArrayList<Integer> mixedNum;
  private int focus;

  /**
   * Creates a current mixed number with all zero values.
   */
  public CurrentMixedNumber()
  {
    this(0, 0, 0);
  }

  /**
   * Constructs a mixed number with a whole value, numerator, and denominator.
   * 
   * @param coefficient
   *          value
   * @param numerator
   *          value
   * @param denominator
   *          value
   */
  public CurrentMixedNumber(final int coefficient, final int numerator, 
      final int denominator)
  {
    super(coefficient, numerator, denominator);
    this.focus = 0;
    mixedNum = new ArrayList<>();
    mixedNum.add(coefficient);
    mixedNum.add(numerator);
    mixedNum.add(denominator);
    mixedNum.add(0); // Exponent
  }

  /**
   * Get method for focus.
   * 
   * @return focused part of mixed number.
   */
  public int getFocus()
  {
    return focus;
  }
  
  /**
   * Allows the list to be updated when new fraction is set.
   * 
   * @param frac to set
   */
  public void setFraction(final MixedFraction frac)
  {
    setNumerator(frac.getNumerator());
    setCoefficient(frac.getCoefficient());
    setDenominator(frac.getDenominator());
    mixedNum.set(0, getCoefficient());
    mixedNum.set(1, getNumerator());
    mixedNum.set(2, getDenominator());
  }
  
  /**
   * Allows the focus to be set from outside.
   * 
   * @param focus to set
   */
  public void setFocus(final int focus)
  {
    this.focus = focus;
  }

  /**
   * Adds a given digit to the current focused number.
   * 
   * @param digit
   *          to be added.
   */
  public void setNum(final Integer digit)
  {
    String num = mixedNum.get(focus) + digit.toString();
    setToFocus(Integer.parseInt(num));
  }

  /**
   * Changes the focus to the next number in the order.
   * 
   * @return Integer at new focus.
   */
  public int focusForward()
  {
    if (focus < 2)
    {
      focus++;
    }
    else
    {
      focus = 0;
    }
    return mixedNum.get(focus);
  }

  /**
   * Changes the focus to the previous number in the order.
   * 
   * @return Integer at new focus.
   */
  public int focusBackward()
  {
    this.setToFocus(0);
    if (focus > 0)
    {
      focus--;
    }
    else
    {
      focus = mixedNum.size() - 2;
    }
    return mixedNum.get(focus);
  }

  /**
   * Changes the +/- sign current coefficient.
   */
  public void setNegative()
  {
    if (getCoefficient() == 0)
      setNumerator(getNumerator() * -1);
    else
      setCoefficient(getCoefficient() * -1);
    
    // setIsNegative(!getIsNegative());
  }

  /**
   * Takes a integer value and sets the current focus to that value.
   * 
   * @param num
   *          to set focus to.
   */
  private void setToFocus(final int num)
  {
    switch (focus)
    {
      case 0:
        this.setCoefficient(num);
        mixedNum.set(0, num);
        break;
      case 1:
        this.setNumerator(num);
        mixedNum.set(1, num);
        break;
      case 2:
        this.setDenominator(num);
        mixedNum.set(2, num);
        break;
      case 3:
        this.setExponent(num);
        mixedNum.set(3, num);
    }
  }
}
