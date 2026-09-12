import java.util.*;
import java.io.*;

public class Main {
    static int[][] pos = new int[10][2];
    static int[] sPos, ePos;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 10; i++) {
            pos[i] = new int[] {-1, -1};
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if (c == 'S') {
                    sPos = new int[] {i, j};
                } else if (c == 'E') {
                    ePos = new int[] {i, j};
                } else if (c == '.') {
                    continue;
                } else {
                    pos[c - '0'] = new int[] {i, j};
                }
            }
        }

        choose(0, 0, 0, 0);
        if (ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    static void choose(int cur, int next, int cnt, int dist) {
        if (cnt == 3) {
            dist += (Math.abs(ePos[0] - pos[cur][0]) + Math.abs(ePos[1] - pos[cur][1]));
            ans = Math.min(ans, dist);
            return;
        }

        for (int i = next; i < 10; i++) {
            if (pos[i][0] == -1 && pos[i][1] == -1) continue;
            int t = 0;
            if (cnt == 0) {
                t = (Math.abs(sPos[0] - pos[i][0]) + Math.abs(sPos[1] - pos[i][1]));
            } else {
                t = (Math.abs(pos[cur][0] - pos[i][0]) + Math.abs(pos[cur][1] - pos[i][1]));
            }
            choose(i, i + 1, cnt + 1, dist + t);
        }
    }
}