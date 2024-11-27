import java.util.Scanner;

public class Main {
    // 상수 MAXN을 정의합니다.
    public static final int MAXN = 1005;
    // 변수 n과 배열 coin, dp를 정의합니다.
    public static int n;
    public static int[] coin = new int[MAXN];
    public static int[][] dp = new int[MAXN][5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 계단의 층 수를 입력받습니다.
        n = sc.nextInt();
        // 각 층의 동전의 개수를 입력받습니다.
        for (int i = 1; i <= n; i++)
            coin[i] = sc.nextInt();

        // 기본 케이스를 초기화합니다.
        dp[1][1] = coin[1];

        dp[2][0] = coin[2];
        dp[2][2] = coin[1] + coin[2];

        // 동적 프로그래밍을 사용하여 최대 가치를 계산합니다.
        // dp[i][j] : i번 위치에 도착했을 때, 정확히 j번 1계단 올랐을 때의 최대 가치
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j <= 3; j++) {
                if (dp[i-2][j] != 0)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 2][j] + coin[i]);
                if (j > 0 && dp[i-1][j-1] != 0)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + coin[i]);
            }
        }

        // 가능한 모든 경우에서 최대 가치를 찾아 출력합니다.
        int ans = 0;
        for (int j = 0; j <= 3; j++)
            ans = Math.max(ans, dp[n][j]);

        System.out.println(ans);
    }
}