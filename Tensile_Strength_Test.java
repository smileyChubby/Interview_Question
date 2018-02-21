/*
 *	Thanapon Jarukasetphon 5888057 
 -------------------------------------------------------------------------------------------------------------
 Dynamic Programming - Tensile_Strength_Test
 -------------------------------------------------------------------------------------------------------------
  	k ==> Number of Forces
  	n ==> Number of Samples
  	tensileTest(n, k) ==> Minimum number of trials needed to find the critical force in worst case.
  	tensileTest(n, k) = 1 + min{max(tensileTest(n - 1, x - 1), tensileTest(n, k - x)): 
                 x in {1, 2, ..., k}}
 -------------------------------------------------------------------------------------------------------------
 Big O: O(nk^2)
 -------------------------------------------------------------------------------------------------------------
*
*/

public class Tensile_Strength_Test {

	public Tensile_Strength_Test() {}
		
	public static void main(String[] args) {
		// Samples = 3, Force = 1000
		int n = 3, k = 1000;
        System.out.println("The least number of trials which would guarantee for "+ n +" samples and "+ k +" forces is " + tensileTest(n, k));   
	}
	
	public static int tensileTest(int n, int k) {
	       
		// sampleForce[][] will represent minimum number of trials needed for i samples and j forces.
		int sampleForce[][] = new int[n+1][k+1];
	    int result, i, j, x;
	    
	    // Base case: 1 trial for 1 force and 0 trials for 0 force
        for (i = 1; i <= n; i++){
            sampleForce[i][1] = 1;
            sampleForce[i][0] = 0;
        }
         
        // j trials for one sample and j forces.
        for (j = 1; j <= k; j++) sampleForce[1][j] = j;
		
        // Fill rest of the entries in table using optimal substructure property
        for (i = 2; i <= n; i++){
            for (j = 2; j <= k; j++){
                sampleForce[i][j] = Integer.MAX_VALUE;
                for (x = 1; x <= j; x++){
                     result = 1 + Math.max(sampleForce[i-1][x-1], sampleForce[i][j-x]);
                     if (result < sampleForce[i][j])
                        sampleForce[i][j] = result;
                }
            }
        }  
        
		return sampleForce[n][k];
	}
}
