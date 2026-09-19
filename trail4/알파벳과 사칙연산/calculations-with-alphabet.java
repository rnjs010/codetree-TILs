import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr = new int[6];
    static int ans = Integer.MIN_VALUE;
    static ArrayDeque<Character> dq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        n = expression.length();
        for (int i = 0; i < n; i++) {
            dq.offerLast(expression.charAt(i));
        }

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
        char c = dq.pollFirst();
        dq.offerLast(c);
        int res = arr[c - 97];

        for (int i = 0; i < n / 2; i++) {
            char op = dq.pollFirst();
            char n2 = dq.pollFirst();
            dq.offerLast(op);
            dq.offerLast(n2);
            if (op == '+') {
                res += arr[n2 - 97];
            } else if (op == '-') {
                res -= arr[n2 - 97];
            } else {
                res *= arr[n2 - 97];
            }
        }
        return res;
    }
}