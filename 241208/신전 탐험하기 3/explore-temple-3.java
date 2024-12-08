import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 처리
        int n = sc.nextInt(); // 층의 수
        int m = sc.nextInt(); // 방의 수
        int[][] treasures = new int[n][m]; // 각 층의 보물 정보
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                treasures[i][j] = sc.nextInt();
            }
        }
        
        // DP 테이블 초기화
        int[][] dp = new int[n][m];
        
        // 첫 번째 층 초기화
        for (int j = 0; j < m; j++) {
            dp[0][j] = treasures[0][j];
        }
        
        // DP 점화식 계산
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int maxPrev = 0;
                for (int k = 0; k < m; k++) {
                    if (k != j) { // 같은 방을 연속으로 선택할 수 없음
                        maxPrev = Math.max(maxPrev, dp[i - 1][k]);
                    }
                }
                dp[i][j] = maxPrev + treasures[i][j];
            }
        }
        
        // 마지막 층에서 최댓값 계산
        int maxTreasure = 0;
        for (int j = 0; j < m; j++) {
            maxTreasure = Math.max(maxTreasure, dp[n - 1][j]);
        }
        
        // 결과 출력
        System.out.println(maxTreasure);
    }
}
