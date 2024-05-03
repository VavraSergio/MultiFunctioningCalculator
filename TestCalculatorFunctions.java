package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.*;

import numbers.MixedFraction;
import numbers.Operation;
import numbers.CalculatorFunctions;
import numbers.CurrentMixedNumber;
import numbers.Expression;
import numbers.FractionForm;

/**
 * JUnit tests for addition and subtraction.
 * 
 * @author Parker A. & Cole D.
 * @version 11-2-23
 */
public class TestCalculatorFunctions {
	
    @Test
    public void testCalculateAdd39() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 2);
        MixedFraction fraction2 = new MixedFraction(1, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(3, result.getCoefficient());
        assertEquals(1, result.getNumerator());
        assertEquals(4, result.getDenominator());
    }


  @Test
  public void testConstructorAndMultiply() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(1, 3, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(2, result.getCoefficient());
      assertEquals(5, result.getNumerator());
      assertEquals(8, result.getDenominator());
  }

  @Test
  public void testConstructorAndDivide() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(1, 3, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
     
      assertEquals(0, result.getCoefficient());
      assertEquals(6, result.getNumerator());
      assertEquals(7, result.getDenominator());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testInvalidOperation() throws IOException {
      MixedFraction fractionOne = new MixedFraction(1, 1, 2);
      MixedFraction fractionTwo = new MixedFraction(1, 3, 4);
      MixedFraction result = CalculatorFunctions.calculate(fractionOne, fractionTwo, null, FractionForm.REDUCED_PROPER);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideByZero2() throws IOException {
      MixedFraction fractionOne = new MixedFraction(1, 1, 2);
      MixedFraction fractionTwo = new MixedFraction(0, 0, 1);
      MixedFraction result = CalculatorFunctions.calculate(fractionOne, fractionTwo, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
  }


  @Test
  public void testGCD() {
	  int num1 = 2;
	  int num2 = 3;
	  int num3 = 0;
	  
	  int gcd12 = CalculatorFunctions.gcd(num1, num2);
	  int gcd13 = CalculatorFunctions.gcd(num1, num3);
	  
	  assertEquals(2, gcd13);
	  assertEquals(1, gcd12);
  }
  
	
	@Test
	public void testAddition1() throws IOException {
        MixedFraction fraction1 = new MixedFraction(3, 5, 8);
        MixedFraction fraction2 = new MixedFraction(2, 1, 2);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(6, result.getCoefficient());
        assertEquals(1, result.getNumerator());
        assertEquals(8, result.getDenominator());
		}
	
	@Test
	public void testAddition1Prop() throws IOException {
        MixedFraction fraction1 = new MixedFraction(3, 5, 8);
        MixedFraction fraction2 = new MixedFraction(2, 1, 2);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(6, result.getCoefficient());
        assertEquals(1, result.getNumerator());
        assertEquals(8, result.getDenominator());
		}
	
	@Test
	public void testAddition1ReducedProp() throws IOException {
        MixedFraction fraction1 = new MixedFraction(3, 5, 8);
        MixedFraction fraction2 = new MixedFraction(2, 1, 2);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(6, result.getCoefficient());
        assertEquals(1, result.getNumerator());
        assertEquals(8, result.getDenominator());
		}
	
	@Test
	public void testAddition2() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 2);
        MixedFraction fraction2 = new MixedFraction(1, 1, 2);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(3, result.getCoefficient());
        assertEquals(0, result.getNumerator());
        assertEquals(1, result.getDenominator());
		}
	
	@Test
	public void testAddition3() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 3, 4);
        MixedFraction fraction2 = new MixedFraction(3, 1, 5);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(4, result.getCoefficient());
        assertEquals(19, result.getNumerator());
        assertEquals(20, result.getDenominator());
		}
	
	@Test
	public void testAddition4() throws IOException {
        MixedFraction fraction1 = new MixedFraction(0, 3, 4);
        MixedFraction fraction2 = new MixedFraction(0, 1, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(1, result.getCoefficient());
        assertEquals(0, result.getNumerator());
        assertEquals(1, result.getDenominator());
		}
	
	@Test
	  public void testAddition5() throws IOException {
        MixedFraction fraction1 = new MixedFraction(0, -3, 4);
        MixedFraction fraction2 = new MixedFraction(0, 1, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(0, result.getCoefficient());
        assertEquals(-1, result.getNumerator());
        assertEquals(2, result.getDenominator());
	  	}
	
	public void testAddition8() throws IOException {
        MixedFraction fraction1 = new MixedFraction(15, 5, 91);
        MixedFraction fraction2 = new MixedFraction(-28, 1, 78);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(-12, result.getCoefficient());
        assertEquals(523, result.getNumerator());
        assertEquals(546, result.getDenominator());
	}
	
	public void testAddition9() throws IOException {
	    MixedFraction fraction1 = new MixedFraction(1, 1, 2);
	    MixedFraction fraction2 = new MixedFraction(-1, 1, 2);
	    MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
	   
	    assertEquals(0, result.getCoefficient());
	    assertEquals(0, result.getNumerator());
	    assertEquals(1, result.getDenominator());
	  }
	
	@Test
	public void testAddition6() throws IOException {
        MixedFraction fraction1 = new MixedFraction(-1, 1, 2);
        MixedFraction fraction2 = new MixedFraction(1, 1, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(0, result.getCoefficient());
        assertEquals(-1, result.getNumerator());
        assertEquals(4, result.getDenominator());
	}	
	
	@Test
	public void testSubtraction1() throws IOException {
        MixedFraction fraction1 = new MixedFraction(3, 5, 8);
        MixedFraction fraction2 = new MixedFraction(2, 1, 2);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_PROPER);
       
        assertEquals(1, result.getCoefficient());
        assertEquals(1, result.getNumerator());
        assertEquals(8, result.getDenominator());
	}
	
	@Test
	public void testSubtraction2() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 3, 4);
        MixedFraction fraction2 = new MixedFraction(3, 1, 5);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_PROPER);
       
        assertEquals(-1, result.getCoefficient());
        assertEquals(9, result.getNumerator());
        assertEquals(20, result.getDenominator());
	}
	
	@Test
	public void testSubtraction3() throws IOException {
        MixedFraction fraction1 = new MixedFraction(5, 2, 5);
        MixedFraction fraction2 = new MixedFraction(3, 1, 2);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_PROPER);
       
        assertEquals(1, result.getCoefficient());
        assertEquals(9, result.getNumerator());
        assertEquals(10, result.getDenominator());
	}
	
	@Test
	public void testSubtraction4() throws IOException {
        MixedFraction fraction1 = new MixedFraction(7, 2, 7);
        MixedFraction fraction2 = new MixedFraction(10, 13, 20);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_PROPER);
       
        assertEquals(-3, result.getCoefficient());
        assertEquals(51, result.getNumerator());
        assertEquals(140, result.getDenominator());
	}
	
	@Test
	public void testSubtraction5() throws IOException {	
        MixedFraction fraction1 = new MixedFraction(-9, 2, 7);
        MixedFraction fraction2 = new MixedFraction(-10, 1, 20);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_PROPER);
       
        assertEquals(0, result.getCoefficient());
        assertEquals(107, result.getNumerator());
        assertEquals(140, result.getDenominator());
	}
	
	@Test
  public void MultiplyTwoMixedFracDiffDenom() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 3, 4);
      MixedFraction fraction2 = new MixedFraction(3, 1, 5);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(5, result.getCoefficient());
      assertEquals(3, result.getNumerator());
      assertEquals(5, result.getDenominator());
  }
  
  @Test
