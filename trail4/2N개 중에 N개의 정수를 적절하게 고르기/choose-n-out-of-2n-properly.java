import java.util.*;
import java.io.*;

public class Main {
    static int n, ans, total = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }
        
        ans = Integer.MAX_VALUE;
        comb(1, 1, arr[0]);
        System.out.println(ans);
    }

    static void comb(int idx, int cnt, int sum) {
        if (cnt == n) {
            ans = Math.min(ans, Math.abs((total - sum) - sum));
            return;
        }

        for (int i = idx; i < 2 * n; i++) {
            comb(i + 1, cnt + 1, sum + arr[i]);
        }
    }   
}