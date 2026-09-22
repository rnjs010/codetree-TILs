import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] grid;
    static boolean[] visit;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        grid = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
            
        visit = new boolean[n];
        perm(0, 0, 0);
        System.out.print(ans);
    }

    static void perm(int cur, int row, int sum) {
        if (cur == n) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            perm(cur + 1, row + 1, sum + grid[row][i]);
            visit[i] = false;
        }
    }
}