public void testMultiplyTwoNonMixedFracSameDenom() throws IOException {
      MixedFraction fraction1 = new MixedFraction(0, 3, 4);
      MixedFraction fraction2 = new MixedFraction(0, 1, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(0, result.getCoefficient());
      assertEquals(3, result.getNumerator());
      assertEquals(16, result.getDenominator());
  }
  
  @Test
  public void testMultiplyTwoSameFrac() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 3, 6);
      MixedFraction fraction2 = new MixedFraction(1, 3, 6);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(2, result.getCoefficient());
      assertEquals(1, result.getNumerator());
      assertEquals(4, result.getDenominator());
  }
  
  @Test
  public void testMultiplyIntByMixFrac() throws IOException {
      MixedFraction fraction1 = new MixedFraction(0, 1, 2);
      MixedFraction fraction2 = new MixedFraction(2, 0, 0);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(1, result.getCoefficient());
      assertEquals(0, result.getNumerator());
      assertEquals(1, result.getDenominator());
  }
  
  @Test
  public void testMultiplyNegFracByMixFrac() throws IOException {
      MixedFraction fraction1 = new MixedFraction(-1, 3, 4);
      MixedFraction fraction2 = new MixedFraction(3, 1, 5);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(-5, result.getCoefficient());
      assertEquals(3, result.getNumerator());
      assertEquals(5, result.getDenominator());
  }
  
  @Test
  public void testMultiplyTwoNegativeFrac() throws IOException {
      MixedFraction fraction1 = new MixedFraction(0, -1, 2);
      MixedFraction fraction2 = new MixedFraction(-2, 0, 0);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(1, result.getCoefficient());
      assertEquals(0, result.getNumerator());
      assertEquals(1, result.getDenominator());
  }
  
  @Test
  public void testDivide1() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(2, 1, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
     
      assertEquals(0, result.getCoefficient());
      assertEquals(2, result.getNumerator());
      assertEquals(3, result.getDenominator());
  }
  
  @Test
  public void testDivide3() throws IOException {
      MixedFraction fraction1 = new MixedFraction(-1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(-2, 1, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
     
      assertEquals(0, result.getCoefficient());
      assertEquals(2, result.getNumerator());
      assertEquals(3, result.getDenominator());
  }
  
  @Test
  public void testDivide2() throws IOException {
      MixedFraction fraction1 = new MixedFraction(-48, 4, 29);
      MixedFraction fraction2 = new MixedFraction(2, 1, 302);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
      
      assertEquals(-24, result.getCoefficient());
      assertEquals(512, result.getNumerator());
      assertEquals(17545, result.getDenominator());
  }
  
  @Test
  public void testMultiplyTwoMixedFracSameDenom() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 3, 4);
      MixedFraction fraction2 = new MixedFraction(3, 1, 5);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(5, result.getCoefficient());
      assertEquals(3, result.getNumerator());
      assertEquals(5, result.getDenominator());
  }

  @Test
  public void testMultiplyByZero() throws IOException {
      MixedFraction fraction1 = new MixedFraction(0, 0, 1);
      MixedFraction fraction2 = new MixedFraction(2, 3, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(0, result.getCoefficient());
      assertEquals(0, result.getNumerator());
      assertEquals(1, result.getDenominator());
  }

  @Test
  public void testDivideByZero() {
      MixedFraction fractionOne = new MixedFraction(1, 2, 3);
      MixedFraction fractionTwo = new MixedFraction(0, 0, 1);
      
      // Expect an IllegalArgumentException to be thrown
      assertThrows(IllegalArgumentException.class, () -> {MixedFraction result = CalculatorFunctions.calculate(fractionOne, fractionTwo, Operation.DIVIDE, FractionForm.REDUCED_PROPER);;});
  }
  
  @Test
  public void testAddition() throws IOException {
      MixedFraction fraction1 = new MixedFraction(3, 5, 8);
      MixedFraction fraction2 = new MixedFraction(2, 1, 2);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
     
      assertEquals(6, result.getCoefficient());
      assertEquals(1, result.getNumerator());
      assertEquals(8, result.getDenominator());
  }

  @Test
  public void testSubtraction() throws IOException {
      MixedFraction fraction1 = new MixedFraction(3, 5, 8);
      MixedFraction fraction2 = new MixedFraction(2, 1, 2);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_PROPER);
     
      assertEquals(1, result.getCoefficient());
      assertEquals(1, result.getNumerator());
      assertEquals(8, result.getDenominator());
  }
  
  @Test
  public void testSubtraction9() throws IOException {
      MixedFraction fraction1 = new MixedFraction(2, 1, 2);
      MixedFraction fraction2 = new MixedFraction(5, 1, 7);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_PROPER);
     
      assertEquals(-2, result.getCoefficient());
      assertEquals(9, result.getNumerator());
      assertEquals(14, result.getDenominator());
  }

  @Test
  public void testMultiplication() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 3, 4);
      MixedFraction fraction2 = new MixedFraction(3, 1, 5);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
     
      assertEquals(5, result.getCoefficient());
      assertEquals(3, result.getNumerator());
      assertEquals(5, result.getDenominator());
  }

  @Test
  public void testDivision() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(2, 1, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
     
      assertEquals(0, result.getCoefficient());
      assertEquals(2, result.getNumerator());
      assertEquals(3, result.getDenominator());
  }
  
  @Test
  public void testDivisionRealTest() throws IOException {
      MixedFraction fraction1 = new MixedFraction(2, 11, 17);
      MixedFraction fraction2 = new MixedFraction(0, 27, 34);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
     
      assertEquals(3, result.getCoefficient());
      assertEquals(1, result.getNumerator());
      assertEquals(3, result.getDenominator());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideByZero1() throws IOException {
      MixedFraction mf1 = new MixedFraction(1, 1, 2);
      MixedFraction mf2 = new MixedFraction(0, 0, 1);

      MixedFraction result = CalculatorFunctions.calculate(mf1, mf2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
  }
  
  @Test
  public void testDivideNum0Coe0() {
      MixedFraction fractionOne = new MixedFraction(0, 0, 3);
      MixedFraction fractionTwo = new MixedFraction(0, 0, 1);
      
      // Expect an IllegalArgumentException to be thrown
      assertThrows(IllegalArgumentException.class, () -> {MixedFraction result = CalculatorFunctions.calculate(fractionOne, fractionTwo, Operation.DIVIDE, FractionForm.REDUCED_PROPER);;});
  }
  
  @Test
  public void testDivideNum0Coe02() {
      MixedFraction fractionOne = new MixedFraction(0, 1, 3);
      MixedFraction fractionTwo = new MixedFraction(0, 0, 1);

      // Expect an IllegalArgumentException to be thrown
      assertThrows(IllegalArgumentException.class, () -> {MixedFraction result = CalculatorFunctions.calculate(fractionOne, fractionTwo, Operation.DIVIDE, FractionForm.REDUCED_PROPER);;});
  }
  
  @Test
  public void testDivideNum0Coe03() {
      MixedFraction fractionOne = new MixedFraction(1, 0, 3);
      MixedFraction fractionTwo = new MixedFraction(0, 0, 1);

      // Expect an IllegalArgumentException to be thrown
      assertThrows(IllegalArgumentException.class, () -> {MixedFraction result = CalculatorFunctions.calculate(fractionOne, fractionTwo, Operation.DIVIDE, FractionForm.REDUCED_PROPER);;});
  }
  
  @Test
  public void testDivideNum0Coe04() {
      MixedFraction fractionOne = new MixedFraction(0, 0, 3);
      MixedFraction fractionTwo = new MixedFraction(0, 1, 1);

      // Expect an IllegalArgumentException to be thrown
      assertThrows(IllegalArgumentException.class, () -> {MixedFraction result = CalculatorFunctions.calculate(fractionOne, fractionTwo, Operation.DIVIDE, FractionForm.REDUCED_PROPER);;});
  }
  
  @Test
  public void testInstantiateZeroDom() {      
      
    // Expect an IllegalArgumentException to be thrown
	assertThrows(IllegalArgumentException.class, () -> {MixedFraction fractionOne = new MixedFraction(1, 2, 0); ;});
  }
  
  	@Test
  	public void testDivideNum0Coe05() throws IOException {
	  MixedFraction fraction1 = new MixedFraction(0, 1, 3);
	  MixedFraction fraction2 = new MixedFraction(1, 0, 1);
	  MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
	 
	  assertEquals(0, result.getCoefficient());
	  assertEquals(1, result.getNumerator());
	  assertEquals(3, result.getDenominator());
  }
  
  	@Test
	public void testReduce1() { //wrong!!!
      MixedFraction fraction1 = new MixedFraction(0, 4, 8);
      MixedFraction fraction2 = new MixedFraction(0, 5, 10);
      MixedFraction result1 = CalculatorFunctions.reduce(fraction1, null);
      MixedFraction result2 = CalculatorFunctions.reduce(fraction2, null);
     
      assertEquals(0, result1.getCoefficient());
      assertEquals(1, result1.getNumerator());
      assertEquals(2, result1.getDenominator());
      
      assertEquals(0, result2.getCoefficient());
      assertEquals(1, result2.getNumerator());
      assertEquals(2, result2.getDenominator());
	}
	
	@Test
	public void testReduce2() {
      MixedFraction fraction1 = new MixedFraction(1, 5, 20);
      MixedFraction fraction2 = new MixedFraction(1, 2, 8);
      MixedFraction result1 = CalculatorFunctions.reduce(fraction1, null);
      MixedFraction result2 = CalculatorFunctions.reduce(fraction2, null);
     
      assertEquals(1, result1.getCoefficient());
      assertEquals(1, result1.getNumerator());
      assertEquals(4, result1.getDenominator());
      
      assertEquals(1, result2.getCoefficient());
      assertEquals(1, result2.getNumerator());
      assertEquals(4, result2.getDenominator());
	}
	
    @Test
    public void testAdd() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 2);
        MixedFraction fraction2 = new MixedFraction(2, 1, 3);
        MixedFraction result = CalculatorFunctions.add(fraction1, fraction2, FractionForm.REDUCED_PROPER);

        MixedFraction actual = new MixedFraction(3, 5, 6);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }
   
    @Test
    public void testAddWithREDUCED_PROPERFractions() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 4);
        MixedFraction fraction2 = new MixedFraction(2, 1, 3);
        MixedFraction result = CalculatorFunctions.add(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(3, 7, 12);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testAddWithImREDUCED_PROPERFractions() throws IOException {
        MixedFraction fraction1 = new MixedFraction(0, 5, 6);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.add(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(1, 7, 12);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testAddWithMixedAndImREDUCED_PROPERFractions() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.add(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(3, 0, 1);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testAddWithNegativeFractions() throws IOException {
        MixedFraction fraction1 = new MixedFraction(-1, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.add(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(0, -1, 2);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testAddWithDifferentDenominators() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 1, 3);
        MixedFraction result = CalculatorFunctions.add(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(1, 7, 12);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }


    @Test
    public void testSubtract() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 3, 4);
        MixedFraction fraction2 = new MixedFraction(1, 1, 2);
        MixedFraction result = CalculatorFunctions.subtract(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(1, 1, 4);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testMultiply() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 3, 4);
        MixedFraction fraction2 = new MixedFraction(1, 1, 2);
        MixedFraction result = CalculatorFunctions.multiply(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(4, 1, 8);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testDivide() throws IllegalArgumentException, IOException {
        MixedFraction fraction1 = new MixedFraction(2, 3, 4);
        MixedFraction fraction2 = new MixedFraction(1, 1, 2);
        MixedFraction result = CalculatorFunctions.divide(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(1, 5, 6);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testMediant() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 2);
        MixedFraction fraction2 = new MixedFraction(2, 1, 3);
        MixedFraction result = CalculatorFunctions.mediant(fraction1, fraction2, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(2, 0, 1);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testPower() throws IOException {
        MixedFraction fraction = new MixedFraction(1, 1, 2, 3);
        MixedFraction result = CalculatorFunctions.power(fraction, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(3, 3, 8);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testInvert() throws IOException {
        MixedFraction fraction = new MixedFraction(1, 1, 2);
        MixedFraction result = CalculatorFunctions.invert(fraction, FractionForm.REDUCED_PROPER);
        MixedFraction actual = new MixedFraction(0, 2, 3);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testRevisedNumerator() {
        MixedFraction fraction = new MixedFraction(1, 1, 2);
        //int result = CalculatorFunctions.revisedNumerator(fraction);
        //assertEquals(3, result);
    }

    @Test
    public void testCheckNegativeAnything() {
        MixedFraction fraction = new MixedFraction(1, -1, 2);
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fraction);
        MixedFraction actual = new MixedFraction(-1, 1, 2);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testFormFraction() {
        MixedFraction fraction = new MixedFraction(0, 10, 12);
        MixedFraction result = CalculatorFunctions.formFraction(fraction, FractionForm.REDUCED_PROPER, null);
        MixedFraction actual = new MixedFraction(0, 5, 6);
        assertEquals(actual.getCoefficient(), result.getCoefficient());
        assertEquals(actual.getNumerator(), result.getNumerator());
        assertEquals(actual.getDenominator(), result.getDenominator());
    }

    @Test
    public void testGcd() {
        int result = CalculatorFunctions.gcd(12, 18);
        assertEquals(6, result);
    }
   
    @Test
    public void testCalculateAdd() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
       
        assertEquals(2, result.getCoefficient());
        assertEquals(0, result.getNumerator());
        assertEquals(1, result.getDenominator());
    }

    @Test
    public void testCalculateSubtract() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_PROPER);
       
        assertEquals(1, result.getCoefficient());
        assertEquals(1, result.getNumerator());
        assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCalculateSubtractReducedImproper() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.REDUCED_IMPROPER);
       
        assertEquals(0, result.getCoefficient());
        assertEquals(3, result.getNumerator());
        assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCalculateSubtractIreducedImproper() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.IRREDUCED_IMPROPER);
       
        assertEquals(0, result.getCoefficient());
        assertEquals(24, result.getNumerator());
        assertEquals(16, result.getDenominator());
    }

    @Test
    public void testCalculateMultiply() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_PROPER);
       
        assertEquals(1, result.getCoefficient());
        assertEquals(11, result.getNumerator());
        assertEquals(16, result.getDenominator());
    }
    
    @Test
    public void testCalculateMultiplyRedImp() throws IOException {
        MixedFraction fraction1 = new MixedFraction(5, 1, 4);
        MixedFraction fraction2 = new MixedFraction(2, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_IMPROPER);
       
        assertEquals(0, result.getCoefficient());
        assertEquals(231, result.getNumerator());
        assertEquals(16, result.getDenominator());
    }
    
    @Test
    public void testCalculateMultiplyIredProp() throws IOException {
        MixedFraction fraction1 = new MixedFraction(5, 1, 4);
        MixedFraction fraction2 = new MixedFraction(2, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.IRREDUCED_PROPER);
       
        assertEquals(14, result.getCoefficient());
        assertEquals(7, result.getNumerator());
        assertEquals(16, result.getDenominator());
    }
    
    @Test
    public void testCalculateMultiplyImproperReduced() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.REDUCED_IMPROPER);
       
        assertEquals(0, result.getCoefficient());
        assertEquals(27, result.getNumerator());
        assertEquals(16, result.getDenominator());
    }
    
    @Test
    public void testCalculateMultiplyImproperIrreduced() throws IOException {
        MixedFraction fraction1 = new MixedFraction(2, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MULTIPLY, FractionForm.IRREDUCED_IMPROPER);
       
        assertEquals(0, result.getCoefficient());
        assertEquals(27, result.getNumerator());
        assertEquals(16, result.getDenominator());
    }

    @Test
    public void testCalculateDivide() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
        MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.DIVIDE, FractionForm.REDUCED_PROPER);
       
        assertEquals(1, result.getCoefficient());
        assertEquals(2, result.getNumerator());
        assertEquals(3, result.getDenominator());
    }

    @Test
    public void testCalculateMediant() throws IOException {
        MixedFraction fraction1 = new MixedFraction(1, 1, 4);
        MixedFraction fraction2 = new MixedFraction(0, 3, 4);
	    MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.MEDIANT, FractionForm.REDUCED_PROPER);
	   
	    assertEquals(1, result.getCoefficient());
	    assertEquals(0, result.getNumerator());
	    assertEquals(1, result.getDenominator());
	}
    
    @Test
    public void testInvalidForm() {
        MixedFraction fractionOne = new MixedFraction(0, 0, 3);

        // Expect an IllegalArgumentException to be thrown
        assertThrows(UnsupportedOperationException.class, () -> {MixedFraction result = CalculatorFunctions.formFraction(fractionOne, null, null);;});
    }
    
    @Test
    public void testCheckNeg1() {
        MixedFraction fractionOne = new MixedFraction(0, -1, 2);
        
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fractionOne);

	    assertEquals(0, result.getCoefficient());
	    assertEquals(-1, result.getNumerator());
	    assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCheckNeg2() {
        MixedFraction fractionOne = new MixedFraction(0, 1, -2);
        
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fractionOne);

	    assertEquals(0, result.getCoefficient());
	    assertEquals(-1, result.getNumerator());
	    assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCheckNeg3() {
        MixedFraction fractionOne = new MixedFraction(0, 0, -2);
        
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fractionOne);

	    assertEquals(0, result.getCoefficient());
	    assertEquals(0, result.getNumerator());
	    assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCheckNeg4() {
        MixedFraction fractionOne = new MixedFraction(0, 1, -2);
        
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fractionOne);

	    assertEquals(0, result.getCoefficient());
	    assertEquals(-1, result.getNumerator());
	    assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCheckNeg5() {
        MixedFraction fractionOne = new MixedFraction(-1, 1, 2);
        
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fractionOne);

	    assertEquals(-1, result.getCoefficient());
	    assertEquals(1, result.getNumerator());
	    assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCheckNeg6() {
        MixedFraction fractionOne = new MixedFraction(-1, -1, 2);
        
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fractionOne);

	    assertEquals(-1, result.getCoefficient());
	    assertEquals(1, result.getNumerator());
	    assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCheckNeg7() {
        MixedFraction fractionOne = new MixedFraction(1, 1, 2);
        
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fractionOne);

	    assertEquals(1, result.getCoefficient());
	    assertEquals(1, result.getNumerator());
	    assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testCheckNeg8() {
        MixedFraction fractionOne = new MixedFraction(1, -1, 2);
        
        MixedFraction result = CalculatorFunctions.checkNegativeAnything(fractionOne);

	    assertEquals(-1, result.getCoefficient());
	    assertEquals(1, result.getNumerator());
	    assertEquals(2, result.getDenominator());
    }
    
    @Test
    public void testConstructor() {
        CalculatorFunctions dummy = new CalculatorFunctions();
    }
	
  @Test
  public void testCalculateAdd1() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(1, 3, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_PROPER);
     
      assertEquals(3, result.getCoefficient());
      assertEquals(1, result.getNumerator());
      assertEquals(4, result.getDenominator());
  }
  
  @Test
  public void testCalculateAddIrreducedImproper() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(1, 3, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.IRREDUCED_IMPROPER);
      
      assertEquals(0, result.getCoefficient());
      assertEquals(26, result.getNumerator());
      assertEquals(8, result.getDenominator());
  }
  
  @Test
  public void testCalculateAddIrreducedProper() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(1, 3, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.IRREDUCED_PROPER);
      
      assertEquals(3, result.getCoefficient());
      assertEquals(2, result.getNumerator());
      assertEquals(8, result.getDenominator());
  }
  
  @Test
  public void testCalculateAddReducedImproper() throws IOException {
      MixedFraction fraction1 = new MixedFraction(1, 1, 2);
      MixedFraction fraction2 = new MixedFraction(1, 3, 4);
      MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.ADD, FractionForm.REDUCED_IMPROPER);
      
      assertEquals(0, result.getCoefficient());
      assertEquals(13, result.getNumerator());
      assertEquals(4, result.getDenominator());
  }
  
  @Test
  public void testGreaterThan1() {
      MixedFraction fraction1 = new MixedFraction(12201, 2455, 3200);
      MixedFraction fraction2 = new MixedFraction(-199223, 15, 1040);
      boolean result = CalculatorFunctions.greaterThan(fraction1, fraction2);
      
      assertTrue(result == true);
  }
  
  @Test
  public void testGreaterThan2() {
      MixedFraction fraction1 = new MixedFraction(12201, 2455, 3200);
      MixedFraction fraction2 = new MixedFraction(199223, 15, 1040);
      boolean result = CalculatorFunctions.greaterThan(fraction1, fraction2);
      
      assertTrue(result == false);
  }
  
  @Test
  public void testGreaterThan3() {
      MixedFraction fraction1 = new MixedFraction(12201, 2455, 3200);
      MixedFraction fraction2 = new MixedFraction(12201, 2455, 3200);
      boolean result = CalculatorFunctions.greaterThan(fraction1, fraction2);
      
      assertTrue(result == false);
  }
  
  @Test
  public void testGreaterThan4() {
      MixedFraction fraction1 = new MixedFraction(312201, 2455, 3200);
      MixedFraction fraction2 = new MixedFraction(12201, 2455, 3200);
      boolean result = CalculatorFunctions.greaterThan(fraction1, fraction2);
      
      assertTrue(result == true);
  }
  
  @Test
  public void testLessThan1() {
      MixedFraction fraction1 = new MixedFraction(312201, 2455, 3200);
      MixedFraction fraction2 = new MixedFraction(12201, 2455, 3200);
      boolean result = CalculatorFunctions.lessThan(fraction1, fraction2);
      
      assertTrue(result == false);
  }
  
  @Test
  public void testLessThan2() {
      MixedFraction fraction1 = new MixedFraction(312201, 2455, 3200);
      MixedFraction fraction2 = new MixedFraction(12201, 2455, 3200);
      boolean result = CalculatorFunctions.lessThan(fraction1, fraction2);
      
      assertTrue(result == false);
  }
  
  @Test
  public void testLessThan3() {
      MixedFraction fraction1 = new MixedFraction(312201, 2455, 3200);
      MixedFraction fraction2 = new MixedFraction(322201, 3, 3200);
      boolean result = CalculatorFunctions.lessThan(fraction1, fraction2);
      
      assertTrue(result == true);
  }
  
  @Test
  public void testLessThan4() {
      MixedFraction fraction1 = new MixedFraction(12201, 2455, 3200);
      MixedFraction fraction2 = new MixedFraction(12201, 2455, 3200);
      boolean result = CalculatorFunctions.lessThan(fraction1, fraction2);
      
      assertTrue(result == false);
  }
  
  @Test
  public void testLessThan5() {
      MixedFraction fraction1 = new MixedFraction(-122110, 2432, 3220);
      MixedFraction fraction2 = new MixedFraction(12201, 2455, 3200);
      boolean result = CalculatorFunctions.lessThan(fraction1, fraction2);
      
      assertTrue(result == true);
  }
  
  @Test
  public void testLessThan6() {
      MixedFraction fraction1 = new MixedFraction(122110, 2432, 3220);
      MixedFraction fraction2 = new MixedFraction(-12221201, 2455, 3200);
      boolean result = CalculatorFunctions.lessThan(fraction1, fraction2);
      
      assertTrue(result == false);
  }
  
  @Test
  public void testEqualTo1() {
      MixedFraction fraction1 = new MixedFraction(21, 242, 245);
      MixedFraction fraction2 = new MixedFraction(100, 215, 420);
      boolean result = CalculatorFunctions.equalTo(fraction1, fraction2);
      
      assertTrue(result == false);
  }
  
  @Test
  public void testEqualTo2() {
      MixedFraction fraction1 = new MixedFraction(100, 215, 420);
      MixedFraction fraction2 = new MixedFraction(100, 215, 420);
      boolean result = CalculatorFunctions.equalTo(fraction1, fraction2);
      
      assertTrue(result == true);
  }
  
  @Test
  public void testEqualTo3() {
      MixedFraction fraction1 = new MixedFraction(103, 215, 420);
      MixedFraction fraction2 = new MixedFraction(100, 315, 720);
      boolean result = CalculatorFunctions.equalTo(fraction1, fraction2);
      
      assertTrue(result == false);
  }
  
  @Test
  public void testCalculateSubtractIrreducedImproper() throws IOException {
    MixedFraction fraction1 = new MixedFraction(1, 1, 4);
    MixedFraction fraction2 = new MixedFraction(1, 3, 4);
    MixedFraction result = CalculatorFunctions.calculate(fraction1, fraction2, Operation.SUBTRACT, FractionForm.IRREDUCED_IMPROPER);
    
    assertEquals(0, result.getCoefficient());
    assertEquals(-8, result.getNumerator());
    assertEquals(16, result.getDenominator());
  }
  
  @Test
  public void testSimplify1() throws IOException {
    MixedFraction fraction = new MixedFraction(1, 2, 4);
    
    MixedFraction result = CalculatorFunctions.simplify(fraction, FractionForm.REDUCED_PROPER);
        
    assertEquals(1, result.getCoefficient());
    assertEquals(1, result.getNumerator());
    assertEquals(2, result.getDenominator());
  }
  
  @Test
  public void testSimplify2() throws IOException {
    MixedFraction fraction = new MixedFraction(3, 12, 21);
    StringBuilder calcsteps = new StringBuilder();
    
    MixedFraction result = CalculatorFunctions.simplify(fraction, FractionForm.REDUCED_PROPER);
        
    assertEquals(3, result.getCoefficient());
    assertEquals(4, result.getNumerator());
    assertEquals(7, result.getDenominator());
  }
  
  @Test
  public void testSimplify3() throws IOException {
    MixedFraction fraction = new MixedFraction(201, 12, 216);
    
    MixedFraction result = CalculatorFunctions.simplify(fraction, FractionForm.REDUCED_PROPER);
        
    assertEquals(201, result.getCoefficient());
    assertEquals(1, result.getNumerator());
    assertEquals(18, result.getDenominator());
  }
  
	  
	    // Add more test cases as needed for edge cases and additional coverage.
  
  @Test
  public void testSetNegative() {
	    MixedFraction fraction = new MixedFraction(1, 1, 4);
	    
	    fraction.setNegative();
	    
	    assertEquals(-1, fraction.getCoefficient());
	    assertEquals(1, fraction.getNumerator());
	    assertEquals(4, fraction.getDenominator());
  	}
  
  @Test
  public void testSetNegative2() {
	    MixedFraction fraction = new MixedFraction(-5, 1, 5);
	    
	    fraction.setNegative();
	    
	    assertEquals(5, fraction.getCoefficient());
	    assertEquals(1, fraction.getNumerator());
	    assertEquals(5, fraction.getDenominator());
  	}
  
  @Test
  public void testSetExponent() {
	    MixedFraction fraction = new MixedFraction(-5, 1, 5);
	    
	    fraction.setExponent(3);
	    
	    assertEquals(-5, fraction.getCoefficient());
	    assertEquals(1, fraction.getNumerator());
	    assertEquals(5, fraction.getDenominator());
	    assertEquals(3, fraction.getExponent());
  	}
  
  @Test
  public void testIsNull() {
	    MixedFraction fraction = new MixedFraction(0, 0, 1);
	    
	    assertTrue(fraction.isNull());
  	}
  
  @Test
  public void testIsNull2() {
	    MixedFraction fraction = new MixedFraction(1, 0, 1);
	    
	    assertFalse(fraction.isNull());
  	}
  
  @Test
  public void testIsNull3() {
	    MixedFraction fraction = new MixedFraction(1, 1, 1);
	    
	    assertFalse(fraction.isNull());
  	}
  
  @Test
  public void testIsNull4() {
	    MixedFraction fraction = new MixedFraction(0, 0, 2);
	    
	    assertFalse(fraction.isNull());
  	}
  
  @Test
  public void testIsNull5() {
	    MixedFraction fraction = new MixedFraction(1, 0, 2);
	    
	    assertFalse(fraction.isNull());
  	}
  @Test
  public void testIsNull6() {
	    MixedFraction fraction = new MixedFraction(0, 2, 2);
	    
	    assertFalse(fraction.isNull());
  	}
  
  @Test
  public void testIsNull74() {
	    MixedFraction fraction = new MixedFraction(1, 1, 2);
	    
	    assertFalse(fraction.isNull());
  	}
  
  @Test
  public void testMFDefCon() {
	    MixedFraction fraction = new MixedFraction();
	  
	    assertTrue(fraction.isNull());
  	}
  
  // HERE BEGINS TESTS FOR EXPRESSION.JAVA
  @Test
  public void testExpression() {
	    MixedFraction mf1 = new MixedFraction(1, 1, 4);
	    MixedFraction mf2 = new MixedFraction(1, 1, 4);
	    MixedFraction ans = new MixedFraction(2, 1, 2);
	    
	    MixedFraction mf1New = new MixedFraction(2, 2, 4);
	    MixedFraction mf2New = new MixedFraction(2, 1, 4);
	    MixedFraction ansNew = new MixedFraction(4, 3, 4);


	  	Expression expression = new Expression(mf1, mf2, ans, Operation.ADD);
	  	
	  	assertEquals(expression.getLeftOp(), mf1);
	  	assertEquals(expression.getRightOp(), mf2);
	  	assertEquals(expression.getAnswer(), ans);
	  	assertEquals(expression.getOperator(), Operation.ADD);
	  	
	  	expression.setLeftOp(mf1New);
	  	expression.setRightOp(mf2New);
	  	expression.setAnswer(ansNew);
	  	expression.setOperator(Operation.SUBTRACT);

	  	assertEquals(expression.getLeftOp(), mf1New);
	  	assertEquals(expression.getRightOp(), mf2New);
	  	assertEquals(expression.getAnswer(), ansNew);
	  	assertEquals(expression.getOperator(), Operation.SUBTRACT);
  	}
  
  // HERE BEGINS TESTS FOR CURRENTMIXEDNUMBER.JAVA
  @Test
  public void testCurrentMixedNumber() {
	  	CurrentMixedNumber cmnNull = new CurrentMixedNumber();
	  	
	  	assertTrue(cmnNull.isNull());

	  	CurrentMixedNumber cmn = new CurrentMixedNumber(1, 1, 2);

	  	assertEquals(cmn.getFocus(), 0);
	  	
	  	cmn.setFocus(1);

	  	assertEquals(cmn.getFocus(), 1);
	  	
	  	CurrentMixedNumber cmnNew = new CurrentMixedNumber(2, 2, 5);
	  	
	  	cmn.setFraction(cmnNew);
	  	
	    assertEquals(2, cmn.getCoefficient());
	    assertEquals(2, cmn.getNumerator());
	    assertEquals(5, cmn.getDenominator());
	    
	    cmn.focusForward();
	    
	    assertEquals(cmn.getFocus(), 2);
	    	    
	    cmn.focusForward();

	    cmn.setNegative();	    
	    
	    assertEquals(cmn.getCoefficient(), -2);
	    
	    cmn.setNum(1);
	    
	    cmn.focusBackward();
	    cmn.focusBackward();
	    cmn.focusBackward();
	    cmn.focusBackward();
	    
	    cmn.setFocus(3);
	    
	    cmn.setNum(0);
	    
	    assertEquals(cmn.getFocus(), 3);
  	}
  
  @Test
  public void testDefaultConstructor() {
      Expression expression = new Expression();
      assertNull(expression.getLeftOp());
      assertNull(expression.getRightOp());
      assertNull(expression.getAnswer());
      assertNull(expression.getOperator());
  }

  @Test
  public void testParameterizedConstructor() {
      MixedFraction left = new MixedFraction(1, 2, 3);
      MixedFraction right = new MixedFraction(4, 5, 6);
      Operation operation = Operation.ADD;

      Expression expression = new Expression(left, right, operation);

      assertEquals(left, expression.getLeftOp());
      assertEquals(right, expression.getRightOp());
      assertNull(expression.getAnswer());
      assertEquals(operation, expression.getOperator());
  }


  }
  

