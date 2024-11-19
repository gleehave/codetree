import java.util.Scanner;

public class Main {
    public static final int INT_MIN = Integer.MIN_VALUE;
    public static final int MAX_NUM = 50;
    
    // 변수 선언
    public static int n, m;
    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    
    public static int[][] dp = new int[MAX_NUM][MAX_NUM];
    
    public static void initialize() {
        // 최대를 구해야 하므로
        // 전부 INT_MIN으로 초기화를 합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                dp[i][j] = INT_MIN;
        
        // 초기조건으로는
        // 시작 위치에 대한 처리가 필요합니다.
        // 시작 위치에서는 정확히 1칸을 밟게 되는 것이므로
        // dp[0][0] = 1이 됩니다.
        dp[0][0] = 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        
        // 초기값을 설정합니다.
        initialize();

        // 점화식에 따라
        // 위에서 아래, 왼쪽에서 오른쪽 방향으로 진행하며
        // 값을 채워줍니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                // 바로 직전 위치를 (k, l)이라 했을 때
                // 조건을 만족하는 경우에 대해
                // 값을 갱신해줍니다.
                for(int k = 0; k < i; k++)
                    for(int l = 0; l < j; l++) {
                        // (k, l)에 도달하는 것이 불가하다면
                        // 패스합니다.
                        if(dp[k][l] == INT_MIN)
                            continue;

                        // 값이 증가하는 경우에만
                        // 갱신을 진행합니다.
                        if(grid[k][l] < grid[i][j])
                            dp[i][j] = Math.max(dp[i][j], dp[k][l] + 1);
                    }
            }

        // 어디에 도착하던 상관없이
        // 최대로 밟을 수 있는 것이 중요하므로
        // 전체 중 최댓값이 답이 됩니다.
        int ans = INT_MIN;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                ans = Math.max(ans, dp[i][j]);
        
        System.out.print(ans);
    }
}