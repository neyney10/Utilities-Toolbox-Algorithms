
import java.util.ArrayList;

import algorithms.dynamicprogramming.LCS;
import algorithms.dynamicprogramming.MDE;
import algorithms.linearprogramming.GaussEliminiation;
import algorithms.linearprogramming.Simplex;


public class Main {
    public static void main(String[] args) {
//        String a = "Ofer.Vermillion";
//        String b = "Vermill";
//
//        LCS lcs = new LCS();
//        int commonLength = lcs.compute(a, b); // should return 4
//
//        System.out.println("Common Length: "+commonLength);
//        
//        int minEditLength = MDE.compute(a, b);
//        
//        System.out.println("minEditLength: "+minEditLength);
//        
//        
//        for (int i = 1; i < a.length(); i++) {
//            String sub = a.substring(0,i);
//            System.out.println(sub + ": "+getBestMatch(sub));
//        }

        
        // Linear programming example 1987 UG exam, result: 39.444 and (x1,x2)=(5.222,2.222)
        double m[][] = {
                {1,1,1,0,0,0,10},
                {1,-1,0,-1,0,0,3},
                {5,4,0,0,1,0,35},
                {-5,-6,0,0,0,1,0}
            };
        /*
        double m[][] = { // result: 30
            {1,2,1,0,0,30},
            {0,1,0,1,0,5},
            {-1,-1,0,0,0,0}
        };
        */
        
        /*
         	double m[][] = { // result: 180,000
                {1,1,1,1,0,0,0,		600},
                {1,3,0,0,1,0,0,		600},
                {2,0,1,0,0,1,0,		900},
                {-60,-90,-300,0,0,0,1,0}
            }; 
         */

        printMatrix(m);
        System.out.println("Substracting...");
        Simplex.compute(m);
        printMatrix(m);
 
        
        
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

    /**
     * https://stackoverflow.com/questions/5061912/printing-out-a-2-d-array-in-matrix-format
     */
    public static void printMatrix(double[][] m) {
        try{
            int rows = m.length;
            int columns = m[0].length;
            String str = "|\t";
    
            for(int i=0;i<rows;i++){
                for(int j=0;j<columns;j++){
                    str += m[i][j] + "\t";
                }
    
                System.out.println(str + "|");
                str = "|\t";
            }
    
        }catch(Exception e){System.out.println("Matrix is empty!!");}
    }
    
    
    
    

}