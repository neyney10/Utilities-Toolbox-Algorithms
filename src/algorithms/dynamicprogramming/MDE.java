package algorithms.dynamicprogramming;

/**
 * Min Edit Distance of strings
 * @author neyne
 *
 */
public class MDE {

	public static int compute(String s1, String s2) {
		int i, j;
		int[][] m = new int[s1.length()+1][s2.length()+1]; //create a matrix
		
		// set stopping values
		for(i = 0 ; i < m.length ; i++)
			m[i][0] = i;
		
		// set stopping values
		for(j = 0 ; j < m[0].length ; j++)
			m[0][j] = j;
		
		// calculate the matrix
		for(i = 1 ; i < m.length ; i++)
			for(j = 1 ; j < m[0].length ; j++)
			{
				if(s1.charAt(i-1) == s2.charAt(j-1)) // if the two letters match
					m[i][j] = m[i-1][j-1]; // then the total edits count is equal to the strings without'em
				else if(s1.length() > s2.length()) // if the lengths aren't the same and the two letters dont match
					m[i][j] = Math.min(m[i-1][j-1], m[i-1][j]) + 1; // min(substitute, remove letter)
				else
					m[i][j] = Math.min(m[i-1][j-1], m[i][j-1]) + 1; // min(substitute, remove letter)
			}
		
		return m[s1.length()][s2.length()];
	}

}
