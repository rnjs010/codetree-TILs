import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] grid;
    static boolean[][] visit;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int pCnt = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        grid = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][n];
        int cnt = 0;
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visit[i][j]) {
                    cnt++;
                    pCnt = 1;
                    visit[i][j] = true;
                    dfs(i, j);
                    arr.add(pCnt);
                }
            }
        }

        Collections.sort(arr);
        System.out.println(cnt);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }          
    }

    static void dfs(int x, int y) {
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
            if (visit[nx][ny] || grid[nx][ny] == 0) continue;
            pCnt++;
            visit[nx][ny] = true;
            dfs(nx, ny);
        }
    }
}