import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] num, moveDir;
    static ArrayList<int[]> sel = new ArrayList<>();
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1}, dy = {0, 0, 1, 1, 1, 0, -1, -1, -1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        num = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        moveDir = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                moveDir[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        sel.add(new int[]{r - 1, c - 1});
        move(0);
        System.out.println(ans);
    }

    static void move(int cnt) {
        ans = Math.max(ans, cnt);

        for (int i = 1; i < n; i++) {
            int[] cur = sel.get(sel.size() - 1);
            int nx = cur[0] + (dx[moveDir[cur[0]][cur[1]]] * i);
            int ny = cur[1] + (dy[moveDir[cur[0]][cur[1]]] * i);
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
            if (num[nx][ny] > num[cur[0]][cur[1]]) {
                sel.add(new int[]{nx, ny});
                move(cnt + 1);
                sel.remove(sel.size() - 1);
            }
        }
    }
}