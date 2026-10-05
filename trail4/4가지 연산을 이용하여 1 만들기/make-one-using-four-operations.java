import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] visit;
    static Deque<Integer> dq = new ArrayDeque<>();
    static int[] arr = {-1, 1, 2, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visit = new int[n * 2 + 1];
        
        visit[n] = 0;
        dq.offer(n);
        bfs();

        System.out.print(visit[1]);
    }


    static void bfs() {
        while(!dq.isEmpty()) {
            int num = dq.poll();
            
            if (num == 1) return;
            

            int a = num - 1;
            if (a > 0 && visit[a] == 0) {
                visit[a] = visit[num] + 1;
                dq.offer(a);
            }

            int b = num + 1;
            if (b <= n * 2 && visit[b] == 0) {
                visit[b] = visit[num] + 1;
                dq.offer(b);
            }

            int c = num / 2;
            if (num % 2 == 0 && visit[c] == 0) {
                visit[c] = visit[num] + 1;
                dq.offer(c);
            }

            int d = num / 3;
            if (num % 3 == 0 && visit[d] == 0) {
                visit[d] = visit[num] + 1;
                dq.offer(d);
            }
        }
    }
}