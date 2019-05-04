package algorithms.linearprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSimplex {
	
	final double DELTA = 0.0001;

	@Test
	void testSimplexComputeMax() {
		/*
		 * Maximum optimization problems
		 */
		
		// Linear programming example 1987 UG exam, result: 39.444 and (x1,x2)=(5.222,2.222) http://people.brunel.ac.uk/~mastjjb/jeb/or/morelp.html
		double m1[][] = { 
				{1,1,10},
				{-1,1,-3},
				{5,4,35},
				{-5,-6,0}
		};

		double m2[][] = { // result: 47/3 = 15.6667 // works
				{1,3,-1,6},
				{0,1,1,4},
				{3,1,0,7},
				{-5,-2,-1,0}
		};

		double m3[][] = { // result: 30 // works
				{1,2,30},
				{0,1,5},
				{-1,-1,0}
		};

		double m4[][] = { // result: 180,000 // works
				{1,1,1,		600},
				{1,3,0,		600},
				{2,0,1,		900},
				{-60,-90,-300,0}
		}; 
		
		double opt;
		opt = Simplex.compute(m1, Simplex.opt.MAX);
		assertEquals(39.4444, opt, DELTA);
		
		opt = Simplex.compute(m2, Simplex.opt.MAX);
		assertEquals(15.6667, opt, DELTA);
		
		opt = Simplex.compute(m3, Simplex.opt.MAX);
		assertEquals(30, opt, DELTA);
		
		opt = Simplex.compute(m4, Simplex.opt.MAX);
		assertEquals(180000, opt, DELTA);

	}
	
	@Test
	void testSimplexComputeMin() {
		/*
		 * Minimum optimization problems
		 */
		
		// min -x1-2x2-3x3
		double m1[][] = { // result: -0.5// works
				{-2,-1,-2,-1},
				{2,-3,5,2},
				{-3,4,-1,1},
				{1,2,3,0}
		}; 
		
		// min 3x1 - x2 +2x3
		double m2[][] = { // result: 0// works
				{2,-1,1,-1},
				{1,0,2,2},
				{-7,4,-6,1},
				{-3,1,-2,0}
		}; 
		
		double opt;
		opt = Simplex.compute(m1, Simplex.opt.MIN);
		assertEquals(-0.5, opt, DELTA);
		
		opt = Simplex.compute(m2, Simplex.opt.MIN);
		assertEquals(0, opt, DELTA);
		
	}

}
