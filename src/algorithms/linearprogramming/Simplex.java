package algorithms.linearprogramming;

import java.util.Arrays;

import algorithms.linearprogramming.LPProblem.opt;
import utilities.Print;

/**
 * A class for solving Linear programming problems using the Simplex algorithm
 * The simplex is an method for solving optimization problems, let it be maximum or minimum optimization
 * problems, It is currently unknown if the running-time is polynomial, it's probably not.
 * The performance of the method can be greatly enhanced by different types of pivot choosing.
 * This class currently does not use any optimized or efficient way of choosing a pivot.
 * Note: this class is a Static class and does not contain any methods beside static functions.
 * RESOURCE: https://www.math.ucla.edu/~tom/LP.pdf
 * @author neyne
 *
 */
public class Simplex {
	
		private Simplex() {} // no use to construct a an instance of it.

	
	/**
	 * Computing a maximum or minmimum Linear programming problem with choosing a max or min pivot choosing 
	 * and interchanging accordingly.
	 * @param matrix : the Linear programming problem as the last row is equal to -(objective function).
	 * @param mode : enum opt - as the optimization type (minimum, maximum...)
	 * @return (double) as the maximum value to the problem. 
	 * Note: simply calling compute(lpp.constructLP(), lpp.getOptimizationType()).
	 */
	public static double compute(LPProblem lpp) {
		return compute(constructLP(lpp), lpp.getOptimizationType());
	}
	
