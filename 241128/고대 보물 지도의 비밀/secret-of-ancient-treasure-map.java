import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] number;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        number = new int[n + 1];
        dp = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        // DP 배열 초기화
        Arrays.fill(dp, Integer.MIN_VALUE);

        // 슬라이딩 윈도우 방식으로 음수 개수 추적
        int count = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            if (number[i] < 0) count++;

            // 음수 개수가 k를 초과하지 않으면 dp 갱신
            if (count <= k) {
                dp[i] = Math.max(dp[i - 1] + number[i], number[i]);
            } else {
                // 음수 개수가 초과하면 새로 시작
                dp[i] = number[i];
                count = 1; // 현재 음수를 새로 포함
            }

            // 최댓값 갱신
            maxSum = Math.max(maxSum, dp[i]);
        }

        System.out.println(maxSum);
    }
}
