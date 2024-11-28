import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] number;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        number = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        // DP 배열 및 변수 초기화
        int[][] dp = new int[n + 1][k + 1]; // dp[i][j]: i번째까지 고려, j개의 음수 포함한 최대 합
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = 0; // 아무 것도 선택하지 않았을 때 초기값

        int maxSum = Integer.MIN_VALUE;

        // DP 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                // 현재 숫자를 선택하지 않는 경우
                dp[i][j] = dp[i - 1][j];

                // 현재 숫자를 선택하는 경우
                if (number[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + number[i]);
                } else if (j > 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + number[i]);
                }

                // 최댓값 갱신
                maxSum = Math.max(maxSum, dp[i][j]);
            }
        }

        System.out.println(maxSum);
    }
}
