import java.util.*;
import java.io.*;

public class Main {
    static int k, n;
    static int[] output;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());


        output = new int[n];
        perm(0);
        System.out.print(sb);
    }

    static void perm(int depth) {
        if (depth == n) {
            printOutput();
            return;
        }

        for (int i = 1; i <= k; i++) {
            if (depth >= 2 && output[depth - 1] == output[depth - 2] && output[depth - 1] == i) {
                continue; 
            }
            output[depth] = i;
            perm(depth + 1);
        }
    }

    static void printOutput() {
        for (int i = 0; i < n; i++) {
            sb.append(output[i]).append(" ");
        }
        sb.append("\n");
    }
}