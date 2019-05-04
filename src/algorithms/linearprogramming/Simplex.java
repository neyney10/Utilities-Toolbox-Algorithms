package algorithms.linearprogramming;

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

	public static void addConstraint(String s) {
		// W.I.P
		HashSet<String> table = new HashSet<>();
		String[] symbols = s.split(" ");
		for (String symb : symbols) {
			table.add(symb);
		}
	}

	/**
	 * Notes: Additional column for Z (objective function) and additional row for 0=obj-z, 
	 * and another column for 'b' - the right hand side of the equations.
	 * @param matrix
	 */
	public static double compute(double matrix[][]) {
		int pivot[];
		while((pivot = findPivot(matrix)) != null) {
			interchange(matrix, pivot[0], pivot[1]);

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
		
		return matrix[matrix.length-1][matrix[0].length-1];
	}

	private static int[] findPivot(double matrix[][]) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		boolean feasible = false;
		double minRatio = Double.MAX_VALUE;
		int position[] = new int[2];

		for (int j = 0; j < cols-1; j++) { // columns
			double cellValueC = matrix[rows-1][j]; // last row
			feasible = true;

			if(cellValueC < 0) {
				feasible = false;
				
				for (int i = 0; i < rows-1; i++) { // rows
					double cellValue = matrix[i][j];
					double ratio = matrix[i][cols-1]/cellValue;
					
					if(cellValue > 0) {
						feasible = true;
						if( ratio < minRatio) {
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
