import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] grid;
    static boolean[][] visit;
    static Deque<int[]> dq = new ArrayDeque<>();
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

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
        dq.add(new int[]{0, 0});
        System.out.print(bfs());
    }

    static int bfs() {
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            if (cur[0] == n - 1 && cur[1] == m - 1) return 1;

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (grid[nx][ny] == 0 || visit[nx][ny]) continue;
                visit[nx][ny] = true;
                dq.add(new int[]{nx, ny});
            }
        }
        return 0;
    }
}