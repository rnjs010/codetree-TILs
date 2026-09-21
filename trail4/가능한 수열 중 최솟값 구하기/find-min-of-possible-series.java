import java.util.*;
import java.io.*;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        perm("");
    }

    static void perm(String str) {
        if (str.length() == n) {
            System.out.println(str);
            System.exit(0);
        }

        for (int i = 4; i <= 6; i++) {
            if (valid(str + i)) {
                perm(str + i);
            }
        }
    }

    static boolean valid(String str) {
        int len = str.length();

        for (int i = 1; i <= len / 2; i++) {
            String a = str.substring(len - i, len);
            String b = str.substring(len - i * 2, len - i);
            if (a.equals(b)) {
                return false;
            }
        }

        return true;
    }
}