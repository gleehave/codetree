import java.io.*;
import java.util.*;

class Cloth {
    int s, e, v;
    public Cloth(int s, int e, int v) {
        this.s = s; // 입기 시작할 수 있는 날짜
        this.e = e; // 입을 수 있는 마지막 날짜
        this.v = v; // 화려함
    }
}

public class Main {
    static int n, m;
    static int[][] dp; // dp[day][cloth]: day날에 cloth번 옷을 입었을 때의 최대 만족도
    static Cloth[] cloth;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cloth = new Cloth[n + 1]; // 옷의 정보 저장 (1-based index)
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            cloth[i] = new Cloth(s, e, v);
        }

        // DP 배열 초기화
        dp = new int[m + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MIN_VALUE); // 초기 값은 최소값으로 설정
        }

        // 첫 날 초기화
        for (int i = 1; i <= n; i++) {
            if (cloth[i].s <= 1 && 1 <= cloth[i].e) { // 첫 날에 입을 수 있는 옷만
                dp[1][i] = 0; // 첫 날에는 이전 만족도가 없으므로 0
            }
        }

        // DP 전이
        for (int day = 2; day <= m; day++) { // 날짜
            for (int curr = 1; curr <= n; curr++) { // 오늘 입을 옷
                if (cloth[curr].s <= day && day <= cloth[curr].e) { // curr번 옷을 오늘 입을 수 있는 경우
                    for (int prev = 1; prev <= n; prev++) { // 어제 입은 옷
                        if (cloth[prev].s <= day - 1 && day - 1 <= cloth[prev].e) { // prev번 옷을 어제 입을 수 있는 경우
                            dp[day][curr] = Math.max(dp[day][curr], 
                                dp[day - 1][prev] + Math.abs(cloth[curr].v - cloth[prev].v));
                        }
                    }
                }
            }
        }

        // 마지막 날 결과 계산
        int maxSatisfaction = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (cloth[i].s <= m && m <= cloth[i].e) { // 마지막 날에 입을 수 있는 옷만
                maxSatisfaction = Math.max(maxSatisfaction, dp[m][i]);
            }
        }

        // 출력
        System.out.println(maxSatisfaction);
    }
}
