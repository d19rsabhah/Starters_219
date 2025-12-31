import java.util.*;
import java.lang.*;
import java.io.*;

class OZ1
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();

            int ones = 0, zeros = 0, good = 0;

            for (char ch : s.toCharArray()) {
                if (ch == '1') ones++;
                else zeros++;

                if (ones >= zeros) good++;
            }

            System.out.println(good);
        }
        sc.close();
	}
}
