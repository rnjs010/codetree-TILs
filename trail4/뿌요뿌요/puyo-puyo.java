import java.util.*;
import java.io.*;

public class Main {
    static int n, cnt;
    static int[][] grid;
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

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

        visit = new boolean[n][n];
        int bomb = 0;
        int maxC = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j]) {
                    cnt = 1;
                    visit[i][j] = true;
                    dfs(i, j, grid[i][j]);
                    if (cnt >= 4) bomb++;
                    maxC = Math.max(maxC, cnt);
                }
            }
        }

        System.out.print(bomb + " " + maxC);
    }

    static void dfs(int x, int y, int num) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (grid[nx][ny] != num || visit[nx][ny]) continue;
            cnt++;
            visit[nx][ny] = true;
            dfs(nx, ny, num);
        }
    }
}