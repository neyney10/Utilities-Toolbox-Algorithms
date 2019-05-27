package algorithms.linearprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import algorithms.linearprogramming.LPProblem.opt;
import utilities.Print;

// Note: this is supposed to be a refactor to the original Simplex class
// This would be more tightly follow the notes in the pdf.

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
public class Simplex2 extends Simplex {

	protected Simplex2() {} // no use to construct an instance of it.


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
	 * Computing a maximum Linear programming problem with choosing a max-pivot 
	 * and interchanging accordingly.
	 * @param matrix the Linear programming problem as the last row is equal to -(objective function). - double matrix[][] 
	 * @return maximum value found - double.
	 */
	public static double max_compute(double matrix[][])
	{
		int dependentVar[] = new int[matrix.length-1];
		int independentVar[]= new int[matrix[0].length-1];
		int pivot[];
		
		setupVectors(dependentVar,independentVar);

		while((pivot = findPivot(matrix)) != null) {
			interchange(matrix, pivot[0], pivot[1]);
			
			int temp = dependentVar[pivot[0]];
			dependentVar[pivot[0]] = independentVar[pivot[1]];
			independentVar[pivot[1]] = temp;

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
		double[][] solutions = getSolutions(matrix, dependentVar, independentVar);
		System.out.println("Solutions:");
		Print.printMatrix(solutions);

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
	protected static double compute(double matrix[][], opt mode) {

		switch (mode) {
		case MAX:
			return max_compute(matrix);
			//case MIN:
			//return //min_compute(matrix);
		default:
			return max_compute(matrix); //default
		}

	}
	
	private static void setupVectors(int dependent[], int independent[]) {
		int i = 0;
		
		for (; i < independent.length; i++) {
			independent[i] = i;
		}
		
		for (; (i-independent.length) < dependent.length; i++) {
			dependent[(i-independent.length)] = i;
		}
	}
	
	private static double[][] getSolutions(double matrix[][], int dependent[],int independent[]) {
		int cols = matrix[0].length;
		double solutions[][] = new double[independent.length][2];
		
		int k = 0;
		
		for (int i = 0; i < dependent.length; i++) {
			if(dependent[i] < independent.length) {
				solutions[k][0] = dependent[i];
				solutions[k][1] = matrix[i][cols-1];
				++k;
			}
		}
		
		return solutions;
	}


	/**
	 * Using the Simplex method to find the pivot (max)
	 * @param matrix - the Simplex matrix
	 * @return int[2] of i,j position in the matrix of the pivot.
	 */
	protected static int[] findPivot(double matrix[][]) {

		int caseType = getCaseType(matrix);
		
		switch (caseType) {
		case 1:
			return findPivotCase1(matrix);
		case 2:
			return findPivotCase2(matrix);
		}
		
		return null;
	}


	private static int[] findPivotCase1(double[][] matrix) {
		int rows = matrix.length;		// amount of rows.
		int cols = matrix[0].length; 	// amount of columns

		boolean feasible = true; 			// if feasible
		boolean finished = true; 			// if the simplex method done working, hence, all c's are non-negative.	
		double minRatio = Double.MAX_VALUE; // reset min
		int position[] = new int[2];

		for (int j = 0; j < cols-1; j++) { // columns
			double cellValueC = matrix[rows-1][j]; // last row

			if(cellValueC < 0) {
				finished = false; // hence we found a nonnegative 'c' 
				feasible = false; // until we found some a_ij > 0.

				for (int i = 0; i < rows-1; i++) { // rows
					double cellValue = matrix[i][j];

					if(cellValue > 0) { // a_ij > 0 
						double cellValueB = matrix[i][cols-1];
						double ratio = cellValueB/cellValue;
						feasible = true;

						if(ratio < minRatio) {
							minRatio = ratio;
							position[0] = i;
							position[1] = j;
						}
					}
				}
			}

			if (!feasible) {
				return null; // THROW ERR EXCEPTION or somethin.
			}
		}

		if (finished)
			return null;

		System.out.println("Case 1: "+Arrays.toString(position)); // DEBUG ONLY. TODO: remove.
		return position;
	}

	private static int[] findPivotCase2(double[][] matrix) {
		int rows = matrix.length;		// amount of rows.
		int cols = matrix[0].length; 	// amount of columns

		boolean feasible = true; 			// if feasible
		boolean finished = false; 			// if the simplex method done working, hence, all c's are non-negative.
		//boolean foundFirstNegB = false;		// find first bi where bi < 0 and denote it as dk.... (see pdf).
		double minRatio = Double.MAX_VALUE; // reset min
		int position[] = new int[2];

		for (int i = 0; i < rows-1; i++) { // columns
			double cellValueB = matrix[i][cols-1]; // last row

			if(cellValueB < 0) {
				feasible = false; // until we find an a_kj < 0
				
				for (int j = 0; j < cols-1; j++) {
					double cellValue = matrix[i][j];
					
					if(cellValue < 0) {
						feasible = true;
						
						for (int k = 0; k < rows-1; k++) {
							cellValue = matrix[k][j];
							cellValueB = matrix[k][cols-1];
							double ratio = cellValueB/cellValue;
							
							if(k == i || cellValue > 0)
								if(ratio < minRatio) {
									minRatio = ratio;
									position[0] = k;
									position[1] = j;
								}
						}
					}
				}
				if(!feasible)
					return null; // return ERR EXCEPTION or somethin.
				break; // found the first negative 'b'.
			} 
		}

		if (finished)
			return null;

		System.out.println("Case 2: "+Arrays.toString(position)); // DEBUG ONLY. TODO: remove.
		return position;
	}

	/**
	 * There are 2 cases for the simplex method.
	 * Case 1: all bi's are non-negative (last column in the matrix).
	 * Case 2: some bi's are negative.
	 * @param matrix - double[][] the simplex tableau.
	 * @return case type number ([int] 1 or 2)
	 */
	private static int getCaseType(double matrix[][]) {
		int lastColIndex = matrix[0].length-1;

		for (int i = 0; i < matrix.length; i++) {
			if(matrix[i][lastColIndex] < 0)
				// found a negative b, then it's case 2.
				return 2;
		}

		// not found any negative b, then it's case 1.
		return 1;
	}







}
