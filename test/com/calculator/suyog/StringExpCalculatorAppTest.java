package com.calculator.suyog;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class StringExpCalculatorAppTest {

	@Test
	void testPositiveScenario() {
		TestCase.assertEquals(StringExpCalculatorApp.evaluate("7+(6*5^2+3-4/2)"), "158");
	}

	@Test
	void testNegativeScenario1() {
		TestCase.assertEquals(StringExpCalculatorApp.evaluate("7+(6*+3-4/2)"), "INVALID EXPRESSION");
	}

	@Test
	void testNegativeScenario2() {
		TestCase.assertEquals(StringExpCalculatorApp.evaluate("7+(6/0-4/2)"), "INVALID EXPRESSION");
	}
	
	@Test
	void testNegativeScenario3() {
		TestCase.assertEquals(StringExpCalculatorApp.evaluate("(3+2"), "INVALID EXPRESSION");
	}
	
	@Test
	void testNegativeScenario4() {
		TestCase.assertEquals(StringExpCalculatorApp.evaluate("(3+asda"), "INVALID EXPRESSION");
	}
	
	@Test
	void testNegativeScenario5() {
		TestCase.assertEquals(StringExpCalculatorApp.evaluate("(3+6-4-3%8"), "INVALID EXPRESSION");
	}

}
