package algorithms.linearprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utilities.Print;

class TestSimplex {

	final double DELTA = 0.0001;

	@Test
	void testSimplexComputeMax() {
		/*
		 * Maximum optimization problems
		 */

		double opt;
		opt = Simplex.compute(getMaxProblem1());
		assertEquals(39.4444, opt, DELTA);

		opt = Simplex.compute(getMaxProblem2());
		assertEquals(15.6667, opt, DELTA);

		opt = Simplex.compute(getMaxProblem3());
		assertEquals(30, opt, DELTA);

		opt = Simplex.compute(getMaxProblem4());
		assertEquals(180000, opt, DELTA);

	}

	@Test
	void testSimplexComputeMin() {
		/*
		 * Minimum optimization problems
		 */
		double opt;
		opt = Simplex.compute(getMinProblem1());
		assertEquals(-0.5, opt, DELTA);

		opt = Simplex.compute(getMinProblem2());
		assertEquals(0, opt, DELTA);

	}

	private LPProblem getMaxProblem1()
	{
		// Linear programming example 1987 UG exam, result: 39.444 and (x1,x2)=(5.222,2.222) http://people.brunel.ac.uk/~mastjjb/jeb/or/morelp.html
		/*		double m1[][] = { 
				{1,1,10},
				{-1,1,-3},
				{5,4,35},
				{-5,-6,0}
		}; */
		
		LPProblem lpp1 = new LPProblem();

		lpp1.setOptimizationType(LPProblem.opt.MAX);
		lpp1.setObjective(new Equation("+ 5x + 6y = 0"));

		lpp1.addConstraint(new Equation("+ x + y <= 10"));
		lpp1.addConstraint(new Equation("+ x - y >= 3"));
		lpp1.addConstraint(new Equation("+ 5x + 4y <= 35"));

		return lpp1;
	}

	private LPProblem getMaxProblem2()
	{
		
		/*		double m2[][] = { // result: 47/3 = 15.6667 // works
				{1,3,-1,6},
				{0,1,1,4},
				{3,1,0,7},
				{-5,-2,-1,0}
		};
		 */
	

		LPProblem lpp = new LPProblem();

		lpp.setOptimizationType(LPProblem.opt.MAX);
		lpp.setObjective(new Equation("+ 5x + 2y + z = 0"));

		lpp.addConstraint(new Equation("+ x + 3y - z <= 6"));
		lpp.addConstraint(new Equation("+ 0x + y + z <= 4"));
		lpp.addConstraint(new Equation("+ 3x + y + 0z <= 7"));

		return lpp;
	}
	
	private LPProblem getMaxProblem3()
	{
		
		/*		double m3[][] = { // result: 30 // works
				{1,2,30},
				{0,1,5},
				{-1,-1,0}
		};
		 */
	

		LPProblem lpp = new LPProblem();

		lpp.setOptimizationType(LPProblem.opt.MAX);
		lpp.setObjective(new Equation("+ x + y = 0"));

		lpp.addConstraint(new Equation("+ 1 + 2y <= 30"));
		lpp.addConstraint(new Equation("+ 0x + y <= 5"));
		

		return lpp;
	}
	
	private LPProblem getMaxProblem4()
	{
		
		/*		
		double m4[][] = { // result: 180,000 // works
				{1,1,1,		600},
				{1,3,0,		600},
				{2,0,1,		900},
				{-60,-90,-300,0}
		}; 
		 */
	

		LPProblem lpp = new LPProblem();

		lpp.setOptimizationType(LPProblem.opt.MAX);
		lpp.setObjective(new Equation("+ 60x + 90y + 300z = 0"));

		lpp.addConstraint(new Equation("+ x + y + z <= 600"));
		lpp.addConstraint(new Equation("+ x + 3y + 0z <= 600"));
		lpp.addConstraint(new Equation("+ 2x + 0y + 1z <= 900"));

		return lpp;
	}
	
	private LPProblem getMinProblem1()
	{
		
		/*		
		// min -x1-2x2-3x3
		double m1[][] = { // result: -0.5// works
				{-2,-1,-2,-1},
				{2,-3,5,2},
				{-3,4,-1,1},
				{1,2,3,0}
		}; 
		 */
	

		LPProblem lpp = new LPProblem();

		lpp.setOptimizationType(LPProblem.opt.MIN);
		lpp.setObjective(new Equation("- x - 2y - 3z = 0"));

		lpp.addConstraint(new Equation("- 2x - y - 2z >= -1"));
		lpp.addConstraint(new Equation("+ 2x - 3y + 5z >= 2"));
		lpp.addConstraint(new Equation("- 3x + 4y - z >= 1"));

		return lpp;
	}
	
	private LPProblem getMinProblem2()
	{
		
		/*		
		// min 3x1 - x2 +2x3
		double m2[][] = { // result: 0// works
				{2,-1,1,-1},
				{1,0,2,2},
				{-7,4,-6,1},
				{-3,1,-2,0}
		}; 
		 */
	

		LPProblem lpp = new LPProblem();

		lpp.setOptimizationType(LPProblem.opt.MIN);
		lpp.setObjective(new Equation("+ 3X - y + 2z = 0"));

		lpp.addConstraint(new Equation("- 2x + y - z >= -1"));
		lpp.addConstraint(new Equation("+ x + 0y + 2z >= 2"));
		lpp.addConstraint(new Equation("- 7x + 4y - 6z >= 1"));

		return lpp;
	}
	

}
