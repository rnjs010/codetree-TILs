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

        find(0, 0, 1);
        System.out.print(ans);
    }

    static void find(int row, int sum, int visit) {
        if (sum >= ans) return;

        if (visit == (1 << n) - 1) {
            if (cost[row][0] != 0) {
                ans = Math.min(ans, sum + cost[row][0]);
            }
            return;
        }

        for (int i = 1; i < n; i++) {
            if (cost[row][i] == 0) continue;
            if ((visit & (1 << i)) != 0) continue;
            
            find(i, sum + cost[row][i], visit | (1 << i));
        }
    }
}