import java.util.*;
import java.io.*;


public class Main {
    static int n;
    static int[][] grid;
    static int ans = 0;

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

        perm(0, Integer.MAX_VALUE, 0);
        System.out.print(ans);
    }

    static void perm(int row, int num, int visit) {
        if (num <= ans) return;

        if (row == n) {
            ans = Math.max(ans, num);
            return;
        }

        for (int i = 0; i < n; i++) {
            if ((visit & (1 << i)) != 0) continue;
            perm(row + 1, Math.min(num, grid[row][i]), visit | (1 << i));
        }
    }
}