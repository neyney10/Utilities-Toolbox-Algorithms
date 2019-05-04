
import java.util.ArrayList;

import algorithms.dynamicprogramming.LCS;
import algorithms.linearprogramming.Equation;
import algorithms.linearprogramming.GaussEliminiation;
import algorithms.linearprogramming.LPProblem;
import algorithms.linearprogramming.Simplex;
import utilities.Print;


public class Main {
	public static void main(String[] args) {

		Equation e1 = new Equation("+ x - y <= 10");
		System.out.println(e1);
		Equation e2 = new Equation("- x + y <= -3");
		System.out.println(e2);
		Equation e3 = new Equation("+ 5x + 4y <= 35");
		System.out.println(e3);
		Equation e4 = new Equation("+ 5x + 6y = 0");
		System.out.println(e4);
		
		LPProblem lpp = new LPProblem();
		lpp.addConstraint(e1);
		lpp.addConstraint(e2);
		lpp.addConstraint(e3);
		
		lpp.setObjective(e4);
		
		Simplex.compute(lpp, Simplex.opt.MAX);
		System.out.println(lpp);
		
//		double m2[][] = { // result: 0// works
//				{-1,5,2,5,5},
//				{0,3,0,1,2}, // equality
//				{-1,0,1,2,1}, // equality
//				{0,-5,-1,-4,0}
//		}; 
//
//
//		Print.printMatrix(m2);
//		System.out.println("Substracting...");
//		Simplex.max_compute(m2);
//		//Print.printArray(GaussEliminiation.compute(m2));
//		System.out.println();
//		Print.printMatrix(m2);



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





}