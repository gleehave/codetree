import java.util.*;
import java.io.*;

public class Main {

    static int N, M, max;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;

        map = new int[N][];
        for (int i=0; i<N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        }

        solution();
        System.out.print(max);
    }// end of main

    public static void solution() {
        // 첫번째 사각형의 위치와 크기 완전탐색
        // i, j : 좌측 상단 꼭지점 좌표
        // x, y : 우측 하단 꼭지점 좌표
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int x=i; x<N; x++) {
                    for (int y=j; y<M; y++) {
                        max = Math.max(max, checkNext(i, j, x, y));
                    }
                }
            }
        }
    }

    public static int checkNext(int si, int sj, int sx, int sy) {
        int sumOfFirstRect = getRectSum(si, sj, sx, sy);  // 첫번째 직사각형의 총합
        int sumMax = Integer.MIN_VALUE;  // 두 직사각형의 총합의 최댓값

        // 두번째 직사각형
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                for (int x=i; x<N; x++) {
                    for (int y=j; y<M; y++) {
                        if (!isOverlapped(i, j, x, y, si, sj, sx, sy)) {  // 두 사각형이 겹치지 않는 경우
                            sumMax = Math.max(sumMax, sumOfFirstRect + getRectSum(i, j, x, y));  // 최댓값 갱신
                        }
                    }
                }
            }
        }

        return sumMax;
    }

    // 두 직사각형이 겹치는지 여부를 반환
    public static boolean isOverlapped(int i, int j, int x, int y, int si, int sj, int sx, int sy) {
        boolean[][] visited = new boolean[N][M];
        for (int k=i; k<=x; k++) {
            for (int l=j; l<=y; l++) {
                visited[k][l] = true;
            }
        }

        for (int k=si; k<=sx; k++) {
            for (int l=sj; l<=sy; l++) {
                if (visited[k][l]) return true;
            }
        }

        return false;
    }

    // 직사각형에 포함되어 있는 수들의 총합
    public static int getRectSum(int i, int j, int x, int y) {
        int sum = 0;
        for (int k=i; k<=x; k++) {
            for (int l=j; l<=y; l++) {
                sum += map[k][l];
            }
        }
        return sum;
    }
}