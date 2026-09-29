import java.util.*;
import java.io.*;

public class Main {
    static int n, k, u, d;
    static int[][] grid;
    static Deque<int[]> dq = new ArrayDeque<>();
    static boolean[][] visit;
    static int ans;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.print(ans);
    }

    static void comb(int idx, int depth) {
        if (depth == k) {
            ans = Math.max(ans, bfs());
            return;
        }

        for (int i = idx; i < n*n; i++) {
            dq.offer(new int[]{i / n, i % n});
            comb(i + 1, depth + 1);
            dq.pollLast();
        }
    }

    static int bfs() {
        int cnt = k;
        visit = new boolean[n][n];
        Deque<int[]> bfsDq = new ArrayDeque<>();
        for (int[] cur: dq) {
            bfsDq.offer(cur);
            visit[cur[0]][cur[1]] = true;
        }

        while (!bfsDq.isEmpty()) {
            int[] cur = bfsDq.poll();
            int x = cur[0];
            int y = cur[1];
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visit[nx][ny]) continue;
                int gap = Math.abs(grid[x][y] - grid[nx][ny]);
                if (gap >= u && gap <= d) {
                    cnt++;
                    visit[nx][ny] = true;
                    bfsDq.offer(new int[]{nx, ny});
                }
            }
        }

        return cnt;
    }
}