import java.util.Scanner;

public class Main {
    public static final int INF = 1987654321;
    public static int n, m, k;
    public static int[][][] dp = new int[15][205][205];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n, m, k 값을 입력받습니다.
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        // 초기 상태를 설정합니다.
        for (int i = 1; i <= m; i++) dp[1][i][i] = 1;

        // 동적 프로그래밍을 사용하여 각 상태를 계산합니다.
        // dp[i][j][k] :: i개의 마법석을 사용하고, 숫자의 합이 j이며, 이중 가장 마지막의 숫자가 k인 가짓수
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= m; k++) {
                    for (int l = 1; l <= k; l++) {
                        if (j + l > m) break;
                        dp[i + 1][j + l][l] += dp[i][j][k];
                        dp[i + 1][j + l][l] = Math.min(dp[i + 1][j + l][l], (int)1e9);
                    }
                }
            }
        }

        // 최종 결과를 계산하고 출력합니다.
        int cur_l = 1;
        int cur_m = m;
        for (int i = n; i >= 1; i--) {
            while (dp[i][cur_m][cur_l] < k) {
                k -= dp[i][cur_m][cur_l];
                cur_l++;
            }

            System.out.print(cur_l + " ");
            cur_m -= cur_l;
        }
    }
}