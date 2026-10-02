import java.util.*;
import java.io.*;

public class Main {
    static int n, h, m;
    static int[][] grid, dist;
    static Deque<int[]> dq = new ArrayDeque<>();
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        dist = new int[n][n]; // 3차원 필요 없이 2차원으로 종결!

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dist[i], -1); // 전부 -1로 초기화
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                
                // 역발상: 대피소(3)를 BFS의 다중 시작점으로 큐에 삽입!
                if (grid[i][j] == 3) {
                    dist[i][j] = 0;
                    dq.offer(new int[]{i, j});
                }
            }
        }

        // ★★★ 잊지 말고 BFS 탐색 실행 ★★★
        bfs();

        // 정답 출력 포맷 구축
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 원래 사람이 있던 자리(2)인 경우
                if (grid[i][j] == 2) {
                    sb.append(dist[i][j]).append(" "); // 대피소에서 도달한 최단 거리 출력 (도달 불가시 -1)
                } else {
                    sb.append(0).append(" "); // 사람이 없는 자리는 문제 조건에 따라 0 출력
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void bfs() {
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if (grid[nx][ny] == 1 || dist[nx][ny] != -1) continue; // 벽이거나 이미 방문한 곳 제외

                dist[nx][ny] = dist[x][y] + 1;
                dq.offer(new int[]{nx, ny});
            }
        }
    }
}
