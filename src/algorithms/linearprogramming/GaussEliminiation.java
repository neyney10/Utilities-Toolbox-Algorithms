package algorithms.linearprogramming;

import utilities.Print;


public class GaussEliminiation {

	public static void main(String[] args) { // TEMP ONLY , TODO: delete
//		double matrix[][] = { // last column is vector 'b', the RIGHT HAND SIDE of the equations.
//				{2,1,0,2},
//				{4,3,1,3},
//				{2,4,0,4},
//		};

//		double matrix[][] = { // last column is vector 'b', the RIGHT HAND SIDE of the equations.
//				{3,1,0,2,3},
//				{1,0,1,-1,3},
//				{0,-2,0,1,0},
//				{1,0,-3,1,-5}
//		};

				double matrix[][] = { // last column is vector 'b', the RIGHT HAND SIDE of the equations.
						{0,0,0,2},
						{4,2,1,3},
						{0,4,0,4},
				};
		//arrangeMatrix(matrix, 0);
		System.out.println("rank: "+rankOfEchelonMatrix(matrix));

		System.out.println("Before: ");
		Print.printMatrix(matrix);
		System.out.println("After: ");
		double x[] = GaussEliminiation.compute(matrix);
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
	public static double[] compute(double matrix[][])
	{
		//TODO: put zero-rows at the bottom.
		for (int i = 0; i < matrix.length-1; i++) { // ROWS
			//Arrange matrix
			arrangeMatrix(matrix, i);
			// find leading coefficient.
			int leadCoeffColumn = findLeadCoeffPosition(matrix, i);

			if(leadCoeffColumn == -1) // if no leading coefficient found, its a zeroed-row
				continue; // skip

			SingleGaussElimination(matrix, i, leadCoeffColumn);
		}

		double x[] = new double[matrix[0].length-1]; // variables.

		int rank = rankOfEchelonMatrix(matrix);
		if(rank < matrix[0].length-1) { // if not regular - inf+ solutions
			// TODO: make function to generate a solution for this kind of scenario.
			for (int r = x.length-1; r >= rank; r--) 
				x[r] = Double.MAX_VALUE;
		}

		// find solutions, if any.

		for (int i = matrix.length-1; i >= 0; i--) { // ROWS,
			// TODO: Currently assumes the matrix is size of NxN, make it support NxM. 

			int leadCoeffColumn = findLeadCoeffPosition(matrix, i);
			if(leadCoeffColumn == -1) // TODO: remove this line after using the rankCheck of echelon matrix
				continue; 
			for (int k = matrix[i].length-2; k > leadCoeffColumn; k--) {
				x[leadCoeffColumn] += matrix[i][k]*x[k];
			}
			x[leadCoeffColumn] = (matrix[i][matrix[i].length-1]-x[leadCoeffColumn])/matrix[i][leadCoeffColumn];

		}

		return x;
	}

	/**
	 * Arranging the matrix's rows in descending order. (by leading coeffient position)
	 * @param matrix
	 * @param startingRow
	 * @return
	 */
	private static void arrangeMatrix(double matrix[][], int startingRow)
	{// TODO: OPTIMIZE.

		for (int r = startingRow; r < matrix.length-1;/*Manual advance*/) { // TODO: change to a while loop maybe?
			Boolean switched = false;
			int currRowLC = findLeadCoeffPosition(matrix,r); // LC = Leading coefficient.

			if(currRowLC != -1) {
				// if not zeroed
				for (int j = r+1; j < matrix.length; j++) {
					int otherRowLC = findLeadCoeffPosition(matrix,j); 

					if ( otherRowLC != -1 && otherRowLC < currRowLC ) {
						ElementaryRowOperations.switchRows(matrix, r, j);
						switched = true;
						break;
					}
				} // for (of j)
			} else {
				// if the row is zeroed, we should stack it at the bottom row of the matrix.
				for (int j = matrix.length-1; j >= r+1; j--) {
					int otherRowLC = findLeadCoeffPosition(matrix,j); 

					if ( otherRowLC != -1 ) {
						ElementaryRowOperations.switchRows(matrix, r, j);
						switched = true;
						break;
					}
				} // for (of j)
			}

			if(!switched)
				//advance
				r++;
		}
	}

	private static int rankOfEchelonMatrix(double matrix[][]) {
		int rank = 0;

		for (int i = 0; i < matrix.length && rank < matrix[0].length-1; i++) {
			int leadCoeffColumn = findLeadCoeffPosition(matrix, i);

			if(leadCoeffColumn != -1)
				rank++;
		}

		return rank;
	}

	private static int findLeadCoeffPosition(double matrix[][], int row)
	{
		int leadCoeffColumn = 0;
		while(leadCoeffColumn < matrix[row].length-1 && matrix[row][leadCoeffColumn] == 0)
			// advance as long as there are zeroes
			leadCoeffColumn++;

		if(leadCoeffColumn == matrix[row].length-1) // if no leading coefficient found, its a zeroed-row
			return -1;
		else
			return leadCoeffColumn;
	}




}