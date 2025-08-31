import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        // Please write your code here.

        Deque<Character> stk = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stk.offerLast(ch);
            } else {
                if (stk.isEmpty()) {
                    System.out.println("No");
                    return;
                }
                stk.pollLast();
            }
        }

        System.out.println(stk.isEmpty() ? "Yes" : "No");
    }
}