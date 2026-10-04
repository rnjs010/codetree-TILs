import java.util.*;
import java.io.*;

public class Main {
    static int n, k, r1, c1, r2, c2;
    static int[][] grid;
    static int[][][] dist;
    static Deque<int[]> dq = new ArrayDeque<>();
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

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

        st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken()) - 1;
        c1 = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        r2 = Integer.parseInt(st.nextToken()) - 1;
        c2 = Integer.parseInt(st.nextToken()) - 1;
        
        dist = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j], -1);
            }
        }
        bfs();

        int ans = Integer.MAX_VALUE;
        for (int w = 0; w <= k; w++) {
            if (dist[r2][c2][w] != -1) {
                ans = Math.min(ans, dist[r2][c2][w]);
            }
        }
        System.out.print((ans == Integer.MAX_VALUE) ? -1 : ans);
    }

    static void bfs() {
        dq.offer(new int[]{r1, c1, 0});
        dist[r1][c1][0] = 0;
        while (!dq.isEmpty()) {
            int[] p = dq.poll();
            int x = p[0], y = p[1], w = p[2];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (grid[nx][ny] == 0) {
                    if (dist[nx][ny][w] == -1) {
                        dist[nx][ny][w] = dist[x][y][w] + 1;
                        dq.offer(new int[]{nx, ny, w});
                    }
                } else {
                    if (w < k && dist[nx][ny][w + 1] == -1) {
                        dist[nx][ny][w + 1] = dist[x][y][w] + 1;
                        dq.offer(new int[]{nx, ny, w + 1});
                    }
                }
            }
        }
    }
}