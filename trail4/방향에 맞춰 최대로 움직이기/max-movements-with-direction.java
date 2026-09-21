import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] num, moveDir;
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
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;

        move(r, c, 0);
        System.out.println(ans);
    }

    static void move(int x, int y, int cnt) {
        ans = Math.max(ans, cnt);

        int dir = moveDir[x][y];
        for (int i = 1; i < n; i++) {
            int nx = x + dx[dir] * i;
            int ny = y + dy[dir] * i;
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (num[nx][ny] > num[x][y]) {
                move(nx, ny, cnt + 1);
            }
        }
    }
}