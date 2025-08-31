import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String[] st = br.readLine().split(" ");
            if (st[0].equals("push")) {
                int a = Integer.parseInt(st[1]);
                stk.offerLast(a);
            } else if (st[0].equals("pop")) {
                System.out.println(stk.pollLast());
            } else if (st[0].equals("size")) {
                System.out.println(stk.size());
            } else if (st[0].equals("empty")) {
                System.out.println(stk.isEmpty() ? 1 : 0);
            } else if (st[0].equals("top")) {
                System.out.println(stk.peekLast());
            } 
        }
    }
}