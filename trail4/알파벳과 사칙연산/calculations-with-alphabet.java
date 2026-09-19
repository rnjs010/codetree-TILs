import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr = new int[6];
    static int ans = Integer.MIN_VALUE;
    static char[] expr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        n = expression.length();
        expr = expression.toCharArray();

        perm(0);
        System.out.println(ans);   
    }

    static void perm(int depth) {
        if (depth == 6) {
            ans = Math.max(ans, calc());
            return;
        }

        for (int i = 1; i <= 4; i++) {
            arr[depth] = i;
            perm(depth + 1);
        }
    }

    static int calc() {
        int res = arr[expr[0] - 'a'];
        
        for (int i = 1; i < n; i += 2) {
            char op = expr[i];
            int nextNum = arr[expr[i + 1] - 'a'];

            if (op == '+') {
                res += nextNum;
            } else if (op == '-') {
                res -= nextNum;
            } else {
                res *= nextNum;
            }
        }
        return res;
    }
}
