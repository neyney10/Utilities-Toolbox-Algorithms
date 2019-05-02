package algorithms.linearprogramming;


/**
 * Does not contain full Gauss Elmination algorithm to solve regular matrix
 * this class is an auxillary class for the Simplex algorithm.
 */
public class GaussEliminiation {
    
    public static void substractRow(double matrix[][], int row1, int row2, double multiplier)
    {
        for (int i = 0; i < matrix[row1].length; i++)
            matrix[row2][i] -= multiplier*matrix[row1][i];
    }
    
    public static void SingleGaussElimination(double matrix[][], int i, int j)
    {
    	for (int k = i+1; k < matrix.length; k++) {
    		double factor = matrix[k][j]/matrix[i][j];
    		substractRow(matrix, i, k, factor);
		}
    }
    

}