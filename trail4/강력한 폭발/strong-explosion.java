import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<int[]> bombPos = new ArrayList<>();
    static int[] output;
    static int maxAns = 0;

    static int[][][] bombShapes = {
        {},
        {{0,0}, {-1,0}, {-2,0}, {1,0}, {2,0}},     // 1번: 상하 2칸씩
        {{0,0}, {-1,0}, {1,0}, {0,-1}, {0,1}},     // 2번: 상하좌우 1칸씩
        {{0,0}, {-1,-1}, {-1,1}, {1,-1}, {1,1}}    // 3번: 대각선 4방향
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) {
                    bombPos.add(new int[]{i, j});
                }
            }
        }

        output = new int[bombPos.size()];
        find(0);
        System.out.println(maxAns);
    }

    static void find(int depth) {
        if (depth == bombPos.size()) {
            calc();
            return;
        }

        for (int i = 1; i <= 3; i++) {
            output[depth] = i;
            find(depth + 1);
        }
    }

    static void calc() {
        boolean[][] visit = new boolean[n][n];
        int cnt = 0;

        for (int i = 0; i < bombPos.size(); i++) {
            int[] pos = bombPos.get(i);
            int x = pos[0];
            int y = pos[1];
            int type = output[i];

            for (int[] d: bombShapes[type]) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visit[nx][ny]) {
                        visit[nx][ny] = true;
                        cnt++;
                    }
                }
            }
        }

        maxAns = Math.max(maxAns, cnt);
    }
}