package algorithms.linearprogramming;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;

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
				printMatrix(matrix);
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
	
	
	/**
     * https://stackoverflow.com/questions/5061912/printing-out-a-2-d-array-in-matrix-format
     */
    public static void printMatrix(double[][] m) {
        try{
            int rows = m.length;
            int columns = m[0].length;
            String str = "|\t";
    
            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    str += m[i][j] + "\t";
                }
    
                System.out.println(str + "|");
                str = "|\t";
            }
    
        }catch(Exception e){System.out.println("Matrix is empty!!");}
    }
}
