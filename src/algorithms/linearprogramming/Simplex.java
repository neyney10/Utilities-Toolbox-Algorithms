package algorithms.linearprogramming;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

import utilities.Print;

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
	public static void compute(double matrix[][]) {
		int pivot[];
		while((pivot = findPivot(matrix)) != null) {
				GaussEliminiation.SingleGaussElimination(matrix, 0, pivot[0]);
				
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
	}
	
	private static int[] findPivot(double matrix[][]) {
		double min = 0; // min value have the most improvement.
		int position[] = new int[2];
		position[1] = matrix.length-1;
		
		for (int i = 0; i < matrix[matrix.length-1].length; i++) {
			double cellValue = matrix[matrix.length-1][i];
			if(cellValue < 0 && cellValue < min) {
				min = cellValue;
				position[0] = i;
			}
			
		}
		
		if(min == 0)
			return null;
		
		return position;
	}
	
	private static void interchange(double matrix[][], int row, int column) // substituion.
	{
		// TODO: implement.
	}
	
	

}
