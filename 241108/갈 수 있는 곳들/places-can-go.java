import java.io.*;
import java.util.*;

public class Main {
    static int n, k, count;
    static int[][] grid;
    static boolean[][] visited;
    static List<int[]> location = new ArrayList<>();

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[n][n];
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if (grid[i][j] == 1) visited[i][j] = true; // Mark blocked cells as visited
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            location.add(new int[]{
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
            });
        }

        if (n == 1) System.out.println(1);
        else {
            bfs();
            System.out.println(count);
        }
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < location.size(); i++) {
            int r = location.get(i)[0];
            int c = location.get(i)[1];
            queue.offer(new int[]{r, c});
            visited[r][c] = true;  // Mark starting points as visited
        }

        count = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            count++;

            for (int d = 0; d < 4; d++) {
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) continue;
                if (visited[nextR][nextC]) continue;
                if (grid[nextR][nextC] == 1) continue;

                visited[nextR][nextC] = true;
                queue.offer(new int[]{nextR, nextC});
            }
        }
    }
}