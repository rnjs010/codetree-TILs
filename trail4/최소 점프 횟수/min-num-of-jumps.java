import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        jump(0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void jump(int cur, int cnt) {
        if (ans <= cnt) return;
        
        if (cur + 1 >= n) {
            ans = Math.min(ans, cnt);
            return;
        }

        for (int i = 1; i <= arr[cur]; i++) {
            jump(cur + i, cnt + 1);
        }
    }
}