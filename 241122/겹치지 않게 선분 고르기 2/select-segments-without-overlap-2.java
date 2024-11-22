import java.util.Scanner;
import java.util.Arrays;

class Segment implements Comparable<Segment> {
    int x1, x2;

    public Segment() {
    }

    public Segment(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    @Override
    public int compareTo(Segment s) {
        // x1을 기준으로 오름차순 정렬을 진행합니다.
        return this.x1 - s.x1;
    }
}

public class Main {
    public static final int MAX_N = 1000;
    
    // 변수 선언
    public static int n;
    public static Segment[] segments = new Segment[MAX_N];
    
    // dp[i] : x1 기준으로 정렬되어 있다는 가정 하에서
    //         i번째 선분을 끝으로
    //         겹치지 않게 선택할 수 있는 최대 선분의 수
    public static int[] dp = new int[MAX_N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            segments[i] = new Segment(x1, x2);
        }

        // x1 기준으로 오름차순 정렬을 진행합니다.
        Arrays.sort(segments, 0, n);

        for(int i = 0; i < n; i++) {
            // 현재 선분이 시작 선분인 경우에는
            // dp값이 1이 되므로
            // 초기 셋팅은 1입니다.
            dp[i] = 1;

            // i번째 선분 선택 전에
            // 바로 선택한 선분을 j라 했을 때 
            // i, j 선분이 서로 겹치지 않는 경우 중 
            // 선택 할 수 있는 선분의 최대 개수를 계산합니다.
            for(int j = 0; j < i; j++) {
                int x1I = segments[i].x1;
                int x2J = segments[j].x2;
                
                // 이미 x1 순으로 정렬이 되어있기에
                // x2[j] < x1[i]이기만 하면 두 선분은 겹치지 않습니다.
                if(x2J < x1I)
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        // 마지막으로 선택한 선분 위치가 i일 때의 경우 중
        // 고를 수 있는 선분의 수가 가장 큰 경우를 고릅니다.
        int ans = 0;
        for(int i = 0; i < n; i++)
            ans = Math.max(ans, dp[i]);

        System.out.print(ans);
    }
}