	/**
	 * Computing a minimum Linear programming problem with choosing a min-pivot 
	 * and interchanging accordingly.
	 * @param the Linear programming problem as the last row is equal to -(objective function) - double matrix[][] 
	 * @return the minimum value found - double .
	 */
	public static double min_compute(double matrix[][]) {
		int pivot[];

		while((pivot = min_findPivot(matrix)) != null) {
			interchange(matrix, pivot[0], pivot[1]);

			// TEMP FOR TESTING
			Print.printMatrix(matrix);
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return matrix[matrix.length-1][matrix[0].length-1];
	}

	
	/**
	 * Computing a maximum Linear programming problem with choosing a max-pivot 
	 * and interchanging accordingly.
	 * @param matrix the Linear programming problem as the last row is equal to -(objective function). - double matrix[][] 
	 * @return maximum value found - double.
	 */
	public static double max_compute(double matrix[][])
	{
		int pivot[];

		while((pivot = findPivot(matrix)) != null) {
			interchange(matrix, pivot[0], pivot[1]);

			//B.put(pivot[0], pivot[1]);

			// TEMP FOR TESTING
			Print.printMatrix(matrix);
			System.out.println();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// find variable values x1,x2...xn
		//System.out.println(B.toString());

		return matrix[matrix.length-1][matrix[0].length-1];
	}

	/**
	 * Computing a maximum or minmimum Linear programming problem with choosing a max or min pivot choosing 
	 * and interchanging accordingly.
	 * @param matrix : the Linear programming problem as the last row is equal to -(objective function).
	 * @param mode : enum opt - as the optimization type (minimum, maximum...)
	 * @return (double) as the maximum value to the problem. 
	 * Notes: and additional row for 0=obj-z (hence -obj), 
	 * and another column for 'b' - the right hand side of the equations.
	 */
	private static double compute(double matrix[][], opt mode) {

		switch (mode) {
			case MAX:
				return max_compute(matrix);
			case MIN:
				return min_compute(matrix);
			default:
				return max_compute(matrix); //default
		}
		
	}
	
	
	/**
	 * Construct a linear programming matrix used for algorithms such as Simplex.
	 * Note: there are many ways to construct an LP matrix, this function currently use the
	 * matrix used in the resource: https://www.math.ucla.edu/~tom/LP.pdf
	 * @return LP matrix (each cell is double)
	 */
	private static double[][] constructLP(LPProblem lpp) {
		
		LPProblem.opt optimizationType = lpp.getOptimizationType();
		Equation objective 	   		   = lpp.getObjective(); 
		Equation constraints[] 		   = lpp.getConstraints();
		
		double matrix[][] = new double[constraints.length+1][objective.getVariables().length+1];
		
		for (int i = 0; i < matrix.length-1; i++) {
			Equation cons = constraints[i];
			matrix[i] = Arrays.copyOf(cons.getVariables(), objective.getVariables().length+1);
			matrix[i][matrix[i].length-1] =  cons.getB();
			
			if(optimizationType == LPProblem.opt.MAX && cons.getSign() == Equation.eqSign.GEQ
					|| optimizationType == LPProblem.opt.MAX && cons.getSign() == Equation.eqSign.GEQ) {
				// if not fit to a standard maximum problem where all inequalities are "<=", hence this one
				// is ">=" or if not fit to a standard minimum problem where all inequalities are ">=", hence this one
				// is "<=".
				
				ElementaryRowOperations.multiplyRow(matrix, i, -1);
			} 
		}
		
		// get all variables. TODO: make it support independent variables even those that are not 
		// in the objective function.
		double variables[] = objective.getVariables();

		// set the last row to be the (0 = z-obj), hence -obj.
		matrix[matrix.length-1] = Arrays.copyOf(variables, variables.length+1);
		ElementaryRowOperations.multiplyRow(matrix, matrix.length-1, -1);
		
		return matrix;
	}


	/**
	 * Using the Simplex method to find the pivot (max)
	 * @param matrix - the Simplex matrix
	 * @return int[2] of i,j position in the matrix of the pivot.
	 */
	private static int[] findPivot(double matrix[][]) {
		int rows = matrix.length;		// amount of rows.
		int cols = matrix[0].length; 	// amount of columns

		boolean feasible; 				// if feasibile
		double minRatio = Double.MAX_VALUE; // reset min
		int position[] = new int[2];

		for (int j = 0; j < cols-1; j++) { // columns
			double cellValueC = matrix[rows-1][j]; // last row
			feasible = true;

			if(cellValueC < 0) {
				feasible = false;

				for (int i = 0; i < rows-1; i++) { // rows
					double cellValue = matrix[i][j];
					double ratio = matrix[i][cols-1]/cellValue;

					if(cellValue > 0 && matrix[i][cols-1] >=0  // Case 1: if 'b' at row i is non-negative.
							|| cellValue < 0 && matrix[i][cols-1] < 0) { // Case 2: if 'b' at row i is negative.

						feasible = true;

						if( ratio < minRatio) {
							// found new minimum ratio -> update minRatio 
							minRatio = ratio;
							position[0] = i;
							position[1] = j;
						}
					} 
				}
			}

			if (!feasible) {
				return null; // THROW ERR EXCEPTION
			}
		}

		if (minRatio == Double.MAX_VALUE)
			return null;

		System.out.println(Arrays.toString(position));
		return position;
	}


	/**
	 * find pivot for the minimum optimization
	 * @param matrix
	 * @return int[2] of i,j position in the matrix of the pivot.
	 */
	private static int[] min_findPivot(double matrix[][]) {
		// TODO: optimize / clean code / re-factor
		int rows = matrix.length;		// amount of rows.
		int cols = matrix[0].length; 	// amount of columns

		boolean feasible = false; 				// if feasible
		double minRatio = Double.MAX_VALUE; // reset min - need to be closest to zero.
		int position[] = new int[2];

		int CASE = 1;
		// check if case 1 or case 2
		for (int j = 0; j < matrix[0].length; j++) {
			if(matrix[rows-1][j] < 0) {
				CASE = 2;
				break;
			}
		}

		// case 1
		if (CASE == 1)
			for (int i = 0; i < rows-1; i++) { // rows
				double cellValueB = matrix[i][cols-1]; // last column - b
				feasible = true;

				if(cellValueB < 0) {
					feasible = false;

					for (int j = 0; j < cols-1; j++) { // columns
						double cellValue = matrix[i][j];
						double ratio = matrix[rows-1][j]/cellValue;

						if(cellValue < 0) {
							feasible = true;

							if( Math.abs(ratio) < minRatio) {
								// found new minimum ratio -> update minRatio 
								minRatio = Math.abs(ratio);
								position[0] = i;
								position[1] = j;
							}
						} 
					}
				}

			}

		// case 2
		// some cj are negative - TODO: optimize, check if there are negative values in the first place.
		if (CASE == 2)
			for (int j = 0; j < cols-1; j++) { // columns
				double cellValueC = matrix[rows-1][j]; // last row
				feasible = true;

				if(cellValueC != 0) {
					feasible = false;

					for (int i = 0; i < rows-1; i++) { // rows
						double cellValue = matrix[i][j];
						double ratio = matrix[rows-1][j]/cellValue;

						if(cellValue > 0 && cellValueC < 0 
								|| cellValue < 0 && cellValueC > 0) { 

							feasible = true;

							if( Math.abs(ratio) < minRatio) {
								// found new minimum ratio -> update minRatio 
								minRatio = Math.abs(ratio);
								position[0] = i;
								position[1] = j;


							}
						} 
					}
				}

				if (!feasible) {
					return null; // THROW ERR EXCEPTION
				}
			}

		if (minRatio == Double.MAX_VALUE)
			return null;

		System.out.println(Arrays.toString(position));
		return position;
	}



	/**
	 * Apply pivot rules to the matrix and change it accordingly. 	<br>
	 * Let i be the pivot's row, and j the pivot's column, then		<br>
	 * every cell in the row 'i' is divided by the pivot's value.	<br>
	 * Example: pivot = 5, the other cell value is 10, then the new value is 10/5 = 2.	<br>
	 * M(i,k) where k!=j, M(i,k) /= Pivot.	<br>
	 * Every cell in the column 'j' is divided by the -(pivot's value) (negative)	<br>
	 * Hence M(k,j) where k!=i, M(k,j) /= -(Pivot).	<br>
	 * Any other cell which is not in the row 'i' or the column 'j', is calculated:	<br>
	 * M(k,v) where k,v!=i,j, M(k,v) -= [(M(i,v)*M(k,j))/Pivot]	<br>
	 * @param matrix
	 * @param row - pivot's row
	 * @param column - pivot's column
	 */
	private static void interchange(double matrix[][], int row, int column) // substituion.
	{

		// Get devision factor
		double factor = matrix[row][column];

		// The pivot element x is inverted to x^(-1)
		matrix[row][column] = 1/matrix[row][column];

		// Calculate the rest of the matrix. (outside of the pivot column and row)
		// TODO: optimize
		for (int i = 0; i < matrix.length; i++) { // rows
			if (i == row)
				continue;

			for (int j = 0; j < matrix[0].length; j++) { // columns
				if (j == column)
					continue;

				matrix[i][j] -= ( (matrix[i][column]*matrix[row][j])/factor );
			}
		}

		// All elements in the same row are divided by the factor
		for (int j = 0; j < matrix[0].length; j++) {
			if ( j == column ) // if pivot element.
				// don't divide the Pivot Element.
				continue;

			matrix[row][j] /=  factor;
		}

		// All elements in the same column are divided by -(factor)
		for (int i = 0; i < matrix.length; i++) {
			if ( i == row ) // if pivot element.
				// don't divide the Pivot Element.
				continue;

			matrix[i][column] /=  -factor;
		}


	}



}
