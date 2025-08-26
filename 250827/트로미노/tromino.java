import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                int val = grid[i][j] + grid[i][j+1];
                int val2 = val + grid[i+1][j];
                int val3 = val + grid[i+1][j+1];
                val = Math.max(val2, val3);
                ans = Math.max(ans, val);
            }
        }

        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < m - 1; j++) {
                int val = grid[i][j] + grid[i][j+1];
                int val2 = val + grid[i-1][j];
                int val3 = val + grid[i-1][j+1];
                val = Math.max(val2, val3);
                ans = Math.max(ans, val);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m - 3; j++) {
                int val = grid[i][j] + grid[i][j+1] + grid[i][j+2];
                ans = Math.max(ans, val);
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = 0; i <= n - 3; i++) {
                int val = grid[i][j] + grid[i+1][j] + grid[i+2][j];
                ans = Math.max(ans, val);
            }
        }
        System.out.println(ans);
    }
}