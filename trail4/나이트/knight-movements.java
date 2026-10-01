import java.util.*;
import java.io.*;

public class Main {
    static int n, r1, c1, r2, c2;
    static int[][] dist;
    static Deque<int[]> dq = new ArrayDeque<>();
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken()) - 1;
        c1 = Integer.parseInt(st.nextToken()) - 1;
        r2 = Integer.parseInt(st.nextToken()) - 1;
        c2 = Integer.parseInt(st.nextToken()) - 1;

        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        dist[r1][c1] = 0;
        dq.offer(new int[]{r1, c1});
        bfs();
        System.out.print(dist[r2][c2]);
    }

    static void bfs() {
        while(!dq.isEmpty()) {
            int[] p = dq.poll();
            int x = p[0], y = p[1];

            if (x == r2 && y == c2) return;

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (dist[nx][ny] != -1) continue;
                dist[nx][ny] = dist[x][y] + 1;
                dq.offer(new int[]{nx, ny});
            }
        }
    }
}