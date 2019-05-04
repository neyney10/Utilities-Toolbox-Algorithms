package algorithms.linearprogramming;

import java.util.HashSet;

/**
 * Equations of the form + c1x1 + c2x2... = b, this is an equality form, although this class
 * supports inequalities as well '<=' and '>=' but not '<' or '>' just yet.
 * @author neyne
 *
 */
 public class Equation {

	private enum eqSign { //equation sign
		EQ,
		GEQ,
		LEQ
	}


	private double x[]; 	// variables.
	private eqSign sign;
	private double b; 		// Right hand side, literal/primitive number.

	/**
	 * Note: sign value must take precedence the variable and coeff.
	 * @param equation
	 */
	public Equation(String equation) {
		// example form: + 3x + 4y + 8z = 25
		String[] symbols = equation.split(" ");
			
		x = new double[symbols.length/2 -1];
		
		int Ivar = 0; // index var of the x[] array.
		
		for (int i = 1; i < symbols.length-2; i+=2) {
			if((symbols[i].length() == 1) && (symbols[i].equals("+") || symbols[i].equals("-")))
				continue;

			double coeff = 0;
			int coeffSign = 1;
			coeffSign = (symbols[i-1].equals("+"))? 1 : -1;

			if((symbols[i].length() == 1))  // if coeff is 1
				coeff = 1;
			else 	
				coeff = Double.parseDouble(symbols[i].substring(0, symbols[i].length()-1));

			coeff *= coeffSign;
			x[Ivar++] = coeff;
		}
		
		switch (symbols[symbols.length-2]) {
		case "=":
			sign = eqSign.EQ;
			break;
		case ">=":
			sign = eqSign.GEQ;
			break;
		case "<=":
			sign = eqSign.LEQ;
			break;
		}
		
		b = Double.parseDouble(symbols[symbols.length-1]);
	}

	

	/**
	 * @return the x[]
	 */
	public double[] getVariables() {
		return x;
	}


	/**
	 * @return the sign
	 */
	public eqSign getSign() {
		return sign;
	}


	/**
	 * @return the b
	 */
	public double getB() {
		return b;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		char var = 'a';
		for (int i = 0; i < x.length-1; i++) {
			sb.append(Double.toString(x[i]) + (var++) + " + ");
		}

		sb.append(Double.toString(x[x.length-1]) + var + " ");

		String equationSignStr = null;

		switch (sign) {
		case EQ:
			equationSignStr = "=";
			break;
		case GEQ:
			equationSignStr = ">=";
			break;
		case LEQ:
			equationSignStr = "<=";
			break;
		}

		sb.append(equationSignStr);
		sb.append(" " + Double.toString(b));

		return sb.toString();
	}
}
