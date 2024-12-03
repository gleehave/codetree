import java.util.Scanner;

public class Main {
    public static final int MOD = 1000000007;
    public static final int MAXN = 1005;
    public static int n;
    public static int[][][] dp = new int[MAXN][5][5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n 값을 입력받습니다.
        n = sc.nextInt();

        // 초기 상태를 설정합니다. 
        dp[1][1][0] = 1;  // 첫 번째 날에 T를 받은 경우
        dp[1][0][1] = 1;  // 첫 번째 날에 B를 받은 경우
        dp[1][0][0] = 1;  // 첫 번째 날에 G를 받은 경우

        // 동적 프로그래밍을 사용해 문제를 해결합니다.
        // dp[i][j][k] :: i번째 날에, T를 총합 j회 받았고, B를 최근 k회 연속 받은 경우의 가짓수
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    // 다음 날로 넘어가는 경우의 수를 갱신합니다.
                    dp[i + 1][j + 1][0] = (dp[i + 1][j + 1][0] + dp[i][j][k]) % MOD;
                    dp[i + 1][j][0] = (dp[i + 1][j][0] + dp[i][j][k]) % MOD;
                    dp[i + 1][j][k + 1] = (dp[i + 1][j][k + 1] + dp[i][j][k]) % MOD;
                }
            }
        }

        // 최종 결과를 계산합니다.
        int ans = 0;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                ans = (ans + dp[n][j][k]) % MOD;
            }
        }

        // 결과를 출력합니다.
        System.out.println(ans);
    }
}