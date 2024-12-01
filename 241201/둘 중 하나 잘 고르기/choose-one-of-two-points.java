import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] red = new int[2 * n + 1];
        int[] blue = new int[2 * n + 1];

        for (int i = 1; i <= 2 * n; i++) {
            st = new StringTokenizer(br.readLine());
            red[i] = Integer.parseInt(st.nextToken());
            blue[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[2 * n + 1][n + 1];
        for (int i = 0; i <= 2 * n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= 2 * n; i++) {
            for (int r = 0; r <= Math.min(i, n); r++) {
                if (r <= i - 1) {
                    dp[i][r] = Math.max(dp[i][r], dp[i - 1][r] + blue[i]);
                }
                if (r > 0) {
                    dp[i][r] = Math.max(dp[i][r], dp[i - 1][r - 1] + red[i]);
                }
            }
        }
        System.out.println(dp[2 * n][n]);
    }
}
