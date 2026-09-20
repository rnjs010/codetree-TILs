import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[] nums, res;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        res = new int[k];
        perm(0);
        System.out.println(ans);
    }

    static void perm(int d) {
        if (d == n) {
            int cnt = 0;
            for (int s: res) {
                if (s >= (m - 1)) cnt++;
            }
            ans = Math.max(ans, cnt);
            return;
        }

        for (int i = 0; i < k; i++) {
            res[i] += nums[d];
            perm(d + 1);
            res[i] -= nums[d];
        }
    }
}