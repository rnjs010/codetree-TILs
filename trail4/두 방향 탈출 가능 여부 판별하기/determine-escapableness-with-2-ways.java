import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] grid;
    static boolean[][] visit;
    static int[] dx = {1, 0}, dy = {0, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][m];
        visit[0][0] = true;
        dfs(0, 0);
        System.out.print(ans);
    }

    static void dfs(int x, int y) {
        if (x == (n - 1) && y == (m - 1)) {
            ans = 1;
            return;
        }

        for (int d = 0; d < 2; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            if (visit[nx][ny] || grid[nx][ny] == 0) continue;
            visit[nx][ny] = true;
            dfs(nx, ny);
        }
    }
}