import java.util.*;
import java.io.*;

public class Main {
    static int n, k, r, c;
    static int[][] grid;
    static boolean[][] visit;
    static Deque<int[]> dq = new ArrayDeque<>();
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1};

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
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < k; i++) {
            visit = new boolean[n][n];
            visit[r][c] = true;
            dq.offer(new int[]{r, c});
            int[] res = bfs(grid[r][c]);
            if (res[0] != n) {
                r = res[0];
                c = res[1];
            } else {
                break;
            }
        }

        System.out.print((r + 1) + " " + (c + 1));
    }

    static int[] bfs(int num) {
        int x = n, y = n, maxV = -1;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visit[nx][ny] || grid[nx][ny] >= num) continue;
                visit[nx][ny] = true;
                dq.offer(new int[]{nx, ny});
                if (grid[nx][ny] >= maxV) {
                    if (grid[nx][ny] == maxV && (x < nx || (x == nx && y < ny))) continue;
                    x = nx;
                    y = ny;
                    maxV = grid[nx][ny];
                }
            }
        }

       return new int[]{x, y};
    }
}