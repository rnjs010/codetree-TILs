import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] arr;
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
        
        comb(0, 0, 0);
        System.out.print(ans);
    }

    static void comb(int idx, int depth, int currXOR) {
        if (depth == m) {
            ans = Math.max(ans, currXOR);
            return;
        }

        for (int i = idx; i < n; i++) {
            comb(i + 1, depth + 1, currXOR ^ arr[i]);
        }
    }
}