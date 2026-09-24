import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] grid;
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int maxN = 0;
        grid = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                maxN = Math.max(maxN, grid[i][j]);
            }
        }

        int cnt = 0;
        int rain = 1;
        for (int k = 1; k < maxN; k++) {
            int t = 0;
            visit = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] > k && !visit[i][j]) {
                        t++;
                        visit[i][j] = true;
                        dfs(i, j, k);
                    }
                }
            }
            if (cnt < t) {
                cnt = t;
                rain = k;
            }
        }

        System.out.print(rain + " " + cnt);
    }

    static void dfs(int x, int y, int k) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (grid[nx][ny] <= k || visit[nx][ny]) continue;
            visit[nx][ny] = true;
            dfs(nx, ny, k);
        }
    }
}