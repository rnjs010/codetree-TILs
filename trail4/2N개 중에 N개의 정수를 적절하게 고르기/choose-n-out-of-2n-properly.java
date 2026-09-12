import java.util.*;
import java.io.*;

public class Main {
    static int n, ans, total = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }
        
        ans = Integer.MAX_VALUE;
        /* 
           ★ 대칭성 컷팅 : 탐색 횟수 줄이기
           왜? 백트래킹 돌다가 같은 경우가 나오기 때문에 (ex. ab/cd = cd/ab)
           첫 번째 원소를 무조건 포함하는 조합들만 구하면, 
           나머지 조합들은 자동으로 남은 그룹이 됨
        */
        comb(1, 1, arr[0]);
        System.out.println(ans);
    }

    static void comb(int idx, int cnt, int sum) {
        if (cnt == n) {
            ans = Math.min(ans, Math.abs((total - sum) - sum));
            return;
        }

        for (int i = idx; i < 2 * n; i++) {
            comb(i + 1, cnt + 1, sum + arr[i]);
        }
    }   
}