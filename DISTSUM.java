import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        String line = br.readLine();
        while (line != null && line.isEmpty())
            line = br.readLine();
        if (line == null)
            return;

        int T = Integer.parseInt(line.trim());
        while (T-- > 0) {
            line = br.readLine();
            while (line != null && line.trim().isEmpty())
                line = br.readLine();
            String[] parts = line.trim().split("\\s+");
            int N = Integer.parseInt(parts[0]);
            long P = Long.parseLong(parts[1]);

            solve(N, P, out);
        }
        System.out.print(out);
    }

    static void solve(int N, long P, StringBuilder out) {
        if (N == 2) {
            out.append(4 % P).append("\n");
            return;
        }

        // Precompute powers and factorials
        long[] powN = new long[N + 1];
        powN[0] = 1;
        for (int i = 1; i <= N; i++)
            powN[i] = (powN[i - 1] * N) % P;

        long[] fact = new long[N + 1];
        long[] invFact = new long[N + 1];
        fact[0] = 1;
        invFact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = (fact[i - 1] * i) % P;
        }
        invFact[N] = modInverse(fact[N], P);
        for (int i = N - 1; i >= 1; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % P;
        }

        // dp[u][v]
        long[][] dp = new long[N][N + 1];
        dp[0][1] = 1;

        long totalSum = powN[N];

        for (int u = 0; u < N; u++) {
            for (int v = u + 1; v < N; v++) { // Only extend from v < N
                long count = dp[u][v];
                if (count == 0)
                    continue;

                long contrib = (count * powN[N - u]) % P;
                if (u == 0 && v == 1) {

                }

                int k_terms = v - u;

                for (int next_v = v + 1; next_v <= N; next_v++) {
                    long ways = 0;
                    if (next_v == N) {
                        // Ways to reach >= N
                        long allOptions = pow(N, k_terms, P); // N^(v-u)
                        long waysLess = getCount(u, v, N - 1, N, P, fact, invFact, powN);
                        ways = (allOptions - waysLess + P) % P;
                    } else {
                        long waysT = getCount(u, v, next_v, N, P, fact, invFact, powN);
                        long waysTminus1 = getCount(u, v, next_v - 1, N, P, fact, invFact, powN);
                        ways = (waysT - waysTminus1 + P) % P;
                    }

                    if (ways > 0) {
                        dp[v][next_v] = (dp[v][next_v] + (count * ways) % P) % P;
                    }
                }
            }
        }

        totalSum = 0;
        for (int u = 0; u < N; u++) {
            for (int v = u + 1; v < N; v++) {
                long count = dp[u][v];
                if (count == 0)
                    continue;
                long contrib = (count * powN[N - u]) % P;
                totalSum = (totalSum + contrib) % P;
            }
        }

        out.append(totalSum).append("\n");
    }

    static long getCount(int u, int v, int T, int N, long P, long[] fact, long[] invFact, long[] powN) {
        long min_term = (long) T - v;
        long max_term = (long) T - u - 1;

        if (min_term <= 0)
            return 0;

        int k_terms = v - u;

        if (min_term > N) {
            return pow(N, k_terms, P);
        }

        if (max_term <= N) {
            long num = fact[(int) max_term];
            long den = invFact[(int) (min_term - 1)];
            return (num * den) % P;
        }

        // Mixed
        long linear_part = (fact[N] * invFact[(int) (min_term - 1)]) % P;
        int clamped_count = (int) (max_term - N);
        long clamped_part = pow(N, clamped_count, P);
        return (linear_part * clamped_part) % P;
    }

    static long pow(long base, long exp, long P) {
        long res = 1;
        base %= P;
        while (exp > 0) {
            if ((exp % 2) == 1)
                res = (res * base) % P;
            base = (base * base) % P;
            exp /= 2;
        }
        return res;
    }

    static long modInverse(long n, long P) {
        return pow(n, P - 2, P);
    }
}
