import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 1000;
    
    public static int n;
    public static int[][] num = new int[MAX_NUM][MAX_NUM];
    public static int[][] dp = new int[MAX_NUM][MAX_NUM];
    
    public static void initialize() {
        // 시작점의 경우 dp[0][0] = num[0][0]으로 초기값을 설정해줍니다
        dp[0][0] = num[0][0];
    
        // 최좌측 열의 초기값을 설정해줍니다.
        for(int i = 1; i < n; i++)
            dp[i][0] = Math.min(dp[i-1][0], num[i][0]);
    
        // 최상단 행의 초기값을 설정해줍니다.
        for(int j = 1; j < n; j++)
            dp[0][j] = Math.min(dp[0][j-1], num[0][j]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                num[i][j] = sc.nextInt();

        // 초기값 설정
        initialize();

        // 탐색하는 위치의 위에 값과 좌측 값 중에 큰 값과
        // 해당 위치의 숫자 중에 최솟값을 구해줍니다.
        for(int i = 1; i < n; i++)
            for(int j = 1; j < n; j++)
                dp[i][j] = Math.min(Math.max(dp[i-1][j], dp[i][j-1]), num[i][j]);


        System.out.print(dp[n-1][n-1]);
    }
}