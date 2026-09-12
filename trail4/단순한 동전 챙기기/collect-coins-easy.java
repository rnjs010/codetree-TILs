import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Point[] pos = new Point[10];
    static Point sPos, ePos;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if (c == 'S') sPos = new Point(i, j);
                else if (c == 'E') ePos = new Point(i, j);
                else if (c != '.') {
                    pos[c - '0'] = new Point(i, j);
                }
            }
        }

        choose(null, 1, 0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int getDist(Point a, Point b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }

    static void choose(Point cur, int next, int cnt, int dist) {
        if (cnt == 3) {
            dist += getDist(cur, ePos);
            ans = Math.min(ans, dist);
            return;
        }

        if (next > 9) return;

        for (int i = next; i < 10; i++) {
            if (pos[i] == null) continue;
            int t = 0;
            if (cnt == 0) {
                t = getDist(sPos, pos[i]);
            } else {
                t = getDist(cur, pos[i]);
            }
            choose(pos[i], i + 1, cnt + 1, dist + t);
        }
    }
}