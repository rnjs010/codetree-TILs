import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] arr, output;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        output = new int[m];
        comb(0, 0);
        System.out.print(ans);
    }

    static void comb(int idx, int depth) {
        if (depth == m) {
            int t = 0;
            for (int num: output) {
                t ^= num;
            }
            ans = Math.max(ans, t);
            return;
        }

        for (int i = idx; i < n; i++) {
            output[depth] = arr[i];
            comb(i + 1, depth + 1);
        }
    }
}