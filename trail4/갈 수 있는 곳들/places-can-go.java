import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[][] grid;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static Deque<int[]> dq = new ArrayDeque<>();
    static boolean[][] visit;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            if (!visit[x][y]) {
                ans++;
                visit[x][y] = true;
                dq.offer(new int[]{x, y});
                bfs();
            }
        }

        System.out.print(ans);
    }

    static void bfs() {
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visit[nx][ny] || grid[nx][ny] == 1) continue;
                ans++;
                visit[nx][ny] = true;
                dq.offer(new int[]{nx, ny});
            }
        }
    }
}