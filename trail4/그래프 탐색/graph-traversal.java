import java.util.*;
import java.io.*;

public class Main {
    static List<Integer>[] graph;
    static boolean[] visit;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visit = new boolean[n + 1];
        visit[1] = true;
        dfs(1);

        System.out.print(ans);
    }

    static void dfs(int v) {
        for (int i = 0; i < graph[v].size(); i++) {
            int next = graph[v].get(i);
            if (!visit[next]) {
                ans++;
                visit[next] = true;
                dfs(next);
            }
        }
    }
}