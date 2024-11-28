import java.util.Scanner;

public class Main {
    public static final int MAXN = 1005;
    public static int n;
    public static int[] a = new int[MAXN], b = new int[MAXN];
    public static int[][] dp = new int[MAXN][MAXN];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 각 플레이어의 카드 정보를 입력받습니다.
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) a[i] = sc.nextInt();
        for (int i = 1; i <= n; i++) b[i] = sc.nextInt();

        // dp 배열을 초기화합니다. 초기값은 -1로 설정합니다.
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= n; j++) dp[i][j] = -1;

        // 기본 케이스를 설정합니다.
        dp[0][0] = 0;

        // 각 경우의 수를 동적 프로그래밍으로 계산합니다.
        // dp[i][j] :: 첫 번째 플레이어는 i번 카드까지, 두 번째 플레이어는 j번 카드까지 버렸을 때 나올 수 있는 최대 점수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == -1) continue;

                // 카드 대결 - 첫 번째 플레이어의 카드가 더 작은 경우
                if (a[i + 1] < b[j + 1])
                    dp[i + 1][j] = Math.max(dp[i + 1][j], dp[i][j]);

                // 카드 대결 - 두 번째 플레이어의 카드가 더 작은 경우
                if (a[i + 1] > b[j + 1])
                    dp[i][j + 1] = Math.max(dp[i][j + 1], dp[i][j] + b[j + 1]);

                // 카드 버리기
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], dp[i][j]);
            }
        }

        // 결과를 계산하여 출력합니다.
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = Math.max(ans, dp[i][n]);
            ans = Math.max(ans, dp[n][i]);
        }
        System.out.println(ans);
    }
}