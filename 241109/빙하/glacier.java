import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] grid;
    static boolean[][] visited;

    static Queue<int[]> queue = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int lastSize = 0;

        while (true) {
            visited = new boolean[n][m];
            queue = new LinkedList<>();

            // 외부 공기 영역 설정 (바깥 테두리)
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;

            // 외부와 연결된 물 찾기
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nextR = r + dr[d];
                    int nextC = c + dc[d];

                    if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) continue;
                    if (visited[nextR][nextC]) continue;
                    if (grid[nextR][nextC] == 0) {
                        queue.offer(new int[]{nextR, nextC});
                        visited[nextR][nextC] = true;
                    }
                }
            }

            // 외부와 닿아있는 빙하 녹이기
            List<int[]> meltList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1 && isExposedToWater(i, j)) {
                        meltList.add(new int[]{i, j});
                    }
                }
            }

            // 빙하가 더 이상 없으면 종료
            if (meltList.isEmpty()) {
                System.out.println(time + " "+ lastSize);
                break;
            }

            // 현재 시간에 녹은 빙하의 개수 저장
            lastSize = meltList.size();

            // 빙하 녹이기
            for (int[] pos : meltList) {
                grid[pos[0]][pos[1]] = 0;
            }

            time++;
        }
    }

    // 해당 빙하가 외부와 닿아있는지 확인
    public static boolean isExposedToWater(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nextR = r + dr[d];
            int nextC = c + dc[d];
            if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) continue;
            if (visited[nextR][nextC]) return true;
        }
        return false;
    }
}