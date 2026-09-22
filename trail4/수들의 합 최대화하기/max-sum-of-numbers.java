import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] grid;
    static int[][] dp;
    static final int INF = -987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], INF);
        }

        int ans = max_sum(0, 0);
        System.out.println(ans);
    }

    static int max_sum(int row, int visited) {
        if (row == n) {
            return 0;
        }

        if (dp[row][visited] != INF) {
            return dp[row][visited];
        }

        int maxVal = INF;
        for (int i = 0; i < n; i++) {
            if ((visited & (1 << i)) != 0) continue;

            int nextResult = grid[row][i] + max_sum(row + 1, visited | (1 << i));
            maxVal = Math.max(maxVal, nextResult);
        }

        return dp[row][visited] = maxVal;
    }
}
