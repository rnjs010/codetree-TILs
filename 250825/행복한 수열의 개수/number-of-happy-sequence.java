import java.util.Scanner;
  
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        // Please write your code here.
        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean happy = false;
            int same = grid[i][0];
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == same) cnt++;
                else {
                    same = grid[i][j];
                    cnt = 1;
                }
                if (cnt >= m) {
                    happy = true;
                    break;
                }
            }
            if (happy) ans++;
        }

        for (int j = 0; j < n; j++) {
            boolean happy = false;
            int same = grid[0][j];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (grid[i][j] == same) cnt++;
                else {
                    same = grid[i][j];
                    cnt = 1;
                }
                if (cnt >= m) {
                    happy = true;
                    break;
                }
            }
            if (happy) ans++;
        }

        System.out.println(ans);
    }
}