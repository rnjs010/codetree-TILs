import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] grid;
    static boolean[][] visit;
    static Deque<int[]> dq = new ArrayDeque<>();
    static Deque<int[]> nextDq = new ArrayDeque<>();
    static int all = 0, sec = 0, cnt = 0;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

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
                if (grid[i][j] == 1) {
                    all++;
                }
            }
        }

        visit = new boolean[n][m];
        visit[0][0] = true;
        dq.offer(new int[]{0, 0});
        while (all > 0) {
            bfs();
            sec++;
            all -= cnt;
            dq = nextDq;
            nextDq = new ArrayDeque<>();
        }

        System.out.print(sec + " " + cnt);

    }

    static void bfs() {
        cnt = 0;
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visit[nx][ny]) continue;
                visit[nx][ny] = true;
                if (grid[nx][ny] == 0) {
                    dq.offer(new int[]{nx, ny});
                } else {
                    grid[nx][ny] = 0;
                    nextDq.offer(new int[]{nx, ny});
                    cnt++;
                }
            }
        }
    }
}