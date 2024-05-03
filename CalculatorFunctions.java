package numbers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class to perform mathematical operations on MixedFractions.
 */
public class CalculatorFunctions
{
  public static final String SUBTRACTION = " - ";
  public static final String ADDITION = " + ";
  public static final String MULTI = " x ";
  public static final String EQUAL = " = ";
  public static final String SPACE = " ";
  public static final String TWO = "2. ";
  public static final String THREE = "3. ";
  public static final String ONE = ":\n\n1. ";
  public static final String FOUR = "\n\n4. ";
  public static final String TAB = "\n    ";
  public static final String FIVE = "\n\n5. ";
  public static final String TABTWO = "\n\n";
  public static final String ADD_RESULT = "ADD_RESULT";
  public static final String COLON = ": ";
  public static final String SIX = "\n\n6. ";
  public static final String TABTHREE = "\n";
  public static final String ADD_FINAL = "ADD_FINAL";
  public static final String DIVIDE = " / ";
  public static final String MED_THREE = "MED_THREE";
  public static final String TIMES = " times\n    ";
  public static final String DIV = "/";
  public static final String AND = " and ";
  public static final String DOT = ". ";
  public static final String COOL = "GRE_THREE";
  public static final String GRE = "GRE_SIX";
  public static final String SPACETWO = "  ";
  public static final String LAME = ".\n";
  public static final String MULTI_TWO = " * ";
  public static final String TWO_TWO = "    2. ";
  public static final String ONE_TWO = "\n    1. ";
  public static final String TAB_FIVE = "\n        ";
  public static final String TAB_SIX = "\n    2. ";
  public static final String SIMPLIFY = "SIMPLIFY";

  static String country = System.getProperty("user.country");
  static String language = System.getProperty("user.language");
  static final ResourceBundle STRINGS;

  public static final String RESULT_FILE = "calculator_results.txt";

  // Use the country and language values to set the application's locale
  static Locale LOCALE = new Locale(country, language);
  // Set your application's locale using 'locale'

  static
  {
    STRINGS = ResourceBundle.getBundle("gui.Strings", LOCALE);
  }

  /**
   * Dummy Constructor for full coverage.
   */
  public CalculatorFunctions()
  {
  }

  /**
   * Performs the specified mathematical operation on two MixedFractions.
   *
   * @param fractionOne
   *          The first MixedFraction.
   * @param fractionTwo
   *          The second MixedFraction.
   * @param operation
   *          The operation to perform (ADD, SUBTRACT, MULTIPLY, DIVIDE, MEDIANT).
   * @param form
   *          The desired form of the result (PROPER, REDUCED, IMPROPER).
   * @return The result of the operation as a MixedFraction.
   * @throws IOException 
   * @throws IllegalArgumentException 
   * @throws UnsupportedOperationException
   *           If an invalid operation is specified.
   */
  public static MixedFraction calculate(final MixedFraction fractionOne,
      final MixedFraction fractionTwo, final Operation operation, final FractionForm form) throws IllegalArgumentException, IOException
  {
    MixedFraction answer;

    if (operation == Operation.ADD)
    {
      answer = add(fractionOne, fractionTwo, form);
    }
    else if (operation == Operation.SUBTRACT)
    {
      answer = subtract(fractionOne, fractionTwo, form);
    }
    else if (operation == Operation.MULTIPLY)
    {
      answer = multiply(fractionOne, fractionTwo, form);
    }
    else if (operation == Operation.DIVIDE)
    {
      answer = divide(fractionOne, fractionTwo, form);
    }
    else if (operation == Operation.MEDIANT)
    {
      answer = mediant(fractionOne, fractionTwo, form);
    }
    else
    {
      throw new UnsupportedOperationException();
    }

    return answer;
  }

  /**
   * Adds and simplifies the mixed fractions passed to the class.
   *
   * @param fractionOne
   *          The first MixedFraction to add.
   * @param fractionTwo
   *          The second MixedFraction to add.
   * @param form
   *          The desired form of the result (PROPER, REDUCED, IMPROPER).
   * @return The result of addition as a MixedFraction.
   * @throws IOException 
   */
  public static MixedFraction add(final MixedFraction fractionOne, final MixedFraction fractionTwo,
      final FractionForm form) throws IOException
  {
    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("ADDITION") + SPACE + fractionOne.toString() + SPACE
        + ADDITION + SPACE + fractionTwo.toString() + ONE);

    int revisedNumerator1 = revisedNumerator(fractionOne, calculationSteps);

    calculationSteps.append(TWO);
    int revisedNumerator2 = revisedNumerator(fractionTwo, calculationSteps);

