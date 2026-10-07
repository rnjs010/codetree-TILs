import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[][] grid, visit;
    static Deque<int[]> dq = new ArrayDeque<>();
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        visit = new int[n][n];
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visit[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 2) {
                    dq.offer(new int[]{i, j});
                    visit[i][j] = 0;
                }
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j] == -1 && grid[i][j] == 1) {
                    sb.append(-2).append(" ");
                } else {
                    sb.append(visit[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void bfs() {
        while(!dq.isEmpty()) {
            int[] p = dq.poll();
            int x = p[0], y = p[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visit[nx][ny] != -1 || grid[nx][ny] == 0) continue;
                visit[nx][ny] = visit[x][y] + 1;
                dq.offer(new int[]{nx, ny});
            }
        }
    }
}