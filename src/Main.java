
import java.util.ArrayList;

import algorithms.dynamicprogramming.LCS;
import algorithms.linearprogramming.Simplex;
import utilities.Print;


public class Main {
	public static void main(String[] args) {

		double m[][] = { // result: -0.5// works
				{-2,-1,-2,-1},
				{2,-3,5,2},
				{-3,4,-1,1},
				{1,2,3,0}
		}; 
		
		double m2[][] = { // result: 0// works
				{2,-1,1,-1},
				{1,0,2,2},
				{-7,4,-6,1},
				{-3,1,-2,0}
		}; 


		Print.printMatrix(m2);
		System.out.println("Substracting...");
		Simplex.min_compute(m2);
		System.out.println();
		Print.printMatrix(m2);



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