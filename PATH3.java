import java.util.*;
import java.lang.*;
import java.io.*;

class PATH3
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            if (n == 2) {
                System.out.println(-1);
                continue;
            }

            int[][] A = new int[n][n];

            A[0][0] = 1;
            A[0][1] = 1;
            A[0][2] = 1;

            A[1][1] = 1;
            A[1][2] = 1;

            A[2][1] = 1;
            A[2][2] = 1;


            for (int j = 2; j < n; j++) {
                A[2][j] = 1;
            }
            for (int i = 2; i < n; i++) {
                A[i][n - 1] = 1;
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(A[i][j] + " ");
                }
                System.out.println();
            }
        }
        sc.close();

	}
}
