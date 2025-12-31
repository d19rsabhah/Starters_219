import java.util.*;
import java.lang.*;
import java.io.*;

class PIZZACOMP
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            int _svalue = 100 * b;
            int _largeV = 225 * a;

            if (_svalue > _largeV) {
                System.out.println("Small");
            } else if (_svalue < _largeV) {
                System.out.println("Large");
            } else {
                System.out.println("Equal");
            }
        }
        sc.close();
	}
}
