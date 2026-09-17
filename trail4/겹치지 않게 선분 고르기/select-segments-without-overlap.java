import java.util.*;
import java.io.*;

public class Main {
    static class Line implements Comparable<Line> {
        int s, e;
        public Line(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Line o) {
            if (this.e == o.e) {
                return Integer.compare(this.s, o.s);
            }
            return Integer.compare(this.e, o.e);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            lines[i] = new Line(s, e);
        }

        Arrays.sort(lines);

        int ans = 0;
        int lastFinish = -1;
        for (int i = 0; i < n; i++) {
            if (lastFinish < lines[i].s) {
                ans++;
                lastFinish = lines[i].e;
            }
        }

        System.out.println(ans);
    }
}