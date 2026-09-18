import java.util.*;
import java.io.*;

public class Main {
    static int n, m, c;
    static int[][] grid;
    static int val, ans;
    static ArrayList<int[]> all = new ArrayList<>();
    static int[][] sel = new int[2][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n - m; j++) {
                val = 0;
                comb1(i, j, j, 0, 0);
                all.add(new int[]{i, j, val});
            }
        }

        comb2(0, 0);
        System.out.println(ans);
    }

    static void comb1(int x, int y, int idx, int sum, int v) {
        if (idx == (m + y)) {
            val = Math.max(val, v);
            return;
        }

        int s = sum + grid[x][idx];
        if (s <= c) {
            comb1(x, y, idx + 1, s, v + (grid[x][idx] * grid[x][idx]));
        }

        comb1(x, y, idx + 1, sum, v);
    }

    static void comb2(int depth, int sum) {
        if (depth == 2) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 0; i < all.size(); i++) {
            if (depth != 0 && sel[depth - 1][0] == all.get(i)[0] 
                && Math.abs(sel[depth - 1][1] - all.get(i)[1]) < m) {
                    continue;
            }
            sel[depth] = all.get(i);
            comb2(depth + 1, sum + all.get(i)[2]);
        }
    }
}