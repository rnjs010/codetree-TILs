import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] cost;
    static int[][] dp;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        int ans = tsp(0, 1);
        System.out.println(ans);
    }

    static int tsp(int cur, int visit) {
        if (visit == (1 << n) - 1) {
            if (cost[cur][0] == 0) return INF;
            return cost[cur][0];
        }

        if (dp[cur][visit] != -1) {
            return dp[cur][visit];
        }

        dp[cur][visit] = INF;

        for (int i = 0; i < n; i++) {
            if (cost[cur][i] == 0) continue;
            if ((visit & (1 << i)) != 0) continue;

            int nextCost = cost[cur][i] + tsp(i, visit | (1 << i));
            dp[cur][visit] = Math.min(dp[cur][visit], nextCost);
        }

        return dp[cur][visit];
    }
}
