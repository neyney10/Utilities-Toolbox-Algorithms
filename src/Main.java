
import java.util.ArrayList;

import algorithms.dynamicprogramming.LCS;
import algorithms.linearprogramming.Equation;
import algorithms.linearprogramming.GaussEliminiation;
import algorithms.linearprogramming.LPProblem;
import algorithms.linearprogramming.Simplex;
import utilities.Print;


public class Main {
	public static void main(String[] args) {

//		Equation e1 = new Equation("+ x + y <= 10");
//		System.out.println(e1);
//		Equation e2 = new Equation("- x + y <= -3");
//		System.out.println(e2);
//		Equation e3 = new Equation("+ 5x + 4y <= 35");
//		System.out.println(e3);
//		Equation e4 = new Equation("+ 5x + 6y = 0");
//		System.out.println(e4);
//		
//		LPProblem lpp = new LPProblem();
//		lpp.addConstraint(e1);
//		lpp.addConstraint(e2);
//		lpp.addConstraint(e3);
//		
//		lpp.setOptimizationType(LPProblem.opt.MAX);
//		lpp.setObjective(e4);
//		
//		
//		Simplex.compute(lpp);
//		System.out.println(lpp);
		//LPProblem p = getEqualityProblem1();
		
		double m2[][] = { // 
		{-1,5,2,5,5},
		{0,3,0,1,2}, // equality
		{-1,0,1,2,1}, // equality
		{0,-5,-1,-4,0}
}; 
		Print.printMatrix(m2);
		System.out.println("(0,1)");
		interchange(m2, 0, 1);
		//m2=removeRowColumn(m2, 2, 3);
		Print.printMatrix(m2);
		System.out.println("(1,1)");
		interchange(m2, 1, 1);
		//m2=removeRowColumn(m2, 1, 1);
		Print.printMatrix(m2);
		System.out.println();
		System.out.println("(1,0)");
		interchange(m2, 1, 0);
		//m2=removeRowColumn(m2, 1, 2);
		Print.printMatrix(m2);
		System.out.println();
		System.out.println("(0,0)");
		interchange(m2, 0, 0);
		Print.printMatrix(m2);
		System.out.println();

		
		//Simplex.compute(getEqualityProblem1());
	}


	public static String getBestMatch(String str) {
		String strings[] = {
				"Nikolai Hersk",
				"Ofek.Vermillio",
				"Ofer.Vermeil",
				"Ofak",
				"Vermillion",
				"Taofik",
				"Ofek Ofek Ofek Ofer Vermiilion",
		};

		String match = strings[0];
		ArrayList<String> matches = new ArrayList<>();

		LCS lcs = new LCS();
		int lastLen = 0;

		for (String s : strings) {
			int len = lcs.compute(s, str);

			if(len >= str.length()/2 && len >= s.length()/2) {
				if(len > lastLen){
					match = s;
					lastLen = len;
				}

				matches.add(s);
			}

		}
		System.out.println(matches.toString());
		return match;
	}
	
	private static LPProblem getEqualityProblem1() {
		
//		double m2[][] = { // 
//				{-1,5,2,5,5},
//				{0,3,0,1,2}, // equality
//				{-1,0,1,2,1}, // equality
//				{0,-5,-1,-4,0}
//		}; 
		
		LPProblem lppe = new LPProblem();

		lppe.setOptimizationType(LPProblem.opt.MAX); //solution = 4.5, 6 IF z is unrestricted
		lppe.setObjective(new Equation("+ 0x + 5y + z + 4w + 0h = 0"));

		lppe.addConstraint(new Equation("- x + 5y + 2z + 5w + h = 5"));
		lppe.addConstraint(new Equation("+ 0x + 3y + 0z + w + 0h = 2"));
		lppe.addConstraint(new Equation("- 1x + 0y + 1z + 2w + 0h = 1"));

		return lppe;
	}
	
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
	
	private static double[][] removeRowColumn(double[][] matrix, int Irow, int Icolumn) {
		// get new size
		int newRowsAmount = matrix.length-1;
		int newColumnAmount = matrix[0].length-1;
		
		// create new matrix.
		double[][] newMatrix = new double[newRowsAmount][newColumnAmount];
		
		
		int Inew = 0; 	//index of the new matrix
		int Jnew = 0; //index of new matrix
		// copy the values of the matrix
		for (int i = 0; i < matrix.length; i++) {
			if(i == Irow) 
				continue; 
				
			Jnew = 0;
			for (int j = 0; j < matrix[i].length; j++) {
				if(j == Icolumn) 
					continue;
				
				newMatrix[Inew][Jnew] = matrix[i][j];
				Jnew++;
			}
			
			Inew++;
		}
		
		// return the new matrix without the row and column
		return newMatrix;
		
	}





}