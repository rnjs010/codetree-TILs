import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] output;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        output = new int[m];
        comb(1, 0);

        System.out.println(sb.toString());
    }

    static void comb(int start, int depth) {
        if (depth == m) {
            saveAns();
            return;
        }

        for (int i = start; i <= n; i++) {
            output[depth] = i;
            comb(i + 1, depth + 1);
        }
    }

    static void saveAns() {
        for (int a: output) {
            sb.append(a).append(" ");
        }
        sb.append("\n");
    }
}