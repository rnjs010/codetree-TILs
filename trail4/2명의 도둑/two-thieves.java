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

        selectThieves();
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

    static void selectThieves() {
        for (int i = 0; i < all.size(); i++) {
            for (int j = i + 1; j < all.size(); j++) {
                int[] t1 = all.get(i);
                int[] t2 = all.get(j);
                
                if (t1[0] == t2[0] && Math.abs(t1[1] - t2[1]) < m) {
                    continue;
                }
                
                ans = Math.max(ans, t1[2] + t2[2]);
            }
        }
    }
}