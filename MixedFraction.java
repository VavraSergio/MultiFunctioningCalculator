package numbers;

import java.io.Serializable;

/**
 * MixedFraction class.
 * 
 * @author Cole & Parker
 * @version 11-2-23
 */
public class MixedFraction implements Serializable
{
	
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private int coefficient;
  private int numerator;
  private int denominator;
  private int exponent;

  /**
   * Constructs a fully zero MixedFraction.
   */
  public MixedFraction()
  {
    this(0, 0, 0);
  }

  /**
   * MixedFraction constructor.
   * 
   * @param coefficient
   *          -- Coefficient of mixed fraction
   * @param numerator
   *          -- Numerator of mixed fraction remainder
   * @param denominator
   *          -- Denominator of mixed fraction remainder
   */
  public MixedFraction(final int coefficient, final int numerator, 
      final int denominator, final int exponent)
  {
    this.coefficient = coefficient;
    this.exponent = exponent;
    if ((numerator == 0) && (denominator == 0))
    {
      this.numerator = 0;
      this.denominator = 1;
    }
    else if (denominator == 0)
    {
      throw new IllegalArgumentException();
    }
    else
    {
      this.numerator = numerator;
      this.denominator = denominator;
    }
  }
  
  /**
   * MixedFraction constructor.
   * 
   * @param coefficient
   *          -- Coefficient of mixed fraction
   * @param numerator
   *          -- Numerator of mixed fraction remainder
   * @param denominator
   *          -- Denominator of mixed fraction remainder
   */
  public MixedFraction(final int coefficient, final int numerator, final int denominator)
  {
    this(coefficient, numerator, denominator, 1);
  }

  /**
   * Checks if the fraction has 0 coefficient, 0 numerator, and 1 denominator. This is its default
   * form: it has not been edited.
   * 
   * @return boolean if null
   */
  public boolean isNull()
  {
    return coefficient == 0 && numerator == 0 && denominator == 1;
  }

  /**
   * Returns mixed fraction coefficient.
   * 
   * @return coefficient
   */
  public int getCoefficient()
  {
    return this.coefficient;
  }

  /**
   * Sets mixed fraction coefficient.
   * 
   * @param coefficient
   *          -- coefficient to be set to
   * 
   */
  public void setCoefficient(final int coefficient)
  {
    this.coefficient = coefficient;
  }

  /**
   * Returns mixed fraction numerator.
   * 
   * @return numerator
   */
  public int getNumerator()
  {
    return this.numerator;
  }

  /**
   * Sets mixed fraction numerator.
   * 
   * @param numerator
   *          -- numerator to be set to
   * 
   */
  public void setNumerator(final int numerator)
  {
    this.numerator = numerator;
  }

  /**
   * Returns mixed fraction denominator.
   * 
   * @return denominator
   */
  public int getDenominator()
  {
    return this.denominator;
  }

  /**
   * Sets mixed fraction denominator.
   * 
   * @param denominator
   *          -- denominator to be set to
   * 
   */
  public void setDenominator(final int denominator)
  {
    this.denominator = denominator;
  }
  
  /**
   * Returns exponent from fraction.
   * 
   * @return int, exponent of fraction
   */
  public int getExponent() 
  {
    return this.exponent;
  }
  
  /**
   * Sets exponent for mixed fraction.
   * 
   * @param exponent
   */
  public void setExponent(final int exponent) 
  {
    this.exponent = exponent;
  }
  
  /**
   * Sets coefficient negative.
   */
  public void setNegative() 
  {
    setCoefficient(this.coefficient * -1);
  }
  
  public String toString() {
    String formattedString;
    if (this.getNumerator() == 0) {
      formattedString = Integer.toString(this.getCoefficient());
    } else if (this.getCoefficient() == 0) {
      formattedString = this.getNumerator() + "/" + this.getDenominator();
    } else {
      formattedString = this.getCoefficient() + " " + this.getNumerator() + "/" + this.getDenominator();
    }
    return formattedString;
  }
}
