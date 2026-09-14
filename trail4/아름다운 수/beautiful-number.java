import java.util.*;
import java.io.*;

public class Main {
    static int n, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        perm(0);
        System.out.print(ans);
    }

    static void perm(int depth) {
        if (depth == n) {
            ans++;
            return;
        }

        for (int i = 1; i <= 4; i++) {
            if (n - depth < i) return;
            perm(depth + i);
        }
    }
}