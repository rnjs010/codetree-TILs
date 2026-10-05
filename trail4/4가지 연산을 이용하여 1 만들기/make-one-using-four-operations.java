import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] visit;
    static Deque<Integer> dq = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        if (n == 1) {
            System.out.print(0);
            return;
        }

        visit = new int[n * 2 + 1];
        Arrays.fill(visit, -1);
        
        visit[n] = 0;
        dq.offer(n);
        bfs();

        System.out.print(visit[1]);
    }

    static void bfs() {
        while(!dq.isEmpty()) {
            int num = dq.poll();
            
            int a = num - 1;
            if (a > 0 && visit[a] == -1) {
                visit[a] = visit[num] + 1;
                if (a == 1) return;
                dq.offer(a);
            }

            int b = num + 1;
            if (b <= n * 2 && visit[b] == -1) {
                visit[b] = visit[num] + 1;
                if (b == 1) return;
                dq.offer(b);
            }

            if (num % 2 == 0) {
                int c = num / 2;
                if (visit[c] == -1) {
                    visit[c] = visit[num] + 1;
                    if (c == 1) return;
                    dq.offer(c);
                }
            }

            if (num % 3 == 0) {
                int d = num / 3;
                if (visit[d] == -1) {
                    visit[d] = visit[num] + 1;
                    if (d == 1) return;
                    dq.offer(d);
                }
            }
        }
    }
}