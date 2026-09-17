import java.util.*;
import java.io.*;

public class Main {
    static class Line implements Comparable<Line> {
        int a, b;
        public Line (int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            if (this.b == o.b) {
                return Integer.compare(this.a, o.a);
            }
            return Integer.compare(this.b, o.b);
        }
    }

    static int n, m;
    static Line[] lines;
    static boolean[] select;
    static int[] all_res;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        lines = new Line[m];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new Line(a, b);
        }

        Arrays.sort(lines);

        select = new boolean[m];
        Arrays.fill(select, true);
        all_res = run(select);

        ans = m;
        Arrays.fill(select, false);
        func(0, 0);

        System.out.print(ans);
    }

    static void func(int idx, int cnt) {
        if (idx == m) {
            int[] sel_res = run(select);
            if (Arrays.equals(all_res, sel_res)) {
                ans = Math.min(ans, cnt);
            }
            return;
        }

        select[idx] = true;
        func(idx + 1, cnt + 1);
        select[idx] = false;
        func(idx + 1, cnt);
    }

    static int[] run(boolean[] select) {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = i + 1;
        }

        for (int i = 0; i < m; i++) {
            if (select[i]) {
                int x = lines[i].a - 1;
                int y = lines[i].a;
                res[x] = res[x] ^ res[y];
                res[y] = res[x] ^ res[y];
                res[x] = res[x] ^ res[y];
            }
        }

        return res;
    }
}