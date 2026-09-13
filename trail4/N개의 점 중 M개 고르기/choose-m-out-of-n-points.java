import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] x, y;
    static int[][] distAll;
    static int[] select;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        x = new int[n];
        y = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        distAll = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distAll[i][j] = (x[i]-x[j])*(x[i]-x[j]) + (y[i]-y[j])*(y[i]-y[j]);
            }
        }

        select = new int[m];
        comb(0, 0, 0);
        System.out.print(ans);
    }

    static void comb(int idx, int cnt, int currMax) {
        if (currMax >= ans) return;

        if (cnt == m) {
            ans = Math.min(ans, currMax);
            return;
        }

        if (n - idx < m - cnt) return;

        for (int i = idx; i < n; i++) {
            int nextMax = currMax;
            
            for (int j = 0; j < cnt; j++) {
                nextMax = Math.max(nextMax, distAll[select[j]][i]);
            }

            select[cnt] = i;
            comb(i + 1, cnt + 1, nextMax);
        }
    }
}