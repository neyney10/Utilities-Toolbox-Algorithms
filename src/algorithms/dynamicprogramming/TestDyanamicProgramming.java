package algorithms.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDyanamicProgramming {

	@Test
	void testLCS() {
		String strings[] = {
				"abcdabd",
				"abcd",
				"abab",
				"efafba"
		};
		
		LCS lcs = new LCS();
		
		assertEquals(lcs.compute(strings[0], strings[1]), 4);
		assertEquals(lcs.compute(strings[1], strings[0]), 4); // reverse should be equal
		
		assertEquals(lcs.compute(strings[0], strings[2]), 4);
		assertEquals(lcs.compute(strings[2], strings[0]), 4); // reverse should be equal
		
		assertEquals(lcs.compute(strings[1], strings[2]), 2);
		assertEquals(lcs.compute(strings[2], strings[1]), 2); // reverse should be equal
		
		assertEquals(lcs.compute(strings[0], strings[3]), 3);
		assertEquals(lcs.compute(strings[3], strings[0]), 3); // reverse should be equal
		
		assertEquals(lcs.compute(strings[2], strings[3]), 3);
		assertEquals(lcs.compute(strings[3], strings[2]), 3); // reverse should be equal
	}
	
	/**
	 * Test Min Edit Distance between strings using the MDE class
	 */
	@Test
	void testMDE() {
		String strings[] = {
				"abcdabd",
				"abcd",
				"abacb"
		};
		
		assertEquals(MDE.compute(strings[0], strings[1]), 3); // should remove 3 chars from the end from strings[0]
		assertEquals(MDE.compute(strings[1], strings[0]), 3); // reverse should be equal
		
		assertEquals(MDE.compute(strings[0], strings[2]), 4); 
		assertEquals(MDE.compute(strings[2], strings[0]), 4); // reverse should be equal
		
		assertEquals(MDE.compute(strings[1], strings[2]), 2); 
		assertEquals(MDE.compute(strings[2], strings[1]), 2); // reverse should be equal
		
	}

}
