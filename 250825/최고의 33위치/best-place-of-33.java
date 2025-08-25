import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        int ans = 0;
        for (int i = 0; i < n-2; i++) {
            for (int j = 0; j < n-2; j++) {
                int val = 0;
                for (int x = i; x < i+3; x++) {
                    for (int y = j; y < j+3; y++) {
                        if (grid[x][y] == 1) val++;
                    }
                }
                ans = Math.max(ans, val);
            }
        }

        System.out.println(ans);
    }
}