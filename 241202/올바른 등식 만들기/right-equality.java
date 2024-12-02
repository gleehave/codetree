import java.util.Scanner;

public class Main {
    public static final int MIN_M = 0;
    public static final int MAX_M = 40;
    public static final int OFFSET = 20;
    public static final int MAX_N = 100;
    
    // dp[i][j] :
    // i번째 숫자까지 고려해봤을 때
    // 계산 결과가 j가 나오는
    // 서로 다른 가짓수
    public static long[][] dp = new long[MAX_N + 1][MAX_M + 1];
    
    public static int n, m;
    
    public static int[] a = new int[MAX_N + 1];
    
    public static void initialize() {
        // 가짓수를 구하는 문제이므로, 
        // 초기에는 전부 0을 넣어줍니다.
        for(int i = 0; i <= n; i++)
            for(int j = MIN_M; j <= MAX_M; j++)
                dp[i][j] = 0;
        
        // 0번째 숫자까지 고려해봤을 때에는
        // 아직 아무 숫자도 보지 못했기 때문에
        // 초기값에 해당하는 0으로 시작해야 합니다.
        // 하지만 계산 도중 합이 -20 ~ 0 사이로 내려갈 경우
        // 배열에 그 숫자를 저장할 수 없으므로 
        // 처음부터 숫자 20에서 시작하여 
        // 0 ~ 40 사이의 숫자를 유지하며 최종적으로 M + 20가
        // 만들어 지는 서로 다른 가짓수를 구합니다.
        // 따라서 처음에 숫자 20에서 시작하는 가짓수는 1가지 입니다.
        dp[0][0 + OFFSET] = 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        // 계산 도중 값들을 전부 양수로 만들기 위해
        // 처음 시작 값을 0이 아닌 OFFSET으로 시작했으므로
        // m에 OFFSET을 더한 값을 만들 수 있는
        // 서로 다른 가짓수를 구해야합니다.
        m += OFFSET;

        for(int i = 1; i <= n; i++)
            a[i] = sc.nextInt();

        initialize();

        for(int i = 1; i <= n; i++) {
            // i번째 숫자까지 고려해봤을 때
            // 계산 결과가 j가 나오는
            // 가짓수를 계산합니다.
            
            // 이러한 상황을 만들기 위한 선택지는 크게 2가지 입니다.
            for(int j = MIN_M; j <= MAX_M; j++) {
                // Case 1
                // i번째 숫자를 뺀 경우입니다.
                // 따라서 i번째 숫자를 빼서 값이 j가 되려면
                // i - 1번째 숫자까지 이용했을 때에는 합이 j + a[i] 가 되었어야 하므로 
                // 해당 가짓수는 dp[i - 1][j + a[i]] 입니다.
                // 만약 j + a[i]가 MAX_M보다 큰 경우에는 계산 도중
                // 나올 수 있는 최댓값을 초과하는 경우이므로
                // j + a[i]는 MAX_M보다 작거나 같아야지만 가능한 경우입니다.

                if(j + a[i] <= MAX_M)
                    dp[i][j] += dp[i - 1][j + a[i]];

                // Case 2
                // i번째 숫자를 더한 경우입니다.
                // 따라서 i번째 숫자를 더해서 값이 j가 되려면
                // i - 1번째 숫자까지 이용했을 때에는 합이 j - a[i] 가 되었어야 하므로 
                // 해당 가짓수는 dp[i - 1][j - a[i]] 입니다.
                // 만약 j - a[i]가 MIN_M보다 작은 경우에는 계산 도중
                // 나올 수 있는 최솟값을 초과하는 경우이므로
                // j - a[i]는 MIN_M보다 크거나 같아야지만 가능한 경우입니다.
                if(j - a[i] >= MIN_M)
                    dp[i][j] += dp[i - 1][j - a[i]];
            }
        }

        // n개의 숫자에 대해 전부 고려했을 때,
        // 최종적으로 합이 m이 되는 가짓수에 해당하는
        // dp 값을 출력합니다.
        System.out.print(dp[n][m]);
    }
}