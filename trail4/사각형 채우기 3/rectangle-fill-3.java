import java.util.*;
import java.io.*;

public class Main {
    final static long MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[Math.max(3, n + 1)];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;

        for (int i = 3; i <= n; i++) {
            long val = (3 * dp[i - 1] + dp[i - 2] - dp[i - 3]) % MOD;
            dp[i] = (val + MOD) % MOD;
        }

        System.out.print(dp[n]);
    }
}