import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] map, dist;
    static Deque<int[]> dq = new ArrayDeque<>();
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        dist[0][0] = 0;
        dq.offer(new int[]{0, 0});
        bfs();
        System.out.println(dist[n - 1][m - 1]);
    }

    static void bfs() {
        while(!dq.isEmpty()) {
            int[] p = dq.poll();
            int x = p[0], y = p[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (dist[nx][ny] != -1 || map[nx][ny] == 0) continue;
                dist[nx][ny] = dist[x][y] + 1;
                dq.offer(new int[]{nx, ny});
            }
        }
    }
}