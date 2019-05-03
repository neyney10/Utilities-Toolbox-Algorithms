package algorithms.linearprogramming;

import utilities.Print;


public class GaussEliminiation {

	public static void main(String[] args) { // TEMP ONLY , TODO: delete
		double matrix[][] = { // last column is vector 'b', the RIGHT HAND SIDE of the equations.
				{2,1,0,2},
				{4,3,1,3},
				{2,4,0,4},
		};

		System.out.println("Before: ");
		Print.printMatrix(matrix);
		System.out.println("After: ");
		double x[] = GaussEliminationSolver(matrix);
		Print.printMatrix(matrix);
		System.out.println("Solutions: (x1,x2,x3)");
		Print.printArray(x);
		System.out.println();

	}

	



	public static void SingleGaussElimination(double matrix[][], int i, int j)
	{
		for (int k = i+1; k < matrix.length; k++) {
			if ( matrix[i][j] == 0 || matrix[k][j] == 0)
				continue; // SKIP - TODO: fix, this is temporal condition.
			double factor = matrix[k][j]/matrix[i][j];
			ElementaryRowOperations.substractRow(matrix, i, k, factor);
		}
	}


	/**
	 * +1 more column for vector 'b', RHS, later on will support multiple 'b' vectors a.k.a a second matrix.
	 * @param matrix
	 */
	public static double[] GaussEliminationSolver(double matrix[][])
	{
		//TODO: put zero-rows at the bottom.
		for (int i = 0; i < matrix.length-1; i++) { // ROWS
			// find leading coefficient.
			int leadCoeffColumn = findLeadCoeffPosition(matrix, i);

			if(leadCoeffColumn == matrix[i].length-1) // if no leading coefficient found, its a zeroed-row
				continue; // skip

			SingleGaussElimination(matrix, i, leadCoeffColumn);
		}

		// find solutions, if any.
		double x[] = new double[matrix[0].length-1]; // variables.
		for (int i = matrix.length-1; i >= 0; i--) { // ROWS,
			// TODO: Currently assumes the matrix is size of NxN, make it support NxM. 
			// TODO: check where the leading coeff is.
			int leadCoeffColumn = findLeadCoeffPosition(matrix, i);
			for (int k = matrix[i].length-2; k > leadCoeffColumn; k--) {
				x[i] += matrix[i][k]*x[k];
			}
			x[i] = (matrix[i][matrix[i].length-1]-x[i])/matrix[i][leadCoeffColumn];

		}

		return x;
	}
	
	private static int findLeadCoeffPosition(double matrix[][], int row)
	{
		int leadCoeffColumn = 0;
		while(leadCoeffColumn < matrix[row].length-1 && matrix[row][leadCoeffColumn] == 0)
			// advance as long as there are zeroes
			leadCoeffColumn++;
		
		return leadCoeffColumn;
	}




}