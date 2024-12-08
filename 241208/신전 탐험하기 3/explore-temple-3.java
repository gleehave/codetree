import java.util.Scanner;

public class Main {
    public static int n, m;
    public static int[][] a = new int[1005][105];
    public static int[][] dp = new int[1005][105];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n과 m을 입력받습니다.
        n = sc.nextInt();
        m = sc.nextInt();
        
        // 각 층의 보물의 개수 정보를 입력받습니다.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++)
                a[i][j] = sc.nextInt();
        }

        // 동적 프로그래밍을 사용하여 문제를 해결합니다.
        // dp[i][j] :: i번째 층까지 올라왔고, 이번 층에서 j번째 방을 들어갔을 때 보물의 최대 개수
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k <= m; k++) {
                    if (j == k) continue; // 이전 층에서 갔던 방과 똑같은 번호의 방을 들어갈 수 없습니다.
                    dp[i + 1][k] = Math.max(dp[i + 1][k], dp[i][j] + a[i + 1][k]);
                }
            }
        }

        // 최대 효율을 계산합니다.
        int ans = 0;
        for (int j = 1; j <= m; j++) {
            ans = Math.max(ans, dp[n][j]);
        }

        // 최대 효율을 출력합니다.
        System.out.println(ans);
    }
}