package algorithms.dynamicprogramming;

import java.util.ArrayList;


/**
 * Least common sequence between two strings.
 */
public class LCS {

    private ArrayList<Integer> commonCharIndex;
    private int commonSequenceLength;


    public ArrayList<Integer> getCommonCharIndex() {
        return this.commonCharIndex;
    }

    public int getCommonSequenceLength() {
        return this.commonSequenceLength;
    }

    
    public int compute(String a, String b) {

        if(a == null || a.length() == 0 || b == null || b.length() == 0)
            return 0;

        //// Initialize variables and a result matrix for dynamic programming.
        int m[][] = new int[a.length()][b.length()];
        commonCharIndex = new ArrayList<>();

        //// Start conditions and information.
        // first cell of the matrix.
        m[0][0] = (a.charAt(0) == b.charAt(0)) ? 1 : 0;

        // first column of the matrix.
        for (int i = 1; i < m.length; i++)
            m[i][0] = Math.max(m[i-1][0], ((a.charAt(i) == b.charAt(0)) ? 1 : 0));

        // first row of the matrix.
        for (int j = 1; j < m[0].length; j++)
            m[0][j] = Math.max(m[0][j-1], ((a.charAt(0) == b.charAt(j)) ? 1 : 0));

        //// fill the matrix using the recursion relation of LCS.
        for (int i = 1; i < m.length;i++) {
            for (int j = 1; j < m[i].length;j++) {
                if(a.charAt(i) == b.charAt(j))
                    m[i][j] = m[i-1][j-1] + 1;
                    
                else m[i][j] = Math.max(m[i-1][j], m[i][j-1]);
            }
        }
        

        return m[a.length() - 1][b.length() - 1]; // return last cell of the matrix - last result
    }



}