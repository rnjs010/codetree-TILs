import java.util.*;
import java.io.*;

public class Main {
    static int n, k, m;
    static int[][] grid, startPoints;
    static boolean[][] visit;
    static ArrayList<int[]> rocks;
    static Deque<int[]> dq = new ArrayDeque<>();
    static int cnt = 0, ans = 0;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        rocks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) {
                    rocks.add(new int[]{i, j});
                }
            }
        }

        startPoints = new int[k][2];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            startPoints[i][0] = Integer.parseInt(st.nextToken()) - 1;
            startPoints[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        comb(0, 0);
        System.out.print(ans);
    }

    static void comb(int d, int idx) {
        if (d == m) {
            visit = new boolean[n][n];
            cnt = 0;
            for (int s = 0; s < k; s++) {
                int[] start = startPoints[s];
                if (!visit[start[0]][start[1]]) {
                    cnt++;
                    bfs(start);
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = idx; i < rocks.size(); i++) {
            int r = rocks.get(i)[0];
            int c = rocks.get(i)[1];
            grid[r][c] = 0;
            comb(d + 1, i + 1);
            grid[r][c] = 1;
        }
    }

    static void bfs(int[] s) {
        visit[s[0]][s[1]] = true;
        dq.offer(s);
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (visit[nx][ny] || grid[nx][ny] == 1) continue;
                cnt++;
                visit[nx][ny] = true;
                dq.offer(new int[]{nx, ny});
            }
        }
    }

}