package algorithms.linearprogramming;

public class ElementaryRowOperations {
	
	public static void switchRows(double matrix[][], int row1, int row2)
	{
		for (int i = 0; i < matrix[row1].length; i++)
		{
			double temp = matrix[row2][i];
			matrix[row2][i] = matrix[row1][i];
			matrix[row1][i] = temp;
		}

	}

	public static void addRow(double matrix[][], int row1, int row2, double multiplier)
	{
		for (int i = 0; i < matrix[row1].length; i++)
			matrix[row2][i] += multiplier*matrix[row1][i];
	}

	public static void substractRow(double matrix[][], int row1, int row2, double multiplier)
	{
		addRow(matrix,row1,row2, -multiplier);
	}
	
	public static void multiplyRow(double matrix[][], int row1, double multiplier)
	{
		multiplyRow(matrix[row1], multiplier);
	}
	
	public static void multiplyRow(double row[], double multiplier)
	{
		for (int i = 0; i < row.length; i++)
			row[i] *= multiplier;
	} 
	
	public static void divideRow(double matrix[][], int row1, double division)
	{
		for (int i = 0; i < matrix[row1].length; i++)
			matrix[row1][i] /= division;
	}
}
