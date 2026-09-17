import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] lines;
    static ArrayList<Integer> select = new ArrayList<>();
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        lines = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken());
            lines[i][1] = Integer.parseInt(st.nextToken());
        }

        func(0);
        System.out.println(ans);
    }

    static void func(int idx) {
        ans = Math.max(ans, select.size());

        for (int i = idx; i < n; i++) {
            if (check(i)) {
                select.add(i);
                func(i + 1);
                select.remove(select.size() - 1);
            }
        }
    }

    static boolean check(int num) {
        int[] cur = lines[num];
        for (int i = 0; i < select.size(); i++) {
            int[] sel = lines[select.get(i)];
            if(!((sel[1] < cur[0] || sel[0] > cur[1]) || (cur[1] < sel[0] || cur[0] > sel[1]))) {
                return false;
            }
        }
        return true;
    }
}