    calculationSteps.append(THREE + STRINGS.getString("COMMON") + TAB);
    int commonDenominator = fractionOne.getDenominator() * fractionTwo.getDenominator();
    calculationSteps.append(fractionOne.getDenominator() + SPACE + MULTI + SPACE
        + fractionTwo.getDenominator() + SPACE + EQUAL + SPACE + commonDenominator);

    calculationSteps.append(FOUR + STRINGS.getString("ADD_MULTIPLY") + TAB);

    int newNumerator1 = (revisedNumerator1 * fractionTwo.getDenominator());
    calculationSteps.append(revisedNumerator1 + SPACE + MULTI + SPACE + fractionTwo.getDenominator()
        + SPACE + EQUAL + SPACE + newNumerator1);
    int newNumerator2 = (revisedNumerator2 * fractionOne.getDenominator());
    calculationSteps.append(TAB + revisedNumerator2 + SPACE + MULTI + SPACE
        + fractionOne.getDenominator() + SPACE + EQUAL + SPACE + newNumerator2);

    calculationSteps.append(FIVE + STRINGS.getString("ADD_REVISED") + TAB);
    int newNumerator = newNumerator1 + newNumerator2;
    MixedFraction uglyAnswer = new MixedFraction(0, newNumerator, commonDenominator);
    calculationSteps.append((newNumerator1) + ADDITION + (newNumerator2) + EQUAL + newNumerator);

    calculationSteps
        .append(TABTWO + STRINGS.getString(ADD_RESULT) + COLON + uglyAnswer.toString() + SIX);

    MixedFraction answer = formFraction(uglyAnswer, form, calculationSteps);

    calculationSteps
        .append(TABTHREE + STRINGS.getString(ADD_FINAL) + COLON + answer.toString() + TABTWO);

    writeCalculationStepsToFile(calculationSteps.toString(), answer);

