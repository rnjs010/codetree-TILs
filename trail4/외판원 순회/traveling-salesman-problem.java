import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] cost;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        cost = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0, 0, 0, 0);
        System.out.print(ans);
    }

    static void find(int depth, int row, int sum, int visit) {
        if (sum >= ans) return;

        if (depth == n - 1) {
            if (cost[row][0] == 0) return;
            ans = Math.min(ans, sum + cost[row][0]);
            return;
        }

        for (int i = 1; i < n; i++) {
            if (cost[row][i] == 0) continue;
            if ((visit & (1 << i)) != 0) continue;
            find(depth + 1, i, sum + cost[row][i], visit | (1 << i));
        }
    }
}