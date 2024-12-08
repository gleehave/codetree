import java.util.Scanner;

public class Main {
    public static final int INF = 1987654321;
    public static final int MAX_N = 1005;
    public static final int MAX_K = 25;
    public static final int STATES = 5;

    public static int n, k;
    public static String str;
    public static int[][][] dp = new int[MAX_N][MAX_K][STATES];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // n과 k 값을 입력받습니다.
        n = sc.nextInt();
        k = sc.nextInt();
        // 문자열 str을 입력받습니다.
        str = sc.next();
        // 문자열 앞에 공백을 추가하여 인덱스를 1부터 시작하게 합니다.
        str = " " + str;
        
        // dp 배열을 초기화합니다.
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][0] = dp[i][j][1] = -INF;
            }
        }
        
        // 초기 상태를 설정합니다.
        dp[0][0][0] = 0;
        dp[0][1][1] = 0;

        // 동적 프로그래밍을 이용하여 문제를 해결합니다.
        // dp[i][j][k] :: i번째 수정까지 떨어졌을 때, 총 j번 엘라가 움직였고,
        // 현재 위치가 k일 때 (k = 0일 때 왼쪽, k = 1일 때 오른쪽) 수집할 수 있는 수정의 최대 개수
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // 현재 위치가 왼쪽일 때를 관리합니다.
                if (dp[i][j][0] != -INF) {
                    if (str.charAt(i + 1) == 'L') {
                        dp[i + 1][j][0] = Math.max(dp[i + 1][j][0], dp[i][j][0] + 1);
                        dp[i + 1][j + 1][1] = Math.max(dp[i + 1][j + 1][1], dp[i][j][0]);
                    } else {
                        dp[i + 1][j][0] = Math.max(dp[i + 1][j][0], dp[i][j][0]);
                        dp[i + 1][j + 1][1] = Math.max(dp[i + 1][j + 1][1], dp[i][j][0] + 1);
                    }
                }

                // 현재 위치가 오른쪽일 때를 관리합니다.
                if (dp[i][j][1] != -INF) {
                    if (str.charAt(i + 1) == 'L') {
                        dp[i + 1][j + 1][0] = Math.max(dp[i + 1][j + 1][0], dp[i][j][1] + 1);
                        dp[i + 1][j][1] = Math.max(dp[i + 1][j][1], dp[i][j][1]);
                    } else {
                        dp[i + 1][j + 1][0] = Math.max(dp[i + 1][j + 1][0], dp[i][j][1]);
                        dp[i + 1][j][1] = Math.max(dp[i + 1][j][1], dp[i][j][1] + 1);
                    }
                }
            }
        }

        // 최종 결과를 계산합니다.
        int ans = 0;
        for (int j = 0; j <= k; j++) {
            ans = Math.max(ans, dp[n][j][0]);
            ans = Math.max(ans, dp[n][j][1]);
        }

        // 결과를 출력합니다.
        System.out.println(ans);
    }
}