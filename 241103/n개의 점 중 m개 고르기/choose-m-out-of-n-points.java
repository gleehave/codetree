import java.io.*;
import java.util.*;

public class Main {

    static int n, m, min;
    static List<Integer> select = new ArrayList<>();
    static int[][] points;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        points = new int[n][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;
        solve(0, 0);
        System.out.println(min); // 유클리디안 거리로 출력
    }

    public static void solve(int depth, int cur) {
        if (depth == m) {
            calc();
            return;
        }

        for (int i = cur; i < n; i++) {
            select.add(i);
            solve(depth + 1, i + 1);
            select.remove(select.size() - 1);
        }
    }

    public static void calc() {
        int maxDist = 0; // 선택된 점들 중 가장 큰 거리값

        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                int x1 = points[select.get(i)][0];
                int y1 = points[select.get(i)][1];
                int x2 = points[select.get(j)][0];
                int y2 = points[select.get(j)][1];

                int dx = x1 - x2;
                int dy = y1 - y2;
                int dist = dx * dx + dy * dy;

                maxDist = Math.max(maxDist, dist);
            }
        }

        min = Math.min(min, maxDist); // 최소의 최대 거리값을 갱신
    }
}