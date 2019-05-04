package algorithms.linearprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSimplex {
	
	final double DELTA = 0.0001;

	@Test
	void testSimplexCompute() {
		// Linear programming example 1987 UG exam, result: 39.444 and (x1,x2)=(5.222,2.222) http://people.brunel.ac.uk/~mastjjb/jeb/or/morelp.html
		double m1[][] = { 
				{1,1,1,0,0,10},
				{-1,1,0,1,0,-3},
				{5,4,0,0,1,35},
				{-5,-6,0,0,0,0}
		};

		double m2[][] = { // result: 47/3 = 15.6667 // works
				{1,3,-1,6},
				{0,1,1,4},
				{3,1,0,7},
				{-5,-2,-1,0}
		};

		double m3[][] = { // result: 30 // works
				{1,2,1,0,0,30},
				{0,1,0,1,0,5},
				{-1,-1,0,0,0,0}
		};

		double m4[][] = { // result: 180,000 // works
				{1,1,1,1,0,0,0,		600},
				{1,3,0,0,1,0,0,		600},
				{2,0,1,0,0,1,0,		900},
				{-60,-90,-300,0,0,0,1,0}
		}; 
		
		double opt;
		opt = Simplex.compute(m1);
		assertEquals(39.4444, opt, DELTA);
		
		opt = Simplex.compute(m2);
		assertEquals(15.6667, opt, DELTA);
		
		opt = Simplex.compute(m3);
		assertEquals(30, opt, DELTA);
		
		opt = Simplex.compute(m4);
		assertEquals(180000, opt, DELTA);

	}

}
