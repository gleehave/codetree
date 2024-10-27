import java.util.*;

public class Main {
    static String str;
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str = sc.next();
        int[] charToInt = new int[128];

        solve((int) 'a', charToInt);
        System.out.println(ans);
    }

    private static void solve(int local, int[] charToInt) {
        if(local == ((int) 'f') + 1) {
            int num = charToInt[str.charAt(0)];

            for(int i = 2; i < str.length(); i += 2) {
                char temp = str.charAt(i - 1);
                if(temp == '-') {
                    num -= charToInt[str.charAt(i)];
                } else if(temp == '+') {
                    num += charToInt[str.charAt(i)];
                } else {
                    num *= charToInt[str.charAt(i)];
                }
            }

            ans = Math.max(ans, num);
            return;
        }

        for(int i = 1; i <= 4; i++) {
            charToInt[local] = i;
            solve(local + 1, charToInt);
        }
    }
}