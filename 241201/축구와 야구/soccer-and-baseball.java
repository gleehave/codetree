import java.util.*;
import java.io.*;

class Student {
    int football;
    int baseball;

    public Student(int football, int baseball) {
        this.football = football;
        this.baseball = baseball;
    }
}

public class Main {
    static int n;
    static Student[] people;
    static int[][][] dp; // DP 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 입력 처리
        n = Integer.parseInt(br.readLine());
        people = new Student[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            people[i] = new Student(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            );
        }

        // DP 배열 초기화
        dp = new int[n + 1][12][10]; // 11명 축구, 9명 야구 필요 -> 크기 12, 10으로 설정
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, Integer.MIN_VALUE);
            }
        }
        dp[0][0][0] = 0; // 초기 상태

        // DP 진행
        for (int i = 1; i <= n; i++) {
            for (int f = 0; f <= 11; f++) {
                for (int b = 0; b <= 9; b++) {
                    if (dp[i - 1][f][b] != Integer.MIN_VALUE) {
                        // 축구팀에 배정
                        if (f < 11) {
                            dp[i][f + 1][b] = Math.max(dp[i][f + 1][b], dp[i - 1][f][b] + people[i].football);
                        }
                        // 야구팀에 배정
                        if (b < 9) {
                            dp[i][f][b + 1] = Math.max(dp[i][f][b + 1], dp[i - 1][f][b] + people[i].baseball);
                        }
                        // 아무 팀에도 배정하지 않음
                        dp[i][f][b] = Math.max(dp[i][f][b], dp[i - 1][f][b]);
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(dp[n][11][9]);
    }
}
