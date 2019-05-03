package utilities;

public class Print {
	
	public static void printArray(double[] a)
	{
		System.out.print("[");
		for (int i = 0; i < a.length-1; i++) 
			System.out.print(a[i]+", ");
		System.out.print(a[a.length-1]);
		System.out.print("]");
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