    return answer;
  }

  /**
   * Subtraction and simplifies the mixed fractions passed to the class.
   *
   * @param fractionOne
   *          The MixedFraction to subtract from.
   * @param fractionTwo
   *          The MixedFraction to subtract.
   * @param form
   *          The desired form of the result (PROPER, REDUCED, IMPROPER).
   * @return The result of subtraction as a MixedFraction.
   * @throws IOException 
   */
  public static MixedFraction subtract(final MixedFraction fractionOne,
      final MixedFraction fractionTwo, final FractionForm form) throws IOException
  {
    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("SUB") + SPACE + fractionOne.toString() + SUBTRACTION
        + fractionTwo.toString() + ONE);

    int revisedNumerator1 = revisedNumerator(fractionOne, calculationSteps);
    calculationSteps.append(TWO);
    int revisedNumerator2 = revisedNumerator(fractionTwo, calculationSteps);

    calculationSteps.append(THREE + STRINGS.getString("SUB_DENOM") + TAB);
    int commonDenominator = fractionOne.getDenominator() * fractionTwo.getDenominator();
    calculationSteps.append(fractionOne.getDenominator() + MULTI + fractionTwo.getDenominator()
        + EQUAL + commonDenominator);

    calculationSteps.append(FOUR + STRINGS.getString("SUB_COMMON") + TAB);

    calculationSteps.append(fractionOne.getNumerator() + MULTI + commonDenominator + EQUAL
        + (fractionOne.getNumerator() * commonDenominator));
    calculationSteps.append(TAB + fractionTwo.getNumerator() + MULTI + commonDenominator + EQUAL
        + (fractionTwo.getNumerator() * commonDenominator));

    calculationSteps.append(FIVE + STRINGS.getString("SUB_NUM") + TAB);
    int newNumerator = (revisedNumerator1 * fractionTwo.getDenominator())
        - (revisedNumerator2 * fractionOne.getDenominator());
    MixedFraction uglyAnswer = new MixedFraction(0, newNumerator, commonDenominator);
    calculationSteps.append((fractionOne.getNumerator() * commonDenominator) + SUBTRACTION
        + (fractionTwo.getNumerator() * commonDenominator) + EQUAL + newNumerator);

    calculationSteps
        .append(TABTWO + STRINGS.getString(ADD_RESULT) + COLON + uglyAnswer.toString() + SIX);

    MixedFraction answer = formFraction(uglyAnswer, form, calculationSteps);

    calculationSteps
        .append(TABTHREE + STRINGS.getString(ADD_FINAL) + COLON + answer.toString() + TABTWO);

    writeCalculationStepsToFile(calculationSteps.toString(), answer);

    return answer;
  }

  /**
   * Multiplies and simplifies the mixed fractions passed to the class.
   *
   * @param fractionOne
   *          The first MixedFraction to multiply.
   * @param fractionTwo
   *          The second MixedFraction to multiply.
   * @param form
   *          The desired form of the result (PROPER, REDUCED, IMPROPER).
   * @return The result of multiplication as a MixedFraction.
   * @throws IOException 
   */
  public static MixedFraction multiply(final MixedFraction fractionOne,
      final MixedFraction fractionTwo, final FractionForm form) throws IOException
  {
    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("MULTI") + SPACE + fractionOne.toString() + MULTI
        + fractionTwo.toString() + ONE);

    int revisedNumerator1 = revisedNumerator(fractionOne, calculationSteps);
    calculationSteps.append(TWO);
    int revisedNumerator2 = revisedNumerator(fractionTwo, calculationSteps);

    calculationSteps.append(THREE + STRINGS.getString("MULTI_TWO") + TAB);
    int newNumerator = (revisedNumerator1 * revisedNumerator2);
    calculationSteps.append(revisedNumerator1 + MULTI + revisedNumerator2 + EQUAL + newNumerator);

    calculationSteps.append(FOUR + STRINGS.getString("MULTI_THREE") + TAB);
    int commonDenominator = fractionOne.getDenominator() * fractionTwo.getDenominator();
    calculationSteps.append(fractionOne.getDenominator() + MULTI + fractionTwo.getDenominator()
        + EQUAL + commonDenominator);

    // Append the multiplication steps to the calculationSteps StringBuilder
    MixedFraction uglyAnswer = new MixedFraction(0, newNumerator, commonDenominator);
    calculationSteps
        .append(TABTWO + STRINGS.getString(ADD_RESULT) + COLON + uglyAnswer.toString() + FIVE);

    MixedFraction answer = formFraction(uglyAnswer, form, calculationSteps);

    // Append the final result to the calculationSteps StringBuilder
    calculationSteps.append(STRINGS.getString(ADD_FINAL) + COLON + answer.toString() + TABTWO);

    // Append the calculationSteps to the file
    writeCalculationStepsToFile(calculationSteps.toString(), answer);

    return answer;
  }

  /**
   * Divides and simplifies the mixed fractions passed to the class.
   *
   * @param fractionOne
   *          The MixedFraction to divide.
   * @param fractionTwo
   *          The MixedFraction to divide by.
   * @param form
   *          The desired form of the result (PROPER, REDUCED, IMPROPER).
   * @return The result of division as a MixedFraction.
   * @throws IllegalArgumentException
   *           If division by zero is attempted.
   * @throws IOException 
   */
  public static MixedFraction divide(final MixedFraction fractionOne,
      final MixedFraction fractionTwo, final FractionForm form) throws IllegalArgumentException, IOException
  {
    if ((fractionOne.getNumerator() == 0) && (fractionOne.getCoefficient() == 0))
    {
      throw new IllegalArgumentException();
    }
    if (((fractionTwo.getNumerator() == 0)) && (fractionTwo.getCoefficient() == 0))
    {
      throw new IllegalArgumentException();
    }
    StringBuilder calculationSteps = new StringBuilder();

    calculationSteps.append(STRINGS.getString("DIV_ONE") + SPACE + fractionOne.toString() + DIVIDE
        + fractionTwo.toString() + ONE);

    int revisedNumerator1 = revisedNumerator(fractionOne, calculationSteps);

    calculationSteps.append(TWO);
    int revisedNumerator2 = revisedNumerator(fractionTwo, calculationSteps);

    calculationSteps.append(THREE + STRINGS.getString("DIV_TWO") + TAB);
    int commonDenominator = fractionOne.getDenominator() * revisedNumerator2;
    calculationSteps.append(
        fractionOne.getDenominator() + MULTI + revisedNumerator2 + EQUAL + commonDenominator);

    calculationSteps.append("4. " + STRINGS.getString("DIV_THREE") + TAB);
    int newNumerator = (revisedNumerator1 * fractionTwo.getDenominator());
    calculationSteps
        .append(revisedNumerator1 + MULTI + fractionTwo.getDenominator() + EQUAL + newNumerator);

    MixedFraction uglyAnswer = new MixedFraction(0, newNumerator, commonDenominator);
    calculationSteps
        .append(TABTWO + STRINGS.getString(ADD_RESULT) + COLON + uglyAnswer.toString() + FIVE);

    MixedFraction answer = formFraction(uglyAnswer, form, calculationSteps);

    // Append the final result to the calculationSteps StringBuilder
    calculationSteps.append(STRINGS.getString(ADD_FINAL) + COLON + answer.toString() + TABTWO);

    // Append the calculationSteps to the file
    writeCalculationStepsToFile(calculationSteps.toString(), answer);

    return answer;
  }

  /**
   * Calculates the mediant of two MixedFractions.
   *
   * @param fractionOne
   *          The first MixedFraction.
   * @param fractionTwo
   *          The second MixedFraction.
   * @param form
   *          The desired form of the result (PROPER, REDUCED, IMPROPER).
   * @return The mediant of the two MixedFractions as a MixedFraction.
   * @throws IOException 
   */
  public static MixedFraction mediant(final MixedFraction fractionOne,
      final MixedFraction fractionTwo, final FractionForm form) throws IOException
  {
    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("MED_ONE") + SPACE + fractionOne.toString() + " med "
        + fractionTwo.toString() + ONE);

    int revisedNumerator1 = revisedNumerator(fractionOne, calculationSteps);

    calculationSteps.append(TWO);
    int revisedNumerator2 = revisedNumerator(fractionTwo, calculationSteps);

    calculationSteps.append(THREE + STRINGS.getString(MED_THREE) + TAB);
    int sumNumerators = revisedNumerator1 + revisedNumerator2;
    calculationSteps
        .append(revisedNumerator1 + ADDITION + revisedNumerator2 + EQUAL + sumNumerators);

    calculationSteps.append("\n4. " + STRINGS.getString(MED_THREE) + TAB);
    int sumDenominators = fractionOne.getDenominator() + fractionTwo.getDenominator();
    calculationSteps.append(fractionOne.getDenominator() + ADDITION + fractionTwo.getDenominator()
        + EQUAL + sumDenominators);

    MixedFraction uglyAnswer = new MixedFraction(0, sumNumerators, sumDenominators);
    calculationSteps
        .append(TABTWO + STRINGS.getString(ADD_RESULT) + COLON + uglyAnswer.toString() + FIVE);

    MixedFraction answer = formFraction(uglyAnswer, form, calculationSteps);

    // Append the final result to the calculationSteps StringBuilder
    calculationSteps.append(STRINGS.getString(ADD_FINAL) + COLON + answer.toString() + TABTWO);

    // Append the calculationSteps to the file
    writeCalculationStepsToFile(calculationSteps.toString(), answer);

    return answer;
  }

  /**
   * Calculates the MixedFraction to the power of an integer.
   * 
   * @param fraction
   *          The MixedFraction to raise to a power.
   * @param form
   *          The desired form of the result (PROPER, REDUCED, IMPROPER).
   * @return The result of the operation as a MixedFraction.
   * @throws IOException 
   */
  public static MixedFraction power(final MixedFraction fraction, final FractionForm form) throws IOException
  {
    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("POW_ONE") + SPACE + fraction.toString() + " ^"
        + fraction.getExponent() + ONE);

    int revisedNumerator = revisedNumerator(fraction, calculationSteps);

    int oldDom = fraction.getDenominator();

    calculationSteps
        .append(TWO + STRINGS.getString("POW_TWO") + SPACE + (fraction.getExponent() - 1) + TIMES);
    calculationSteps.append(revisedNumerator + MULTI + revisedNumerator + EQUAL
        + (revisedNumerator * revisedNumerator));
    for (int i = 2; i < fraction.getExponent(); i++)
    {
      calculationSteps
          .append((MULTI + (revisedNumerator) + EQUAL + (int) Math.pow(revisedNumerator, i + 1)));
    }
    int newNum = (int) Math.pow(revisedNumerator, fraction.getExponent());

    calculationSteps.append(
        "\n3. " + STRINGS.getString("POW_THREE") + SPACE + (fraction.getExponent() - 1) + TIMES);
    calculationSteps.append(oldDom + MULTI + oldDom + EQUAL + (oldDom * oldDom));
    for (int i = 2; i < fraction.getExponent(); i++)
    {
      calculationSteps.append((MULTI + (oldDom) + EQUAL + (int) Math.pow(oldDom, i + 1)));
    }
    int newDom = (int) Math.pow(oldDom, fraction.getExponent());

    MixedFraction uglyAnswer = new MixedFraction(0, newNum, newDom);
    calculationSteps
        .append(TABTWO + STRINGS.getString(ADD_RESULT) + COLON + uglyAnswer.toString() + FOUR);

    MixedFraction answer = formFraction(uglyAnswer, form, calculationSteps);

    // Append the final result to the calculationSteps StringBuilder
    calculationSteps.append(STRINGS.getString(ADD_FINAL) + COLON + answer.toString() + TABTWO);

    // Append the calculationSteps to the file
    writeCalculationStepsToFile(calculationSteps.toString(), answer);

    return answer;
  }

  /**
   * Calculates the multiplicative inverse of a MixedFraction.
   *
   * @param fraction
   *          The MixedFraction to find the inverse of.
   * @param form
   *          The desired form of the result (PROPER, REDUCED, IMPROPER).
   * @return The multiplicative inverse of the MixedFraction as a MixedFraction.
   * @throws IOException 
   */
  public static MixedFraction invert(final MixedFraction fraction, final FractionForm form) throws IOException
  {
    StringBuilder calculationSteps = new StringBuilder();

    calculationSteps.append(STRINGS.getString("INV_ONE") + SPACE + fraction.toString() + ONE);

    int revisedNumerator = revisedNumerator(fraction, calculationSteps);

    calculationSteps.append(TWO + STRINGS.getString("INV_TWO") + TAB);
    int oldDenominator = fraction.getDenominator();
    calculationSteps
        .append(STRINGS.getString("INV_THREE") + COLON + revisedNumerator + DIV + oldDenominator);
    calculationSteps.append(
        TAB + STRINGS.getString("INV_FOUR") + COLON + oldDenominator + DIV + revisedNumerator);

    MixedFraction uglyAnswer = new MixedFraction(0, oldDenominator, revisedNumerator);
    calculationSteps.append(TABTWO + STRINGS.getString(ADD_RESULT) + COLON + oldDenominator + DIV
        + revisedNumerator + "\n\n3. ");

    MixedFraction answer = formFraction(uglyAnswer, form, calculationSteps);

    // Append the final result to the calculationSteps StringBuilder
    calculationSteps.append(STRINGS.getString(ADD_FINAL) + COLON + answer.toString() + TABTWO);

    // Append the calculationSteps to the file
    writeCalculationStepsToFile(calculationSteps.toString(), answer);
    return answer;
  }

  /**
   * Simplifies the fraction.
   * 
   * @param notSimple
   *          the non-simple fraction
   * @param form
   *          the form of the simplified fraction
   * @return a newly simplified fraction.
   * @throws IOException 
   */
  public static MixedFraction simplify(final MixedFraction notSimple, final FractionForm form) throws IOException
  {
    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("SIMPLIFY_ONE") + SPACE + notSimple.toString() + TAB);
    int revisedNumerator1 = revisedNumerator(notSimple, calculationSteps);
    MixedFraction answer = new MixedFraction(0, revisedNumerator1, notSimple.getDenominator());
    answer = formFraction(answer, form, calculationSteps);
    calculationSteps.append(STRINGS.getString(ADD_FINAL) + COLON + answer.toString() + TABTWO);
    writeCalculationStepsToFile(calculationSteps.toString(), answer);
    return answer;
  }

  /**
   * Checks is a fraction is greater than another.
   * 
   * @param fractionOne
   *          -- first fraction
   * @param fractionTwo
   *          -- second fraction
   * 
   * @return true if fraction one is greater than fraction two
   */

  public static boolean greaterThan(final MixedFraction fractionOne,
      final MixedFraction fractionTwo)
  {

    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("GRE_ONE") + SPACE + fractionOne.toString() + AND
        + fractionTwo.toString() + ONE);

    double revisedNumerator1 = revisedNumerator(fractionOne, calculationSteps);
    double revisedNumerator2 = revisedNumerator(fractionTwo, calculationSteps);

    double fracOneVal = revisedNumerator1 / fractionOne.getDenominator();
    double fracTwoVal = revisedNumerator2 / fractionTwo.getDenominator();

    calculationSteps.append(STRINGS.getString("GRE_TWO") + DOT + STRINGS.getString(COOL) + COLON
        + fracOneVal + SPACETWO + STRINGS.getString(GRE) + COLON + fracTwoVal + TABTWO);

    if (fracOneVal > fracTwoVal)
    {
      calculationSteps.append(STRINGS.getString("GRE_FOUR") + LAME);
      return true;
    }
    else
    {
      calculationSteps.append(STRINGS.getString("GRE_FIVE") + LAME);
      return false;
    }
  }

  /**
   * Checks if a fraction is less than another.
   * 
   * @param fractionOne
   *          -- first fraction
   * @param fractionTwo
   *          -- second fraction
   * 
   * @return true if fraction one is less than fraction two
   */

  public static boolean lessThan(final MixedFraction fractionOne, final MixedFraction fractionTwo)
  {

    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("LESS_ONE") + SPACE + fractionOne.toString() + AND
        + fractionTwo.toString() + ONE);

    double revisedNumerator1 = revisedNumerator(fractionOne, calculationSteps);
    double revisedNumerator2 = revisedNumerator(fractionTwo, calculationSteps);

    double fracOneVal = revisedNumerator1 / fractionOne.getDenominator();
    double fracTwoVal = revisedNumerator2 / fractionTwo.getDenominator();

    calculationSteps.append(STRINGS.getString("LESS_TWO") + DOT + STRINGS.getString(COOL) + COLON
        + fracOneVal + STRINGS.getString(GRE) + "  : " + fracTwoVal + TABTWO);
    if (fracOneVal < fracTwoVal)
    {
      calculationSteps.append(STRINGS.getString("LESS_THREE") + LAME);
      return true;
    }
    else
    {
      calculationSteps.append(STRINGS.getString("LESS_FOUR") + LAME);
      return false;
    }
  }

  /**
   * Checks if a fraction is equal to another.
   * 
   * @param fractionOne
   *          -- first fraction
   * @param fractionTwo
   *          -- second fraction
   * 
   * @return true if fraction one is equal to fraction two
   */

  public static boolean equalTo(final MixedFraction fractionOne, final MixedFraction fractionTwo)
  {

    StringBuilder calculationSteps = new StringBuilder();
    calculationSteps.append(STRINGS.getString("EQU_ONE") + SPACE + fractionOne.toString() + AND
        + fractionTwo.toString() + ONE);

    double revisedNumerator1 = revisedNumerator(fractionOne, calculationSteps);
    double revisedNumerator2 = revisedNumerator(fractionTwo, calculationSteps);

    double fracOneVal = revisedNumerator1 / fractionOne.getDenominator();
    double fracTwoVal = revisedNumerator2 / fractionTwo.getDenominator();

    calculationSteps.append(STRINGS.getString("EQU_TWO") + DOT + STRINGS.getString(COOL) + COLON
        + fracOneVal + SPACETWO + STRINGS.getString(GRE) + COLON + fracTwoVal + TABTWO);
    if (fracOneVal == fracTwoVal)
    {
      calculationSteps.append(STRINGS.getString("EQU_THREE") + LAME);
      return true;
    }
    else
    {
      calculationSteps.append(STRINGS.getString("EQU_FOUR") + LAME);
      return false;
    }
  }

  /**
   * Turns the fraction improper.
   * 
   * @param fraction
   *          to turn improper
   * @param calculationSteps
   *          the stringbuilder for instructions
   * @return int numerator turned improper
   */
  public static int revisedNumerator(final MixedFraction fraction,
      final StringBuilder calculationSteps)
  {
    int result;
    calculationSteps.append(STRINGS.getString("REV_ONE") + SPACE + fraction.toString() + TABTHREE);
    if (fraction.getCoefficient() < 0)
    {
      result = fraction.getCoefficient() * fraction.getDenominator() - fraction.getNumerator();
      calculationSteps.append("    1." + STRINGS.getString("REV_TWO") + " : ");
      calculationSteps.append(fraction.getCoefficient() + MULTI_TWO + fraction.getDenominator()
          + EQUAL + (fraction.getCoefficient() * fraction.getDenominator()) + TABTHREE);
      calculationSteps.append(TWO_TWO + STRINGS.getString("REV_THREE") + COLON);
      calculationSteps.append((fraction.getCoefficient() * fraction.getDenominator()) + SUBTRACTION
          + fraction.getNumerator() + EQUAL + result + TABTWO);
    }
    else
    {
      result = (fraction.getCoefficient() * fraction.getDenominator()) + fraction.getNumerator();
      calculationSteps.append("    1. " + STRINGS.getString("REV_FOUR") + COLON);
      calculationSteps.append(fraction.getCoefficient() + MULTI_TWO + fraction.getDenominator()
          + EQUAL + (fraction.getCoefficient() * fraction.getDenominator()) + TABTHREE);
      calculationSteps.append(TWO_TWO + STRINGS.getString("REV_FIVE") + COLON);
      calculationSteps.append((fraction.getCoefficient() * fraction.getDenominator()) + ADDITION
          + fraction.getNumerator() + EQUAL + result + TABTWO);
    }
    return result;
  }

  /**
   * Checks to see if the value is negative and adjusts the MixedFraction accordingly.
   *
   * @param fraction
   *          The MixedFraction to check and potentially adjust.
   * 
   * @return The adjusted MixedFraction.
   */
  public static MixedFraction checkNegativeAnything(final MixedFraction fraction)
  {
    if (fraction.getCoefficient() == 0)
    {
      if (fraction.getNumerator() * fraction.getDenominator() < 0)
      {
        if (fraction.getNumerator() < 0)
        {
          fraction.setNumerator(fraction.getNumerator() * 1);
        }
        else
        {
          fraction.setNumerator(fraction.getNumerator() * -1);
        }
        if (fraction.getDenominator() < 0)
        {
          fraction.setDenominator(fraction.getDenominator() * -1);
        }
      }
    }
    if (fraction.getCoefficient() * fraction.getNumerator() * fraction.getDenominator() < 0)
    {
      if (fraction.getCoefficient() < 0)
      {
        fraction.setCoefficient(fraction.getCoefficient() * 1);
      }
      else
      {
        fraction.setCoefficient(fraction.getCoefficient() * -1);
      }
      if (fraction.getNumerator() < 0)
      {
        fraction.setNumerator(fraction.getNumerator() * -1);
      }
    }
    else if (fraction.getDenominator() < 0)
    {
      fraction.setDenominator(fraction.getDenominator() * -1);
    }
    else if (fraction.getCoefficient() < 0)
    {
      fraction.setNumerator(fraction.getNumerator() * -1);
    }
    return fraction;
  }

  /**
   * Forms the MixedFraction based on the chosen FractionForm.
   *
   * @param answer
   *          The MixedFraction to be formed.
   * @param form
   *          The desired form (PROPER, REDUCED, IMPROPER).
   * @param calculationSteps
   *          the stringbuilder for instructions
   * @return The formed MixedFraction.
   */
  public static MixedFraction formFraction(final MixedFraction answer, final FractionForm form,
      final StringBuilder calculationSteps)
  {

    StringBuilder calculationSteps1;

    if (calculationSteps == null)
    {

      calculationSteps1 = new StringBuilder();

    }
    else
    {

      calculationSteps1 = calculationSteps;

    }

    

    if (form == FractionForm.REDUCED_PROPER)
    {
      calculationSteps1.append(STRINGS.getString(SIMPLIFY));
      return reducedProper(answer, calculationSteps1);

    }
    else if (form == FractionForm.REDUCED_IMPROPER)
    {
      calculationSteps1.append(STRINGS.getString(SIMPLIFY));
      return reducedImproper(answer, calculationSteps1);

    }
    else if (form == FractionForm.IRREDUCED_IMPROPER)
    {

      return irreducedImproper(answer, calculationSteps1);

    }
    else if (form == FractionForm.IRREDUCED_PROPER)
    {
      calculationSteps1.append(STRINGS.getString(SIMPLIFY));
      return irreducedProper(answer, calculationSteps1);

    }
    else
    {

      throw new UnsupportedOperationException();

    }

  }

  /**
   * Simplifies the MixedFraction and converts it to proper form.
   *
   * @param notSimpleAnswer
   *          The MixedFraction to simplify and convert.
   * @param calculationSteps
   *          the stringbuilder for instructions
   * @return The simplified MixedFraction in proper form.
   */
  public static MixedFraction proper(final MixedFraction notSimpleAnswer,
      final StringBuilder calculationSteps)
  {
    calculationSteps.append(TABTHREE + STRINGS.getString("PRO_ONE"));
    calculationSteps.append(ONE_TWO + STRINGS.getString("PRO_TWO") + TAB_FIVE);
    int newCoefficient = notSimpleAnswer.getNumerator() / notSimpleAnswer.getDenominator();
    calculationSteps.append(notSimpleAnswer.getNumerator() + DIVIDE
        + notSimpleAnswer.getDenominator() + EQUAL + newCoefficient);
    notSimpleAnswer.setCoefficient(newCoefficient);

    calculationSteps.append(TAB_SIX + STRINGS.getString("PRO_THREE") + ")\n        ");
    int newNumerator = notSimpleAnswer.getNumerator()
        - (newCoefficient * notSimpleAnswer.getDenominator());
    calculationSteps.append(notSimpleAnswer.getNumerator() + " - (" + newCoefficient + MULTI
        + notSimpleAnswer.getDenominator() + ") = " + newNumerator + TABTHREE);
    notSimpleAnswer.setNumerator(newNumerator);

    return notSimpleAnswer;
  }

  /**
   * Reduces the MixedFraction to its simplest form.
   *
   * @param notReducedAnswer
   *          The MixedFraction to reduce.
   * @param calculationSteps
   *          the stringbuilder for instructions
   * @return The reduced MixedFraction.
   */
  public static MixedFraction reduce(final MixedFraction notReducedAnswer,
      final StringBuilder calculationSteps)
  {
    StringBuilder calculationSteps1;
    if (calculationSteps == null)
    {
      calculationSteps1 = new StringBuilder();
    }
    else
    {
      calculationSteps1 = calculationSteps;
    }
    calculationSteps1.append(TABTHREE + STRINGS.getString("RED_ONE"));
    calculationSteps1.append(ONE_TWO + STRINGS.getString("RED_TWO") + TAB_FIVE);
    int gcd = gcd(notReducedAnswer.getNumerator(), notReducedAnswer.getDenominator());
    calculationSteps1.append(STRINGS.getString("RED_THREE") + COLON + gcd);
    calculationSteps1.append(TAB_SIX + STRINGS.getString("RED_FOUR") + TAB_FIVE);
    int reducedNumerator = (notReducedAnswer.getNumerator() / gcd);
    calculationSteps1
        .append(notReducedAnswer.getNumerator() + DIVIDE + gcd + EQUAL + reducedNumerator);
    int reducedDenominator = (notReducedAnswer.getDenominator() / gcd);
    calculationSteps1.append(
        TAB_FIVE + notReducedAnswer.getDenominator() + DIVIDE + gcd + EQUAL + reducedDenominator);
    MixedFraction answer = new MixedFraction(notReducedAnswer.getCoefficient(), reducedNumerator,
        reducedDenominator);
    calculationSteps1.append(TABTHREE + STRINGS.getString("RED_FIVE") + COLON + answer.toString());
    return answer;
  }

  /**
   * Converts the MixedFraction to an reduced proper fraction.
   *
   * @param notReducedAnswer
   *          The MixedFraction to convert.
   * @param calculationSteps
   *          the stringbuilder for instructions
   * @return The MixedFraction in reduced proper fraction form.
   */
  public static MixedFraction reducedProper(final MixedFraction notReducedAnswer,
      final StringBuilder calculationSteps)
  {
    MixedFraction reducedImproper = reduce(notReducedAnswer, calculationSteps);
    MixedFraction reducedProper = proper(reducedImproper, calculationSteps);
    checkNegativeAnything(reducedProper);

    return reducedProper;
  }

  /**
   * Converts the MixedFraction to an reduced improper fraction.
   *
   * @param notReducedAnswer
   *          The MixedFraction to convert.
   * @param calculationSteps
   *          the stringbuilder for instructions
   * @return The MixedFraction in reduced improper fraction form.
   */
  public static MixedFraction reducedImproper(final MixedFraction notReducedAnswer,
      final StringBuilder calculationSteps)
  {
    MixedFraction reducedImproper = reduce(notReducedAnswer, calculationSteps);
    checkNegativeAnything(reducedImproper);

    return reducedImproper;
  }

  /**
   * Converts the MixedFraction to an irreduced proper fraction.
   *
   * @param notReducedAnswer
   *          The MixedFraction to convert.
   * @param calculationSteps
   *          the stringbuilder for instructions
   * @return The MixedFraction in irreduced proper fraction form.
   */
  public static MixedFraction irreducedProper(final MixedFraction notReducedAnswer,
      final StringBuilder calculationSteps)
  {
    MixedFraction irreducedProper = proper(notReducedAnswer, calculationSteps);
    checkNegativeAnything(irreducedProper);

    return irreducedProper;
  }

  /**
   * Converts the MixedFraction to an irreduced improper fraction.
   *
   * @param notReducedAnswer
   *          The MixedFraction to convert.
   * @param calculationSteps
   *          the stringbuilder for instructions
   * @return The MixedFraction in irreduced improper fraction form.
   */
  public static MixedFraction irreducedImproper(final MixedFraction notReducedAnswer,
      final StringBuilder calculationSteps)
  {
    checkNegativeAnything(notReducedAnswer);

    return notReducedAnswer;
  }

  /**
   * Finds the greatest common denominator (gcd).
   *
   * @param a
   *          The first number.
   * @param b
   *          The second number.
   * @return The greatest common denominator.
   */
  public static int gcd(final int a, final int b)
  {
    if (b == 0)
    {
      return a;
    }
    else
    {
      return gcd(b, a % b);
    }
  }
  /**
   * Writes calculation steps to file.
   *
   * @param calculationSteps
   *          file to write to.
   * @param result
   *          MixedFraction to write.
   */
  public static void writeCalculationStepsToFile(final String calculationSteps, final MixedFraction result) throws IOException {
    File resultFile = new File(RESULT_FILE);

    BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile, resultFile.exists()));

    deleteFileContents(RESULT_FILE);

    writer.write(calculationSteps);
    writer.close();
}

/**
 * Method to delete the contents of a file.
 *
 * @param fileName name of file
 */
public static void deleteFileContents(final String fileName) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(fileName);
    System.out.print("");
    writer.close();
}

}
