import java.util.*;
import java.lang.*;
import java.io.*;

class OZ
{
	public static void main (String[] args) throws java.lang.Exception
	{
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(N + "");
            solve(br, st, out);
        }

        System.out.print(out.toString());
	}
	
	static void solve(BufferedReader br, StringTokenizer st, StringBuilder out) throws Exception {
        int N = Integer.parseInt(st.nextToken());
        String S = br.readLine().trim();

        long count1 = 0;
        ArrayList<Integer> pos1 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == '1') {
                count1++;
                pos1.add(i);
            }
        }

        long max_f = Math.min((long) N, 2 * count1);

        long min_swaps = 0;
        int ones_moved = 0;

        for (int i = 1; i <= max_f; i++) {
            int needed = (i + 1) / 2; // ceil(i/2)

            while (ones_moved < needed) {
                int current_pos = pos1.get(ones_moved);
                int target_pos = i - 1;

                if (current_pos > target_pos) {
                    min_swaps += (current_pos - target_pos);
                    pos1.set(ones_moved, target_pos);
                }
                ones_moved++;
            }

            if (ones_moved < count1 && pos1.get(ones_moved) <= i - 1) {
                ones_moved++;
            }
        }

        out.append(max_f).append(" ").append(min_swaps).append('\n');
    }
	
}
