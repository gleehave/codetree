import java.util.Scanner;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_R = 100;
    public static final int MAX_N = 100;
    
    // 변수 선언
    public static int n;
    public static int[][] num = new int[MAX_N][MAX_N];
    public static int[][] dp = new int[MAX_N][MAX_N];
    
    public static int ans = INT_MAX;
    
    public static void initialize() {
        // 전부 INT_MAX로 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dp[i][j] = INT_MAX;
    
        // 시작점의 경우 dp[0][0] = num[0][0]으로 초기값을 설정해줍니다
        dp[0][0] = num[0][0];
    
        // 최좌측 열의 초기값을 설정해줍니다.
        for(int i = 1; i < n; i++)
            dp[i][0] = Math.max(dp[i - 1][0], num[i][0]);
    
        // 최상단 행의 초기값을 설정해줍니다.
        for(int j = 1; j < n; j++)
            dp[0][j] = Math.max(dp[0][j - 1], num[0][j]);
    }
    
    public static int solve(int lowerBound) {
        // lowerBound 미만의 값은 사용할 수 없도록
        // num값을 변경해줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(num[i][j] < lowerBound)
                    num[i][j] = INT_MAX;
        
        // DP 초기값 설정
        initialize();
    
        // 탐색하는 위치의 위에 값과 좌측 값 중에 작은 값과
        // 해당 위치의 숫자 중에 최댓값을 구해줍니다.
        for(int i = 1; i < n; i++)
            for(int j = 1; j < n; j++)
                dp[i][j] = Math.max(
                    Math.min(dp[i - 1][j], dp[i][j - 1]), 
                    num[i][j]
                );
            
        return dp[n - 1][n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력
        n = sc.nextInt();

        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                num[i][j] = sc.nextInt();

        // 최솟값을 lowerBound라고 가정했을 때
        // lowerBound 이상의 수들만 사용하여 
        // 이동한다는 조건하에서
        // 최댓값을 최소로 만드는 DP 문제를 풀어줍니다.
        for(int lowerBound = 1; lowerBound <= MAX_R; lowerBound++) {
            int upperBound = solve(lowerBound);
            
            // 다 진행했음에도 여전히 INT_MAX라면 
            // 그러한 이동이 불가능하다는 뜻이므로
            // 패스합니다.
            if(upperBound == INT_MAX)
                continue;
            
            // 답을 갱신합니다.
            ans = Math.min(ans, upperBound - lowerBound);
        }

        System.out.print(ans);
    }
}