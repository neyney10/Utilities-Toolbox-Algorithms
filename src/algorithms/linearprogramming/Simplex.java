package algorithms.linearprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;



import utilities.Print;

/**
 * resource: https://www.math.ucla.edu/~tom/LP.pdf
 * @author neyne
 *
 */
public class Simplex {
	
	public enum opt {
			MAX,
			MIN
	}

	public static void addConstraint(String s) {
		// W.I.P
		HashSet<String> table = new HashSet<>();
		String[] symbols = s.split(" ");
		for (String symb : symbols) {
			table.add(symb);
		}
	}

	/**
	 * TEMP ONLY
	 * @param matrix
	 * @return
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
	 * Notes: Additional column for Z (objective function) and additional row for 0=obj-z, 
	 * and another column for 'b' - the right hand side of the equations.
	 * @param matrix
	 */
	public static double compute(double matrix[][], opt mode) {

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
	 * Using the Simplex method to find the pivot
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
	 * Status: W.I.P
	 * @param matrix
	 * @return
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



	private static void interchange(double matrix[][], int row, int column) // substituion.
	{// TODO: test for correctness.


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
