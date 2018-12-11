package com.calculator.suyog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class StringExpCalculatorApp {

	public static String evaluate(String expression) {
		try {
			char[] expTokens = expression.replaceAll(" ", "").toCharArray();

			Stack<Integer> digitsStack = new Stack<Integer>();
			Stack<Character> operatorsStack = new Stack<Character>();

			for (int i = 0; i < expTokens.length; i++) {
				if (expTokens[i] >= '0' && expTokens[i] <= '9') {
					StringBuffer sbuf = new StringBuffer();
					int j = i;
					while (j < expTokens.length && expTokens[j] >= '0' && expTokens[j] <= '9')
						sbuf.append(expTokens[j++]);
					digitsStack.push(Integer.parseInt(sbuf.toString()));
					i = j - 1;
				}

				else if (expTokens[i] == '(')
					operatorsStack.push(expTokens[i]);

				else if (expTokens[i] == ')') {
					while (operatorsStack.peek() != '(')
						digitsStack.push(calculateForOp(operatorsStack.pop(), digitsStack.pop(), digitsStack.pop()));
					operatorsStack.pop();
				}

				else if (expTokens[i] == '+' || expTokens[i] == '-' || expTokens[i] == '*' || expTokens[i] == '/'
						|| expTokens[i] == '^') {
					while (!operatorsStack.empty() && hasPrecedence(expTokens[i], operatorsStack.peek()))
						digitsStack.push(calculateForOp(operatorsStack.pop(), digitsStack.pop(), digitsStack.pop()));
					operatorsStack.push(expTokens[i]);
				}
			}
			while (!operatorsStack.empty())
				digitsStack.push(calculateForOp(operatorsStack.pop(), digitsStack.pop(), digitsStack.pop()));
			return digitsStack.pop().toString();
		} catch (Exception e) {
			return "INVALID EXPRESSION";
		}
	}

	private static boolean hasPrecedence(char op1, char op2) {
		if ((op2 == '(' || op2 == ')') || ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
				|| ((op1 == '^') && (op2 == '*' || op2 == '/' || op2 == '+' || op2 == '-')))
			return false;
		else
			return true;
	}

	private static int calculateForOp(char op, int b, int a) throws Exception {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		case '/':
			return a / b;
		case '^':
			return pow(a, b);
		}
		throw new Exception();
	}

	private static int pow(int a, int b) {
		int temp = a;
		while (b > 1) {
			temp = temp * a;
			b--;
		}
		return temp;
	}

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out.print("Enter number of expressions to evaluate : ");
			int expCount = Integer.parseInt(br.readLine());
			ArrayList<String> expList = new ArrayList<>();
			while (expCount > 0) {
				expList.add(br.readLine());
				expCount--;
			}
			for (int i = 0; i < expList.size(); i++) {
				System.out.println("CASE #" + (i + 1) + ": " + StringExpCalculatorApp.evaluate(expList.get(i)));
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println();
		}
	}
}
