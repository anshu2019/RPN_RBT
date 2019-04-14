import java.math.BigInteger;
import java.util.Scanner;

/**
 * This class performs Reverse Polish Notation Calculator
 * 
 * @author Anshu Anand
 *
 */
public class ReversePolishNotation {

	private static Scanner userInput;
	private static RedBlackTree rb = new RedBlackTree();
	private static RedBlackNode nill = RedBlackTree.nil;

	/**
	 * This is main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("----------------------------------------------------");
		System.out.println("java ReversePolishNotation ");
		System.out.println("Please enter your options below with space sepearted. Press Enter to execute:");
		showMenu();

	}

	/**
	 * This method shows command line input prompt
	 * @Precondition:None
	 * @Postcondition:Command line option is provided to enter input
	 */
	private static void showMenu() {
		userInput = new Scanner(System.in);
		String input;
		String output;

		input = userInput.nextLine();
		if (!input.isEmpty() && input != null) {
			String[] data = input.split("\\s+");
			output = doMaths(data);
		} else {
			System.out.println("Terminating, No input provided,Try again ");
		}

	}

	/**
	 * @Precondition:User input is taken on command line
	 * @Postcondition: mathematical operation is performed
	 * This method performs all the maths operations
	 */
	private static String doMaths(String[] input) {
		Stack st = new Stack();

		String operators = "+-*/%~=#";
		String out = "";
		BigInteger output = new BigInteger("0");

		for (String s : input) {
			if (!operators.contains(s)) {
				st.push(s);

			} else {
				int index = operators.indexOf(s);
				switch (index) {
				case 0:
					String b = getValidValue(st.pop().toString());
					String a = getValidValue(st.pop().toString());
					out = add(a, b);
					st.push(out);
					continue;

				case 1:
					String b1 = getValidValue(st.pop().toString());
					String a1 = getValidValue(st.pop().toString());
					out = subtract(a1, b1);
					st.push(out);
					continue;

				case 2:
					String b2 = getValidValue(st.pop().toString());
					String a2 = getValidValue(st.pop().toString());
					out = multiply(a2, b2);
					st.push(out);
					continue;

				case 3:
					String b3 = getValidValue(st.pop().toString());
					String a3 = getValidValue(st.pop().toString());
					out = divide(a3, b3);
					st.push(out);
					continue;

				case 4:
					String b4 = getValidValue(st.pop().toString());
					String a4 = getValidValue(st.pop().toString());
					out = module(a4, b4);
					st.push(out);
					continue;

				case 5:
					String a5 = getValidValue(st.pop().toString());
					out = unryMinus(a5);
					st.push(out);
					continue;

				case 6:
					String b7 = st.pop().toString();
					BigInteger nval = new BigInteger(b7);
					String a7 = st.pop().toString();

					if (a7.matches("[a-zA-Z].*")) {
						RedBlackNode n = new RedBlackNode(a7, nval, 1, nill, nill, nill);
						rb.insert(n);
					} else {
						System.out.println(
								"Exception in thread \"main\" java.lang.Exception: error:" + a7 + " not an lvalue");
						System.exit(0);
					}
					st.push(a7);
					out = getValidValue(a7);
					continue;
				case 7:
					try {
						String c6 = getValidValue(st.pop().toString());
						String b6 = getValidValue(st.pop().toString());
						String a6 = getValidValue(st.pop().toString());
						out = modPow(a6, b6, c6);
						st.push(out);
						continue;
					} catch (Exception e) {
						System.out.println(
								"Exception in thread \"main\" java.lang.Exception: error: stack underflow exception");
						System.exit(0);
					}
				default:
					System.out.println("gtest");
					break;
				}
			}
		}
		out = getValidValue(st.pop().toString());
		System.out.println("Output: " + out);
		showMenu();
		return out;

	}

	/**
	 * This method adds two values
	 * @Precondition:User input is taken on command line
	 * @Postcondition:returns addition result
	 * @param i
	 * @param j
	 * @return
	 */
	private static String add(String i, String j) {
		BigInteger a = new BigInteger(i);
		BigInteger b = new BigInteger(j);
		BigInteger res;
		res = a.add(b);
		return res.toString();
	}

	/**
	 * This method performs subtraction of two values
	 * @Precondition:User input is taken on command line
	 * @Postcondition:Subtraction result is return
	 * @param i
	 * @param j
	 * @return
	 */
	private static String subtract(String i, String j) {
		BigInteger a = new BigInteger(i);
		BigInteger b = new BigInteger(j);
		BigInteger res;
		res = a.subtract(b);
		return res.toString();
	}

	/**
	 * This method divides two values
	 * @Precondition:User input is taken on command line
	 * @Postcondition:Division result is return
	 * @param i
	 * @param j
	 * @return
	 */
	private static String divide(String i, String j) {
		BigInteger a = new BigInteger(i);
		BigInteger b = new BigInteger(j);
		BigInteger res;
		res = a.divide(b);
		return res.toString();
	}

	/**
	 * This method performs modulous operation
	 * @Precondition:User input is taken on command line
	 * @Postcondition:return the result of modulo operation
	 * @param i
	 * @param j
	 * @return
	 */
	private static String module(String i, String j) {
		BigInteger a = new BigInteger(i);
		BigInteger b = new BigInteger(j);
		BigInteger res;
		res = a.mod(b);
		return res.toString();
	}

	/**
	 * this method multiplies two numbers
	 * @Precondition:User input is taken on command line
	 * @Postcondition:result of multiplication is returned
	 * @param i
	 * @param j
	 * @return
	 */
	private static String multiply(String i, String j) {
		BigInteger a = new BigInteger(i);
		BigInteger b = new BigInteger(j);
		BigInteger res;
		res = a.multiply(b);
		return res.toString();
	}

	/**
	 * This method does power modulo operation on values
	 * @Precondition:User input is taken on command line
	 * @Postcondition:retruns the output of modPow operation
	 * @param i
	 * @param j
	 * @param k
	 * @return
	 */
	private static String modPow(String i, String j, String k) {
		BigInteger a = new BigInteger(i);
		BigInteger b = new BigInteger(j);
		BigInteger c = new BigInteger(k);
		BigInteger res;
		res = a.modPow(b, c);
		return res.toString();
	}

	/**
	 * This is unary operator
	 * @Precondition:User input is taken on command line
	 * @Postcondition:returns the result of unry operations
	 * @param i
	 * @return
	 */
	private static String unryMinus(String i) {
		BigInteger a = new BigInteger(i);
		BigInteger res;
		res = BigInteger.ZERO.subtract(a);
		return res.toString();
	}

	/**
	 * This method retrieves the value of the given key from ReBlack tree 
	 * @Precondition:User input is taken on command line
	 * @Postcondition: return the value for given variable from RedBlact tree 
	 * @param s
	 * @return
	 */
	private static String getValidValue(String s) {
		String res = "";
		if (s.matches("[a-zA-Z].*")) {
			RedBlackNode node = rb.lookUp(s, rb.getRoot());
			if (node != null && node != nill) {
				res = node.val.toString();
			} else {
				System.out.println("Exception in thread \"main\" java.lang.Exception: error: no variable " + s);
				System.exit(0);
			}
		} else {
			res = s;
		}
		return res;
	}
}
