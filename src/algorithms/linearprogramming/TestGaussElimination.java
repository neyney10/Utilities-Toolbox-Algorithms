package algorithms.linearprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class TestGaussElimination {
	final double DELTA = 0.000001;

	@Test
	void testGaussEliminationCompute() {
		double m1[][] = { // last column is vector 'b', the RIGHT HAND SIDE of the equations.
				{2,1,0,2},
				{4,3,1,3},
				{2,4,0,4},
		};

		double m2[][] = { // last column is vector 'b', the RIGHT HAND SIDE of the equations.
				{3,1,0,2,3},
				{1,0,1,-1,3},
				{0,-2,0,1,0},
				{1,0,-3,1,-5}
		};

		double m3[][] = { // last column is vector 'b', the RIGHT HAND SIDE of the equations.
				{0,0,0,2},// zero-row, 0x1+0x2+0x3 = 2
				{4,2,1,3},
				{0,4,0,4},
		};
		
		double xsolutions[];
		
		xsolutions = GaussEliminiation.compute(m1);
		assertArrayEquals(xsolutions, new double[] {0.666666,0.666666,-1.666666}, DELTA);
		
		xsolutions = GaussEliminiation.compute(m2);
		assertArrayEquals(xsolutions, new double[] {1,0,2,0}, DELTA);
		
		xsolutions = GaussEliminiation.compute(m3); 
		// TODO: INF solutions
		//assertArrayEquals(xsolutions, new double[] {0.666666,0.666666,-1.666666}, DELTA);

	}

}
