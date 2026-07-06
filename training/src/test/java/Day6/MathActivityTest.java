package Day6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class MathActivityTest {
	
	/**
	 * Addition should handle whole numbers.
	 * 
	 * Compare returned value of add(1, 1) with 2.
	 * */
	@Test
	void exec001() {
		assertEquals(MathActivity.add(1, 1), 2);
	}
	
	/**
	 * Subtraction should handle whole numbers.
	 * 
	 * Compare returned value of subtract(2, 1) with 1.
	 */
	@Test
	void exec002() {
		assertEquals(MathActivity.subtract(2, 1), 1);
	}
	
	/**
	 * Multiplication should handle whole numbers.
	 * 
	 * Compare returned value of multiply(2, 2) with 4.
	 */
	@Test
	void exec003() {
		assertEquals(MathActivity.multiply(2, 2), 4);
	}
	
	/**
	 * Divide should handle whole numbers.
	 * 
	 * Compare returned value of divide(4, 2) with 2.
	 */
	@Test
	void exec004() {
		assertEquals(MathActivity.divide(4,  2), 2);
	}
	
	/**
	 * Divide should error at division by 0.
	 * 
	 * Compare exception thrown at zero division.
	 * @throws ArithmeticException
	 */
	@Test
	void exec005() throws ArithmeticException {
		Executable divideByZero = () -> MathActivity.divide(1, 0);
		assertThrows(ArithmeticException.class, divideByZero);
	}
	
	/**
	 * Addition should handle negative integers.
	 * 
	 * Compare add(-2, 1) and -1.
	 */
	@Test
	void exec006() {
		assertEquals(MathActivity.add(-2, 1), -1);
	}

	/**
	 * Subtraction should handle negative results.
	 * 
	 * Compare subtract(1, 2) and -1.
	 */	
	@Test
	void exec007() {
		assertEquals(MathActivity.subtract(1, 2), -1);
	}
	
	/**
	 * Multiplication should handle negative integers.
	 * 
	 * Compare multiply(-2, 2) and -4.
	 */	
	@Test
	void exec008() {
		assertEquals(MathActivity.multiply(-2, 2), -4);
	}

	/**
	 * Division should handle negative results.
	 * 
	 * Compare divide(-1, 2) and -0.5.
	 */	
	@Test
	void exec009() {
		assertEquals(MathActivity.divide(-1, 2), -0.5);
	}
	
	/**
	 * Multiplication by zero should return zero.
	 * 
	 * Compare multiply(5, 0) and 0.
	 */
	@Test
	void exec010() {
		assertEquals(MathActivity.multiply(5, 0), 0);
	}
	
	/**
	 * MathActivity constructor must be public.
	 */
	@Test
	void exec011() {
		assertNotNull(new MathActivity());
	}
}
