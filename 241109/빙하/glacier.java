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
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        // 외곽이 아닌 0인 부분을 초기화
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (grid[i][j] == 1) continue;
                for (int d = 0; d < 4; d++) {
                    int nextR = i + dr[d];
                    int nextC = j + dc[d];
                    if (grid[nextR][nextC] == 0) {
                        visited[i][j] = true;
                        queue.offer(new int[]{i, j});
                        break;
                    }
                }
            }
        }
        simulate();
    }

    public static void simulate() {
        int time = 0;
        int lastSize = 0;

        while (!queue.isEmpty()) {
            lastSize = 0;
            int currSize = queue.size();
            lastSize = currSize;

            for (int i = 0; i < currSize; i++) {
                int[] cur = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nextR = cur[0] + dr[d];
                    int nextC = cur[1] + dc[d];

                    if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= m) continue;
                    if (visited[nextR][nextC]) continue;

                    if (grid[nextR][nextC] == 1) {
                        grid[nextR][nextC] = 0;
                        visited[nextR][nextC] = true;
                        queue.offer(new int[]{nextR, nextC});
                    }
                }
            }
            // 현재 남아 있는 1의 개수 계산
            // for (int k = 0; k < n; k++) {
            //     for (int z = 0; z < m; z++) {
            //         if (grid[k][z] == 1 && !visited[k][z]) lastSize++;
            //     }
            // }
            // System.out.println("lastSize: "+lastSize);

            time++;
        }

        System.out.println((time - 1) + " " + lastSize);
    }
}