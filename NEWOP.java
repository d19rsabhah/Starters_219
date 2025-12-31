import java.util.*;
import java.lang.*;
import java.io.*;

class NEWOP
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] A = new int[n];

            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
            }

            long[][] dpMin = new long[n][n];
            long[][] dpMax = new long[n][n];

            for (int i = 0; i < n; i++) {
                dpMin[i][i] = dpMax[i][i] = A[i];
            }


            for (int len = 2; len <= n; len++) {
                for (int l = 0; l + len - 1 < n; l++) {
                    int r = l + len - 1;
                    dpMin[l][r] = Long.MAX_VALUE;
                    dpMax[l][r] = Long.MIN_VALUE;

                    for (int k = l; k < r; k++) {
                        long minVal = dpMin[l][k] + 2 * dpMin[k + 1][r];
                        long maxVal = dpMax[l][k] + 2 * dpMax[k + 1][r];

                        dpMin[l][r] = Math.min(dpMin[l][r], minVal);
                        dpMax[l][r] = Math.max(dpMax[l][r], maxVal);
                    }
                }
            }

            System.out.println(dpMin[0][n - 1] + " " + dpMax[0][n - 1]);
        }
        sc.close();

	}
}
