import java.util.Scanner;

public class Main {
    public static final int INT_MIN = Integer.MIN_VALUE;
    public static final int OFFSET = 100000;
    public static final int MAX_M = 100000;
    public static final int MAX_N = 100;
    
    // 변수 선언
    public static int n, m;
    public static int[] arr = new int[MAX_N + 1];
    
    // dp[i][j] : i번째 수까지 고려헀을 떄
    //            그룹 A 합 - 그룹 B 합을 j라 했을 때
    //            만들 수 있는 최대 그룹 A의 합
    public static int[][] dp = new int[MAX_N + 1][MAX_M + 1 + OFFSET];
    
    public static void initialize() {
        // 최대를 구하는 문제이므로
        // 초기값을 INT_MIN으로 넣어줍니다.
        for(int i = 0; i <= n; i++)
            for(int j = -m; j <= m; j++)
                dp[i][j + OFFSET] = INT_MIN;
    
        // 초기 조건은
        // 아직 아무런 수도 고른적이 없는 경우이므로 
        // 0번째 수까지 고려하여
        // 그룹 A 합 - 그룹 B 합이 0이고 
        // 그룹 A의 합이 0인 경우에 대한 정보 입니다.
        dp[0][0 + OFFSET] = 0;
    }
    
    public static void update(int i, int j, int prevI, int prevJ, int val) {
        // 불가능한 경우 패스합니다.
        if(prevJ < -m || prevJ > m || dp[prevI][prevJ + OFFSET] == INT_MIN)
            return;
        
        dp[i][j + OFFSET] = Math.max(dp[i][j + OFFSET], dp[prevI][prevJ + OFFSET] + val);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 1; i <= n; i++)
            arr[i] = sc.nextInt();
        
        // 만들 수 있는 최대 합을 계산합니다.
        for(int i = 1; i <= n; i++)
            m += arr[i];
        
        initialize();

        // 점화식에 따라 값을 채워줍니다.
        for(int i = 1; i <= n; i++) {
            for(int j = -m; j <= m; j++) {
                // Case 1. 그룹 A에 i번째 원소를 추가하여 그룹A-그룹B가 j가 된 경우
                //         dp[i - 1][j - arr[i]] + arr[i] -> dp[i][j]
                update(i, j, i - 1, j - arr[i], arr[i]);

                // Case 2. 그룹 B에 i번째 원소를 추가하여 그룹A-그룹B가 j가 된 경우
                //         dp[i - 1][j + arr[i]] -> dp[i][j]
                update(i, j, i - 1, j + arr[i], 0);

                // Case 3. 그룹 C에 i번째 원소를 추가하여 그룹A-그룹B가 j가 된 경우
                //         dp[i - 1][j] -> dp[i][j]
                update(i, j, i  - 1, j, 0);
            }
        }

        // n개의 수를 고려하여
        // 그룹A-그룹B가 0이 된 경우 중
        // 가능한 그룹A 합의 최대값이 답이 됩니다.
        System.out.print(dp[n][0 + OFFSET]);
    }
}