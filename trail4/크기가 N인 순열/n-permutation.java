import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static boolean[] visit;
    static int[] output;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visit = new boolean[n + 1];
        output = new int[n];
        perm(0);
        System.out.print(sb);
    }

    static void perm(int depth) {
        if (depth == n) {
            for (int i = 0; i < n; i++) {
                sb.append(output[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            output[depth] = i;
            perm(depth + 1);
            visit[i] = false;
        }
    }
}