import java.io.*;
import java.util.*;

public class Main {

    static int n, k, u, d;
    static int[][] grid;
    static boolean[][] visited;
    static int maxCities = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static List<Integer> cities = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selectCities(0, 0);
        System.out.println(maxCities);
    }

    // k개의 도시를 선택하는 조합 생성
    public static void selectCities(int index, int cnt) {
        if (cnt == k) {
            maxCities = Math.max(maxCities, bfs());
            return;
        }

        if (index >= n * n) return;

        // 현재 인덱스의 도시 선택
        cities.add(index);
        selectCities(index + 1, cnt + 1);

        // 선택 취소 후 다음 도시 선택
        cities.remove(cities.size() - 1);
        selectCities(index + 1, cnt);
    }

    // 선택된 k개의 도시로부터 BFS 탐색
    public static int bfs() {
        int count = 0;
        visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();

        // 선택된 k개의 시작 도시를 Queue에 추가
        for (int city : cities) {
            int r = city / n;
            int c = city % n;

            if (!visited[r][c]) {
                count++;
                visited[r][c] = true;
                queue.offer(new int[]{r, c});
            }
        }

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int origin = grid[cur[0]][cur[1]];

            for(int[] loc: queue) System.out.println("loc: "+loc[0]+" "+loc[1]);
            
            for(int d=0; d<4; d++){
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) {
                    continue;
                }
                
                if (visited[nextR][nextC]) {
                    continue;
                }

                int heightDifference = Math.abs(origin - grid[nextR][nextC]);
                System.out.println("height: " + heightDifference);
                        
                if (heightDifference >= u && heightDifference <= d) {
                    count++;
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC});
                }
                System.out.println(nextR+" "+nextC);
            }
            System.out.println();

            /*
            for (int d = 0; d < 4; d++) {
                int nextR = cur[0] + dr[d];
                int nextC = cur[1] + dc[d];

                // 격자 바깥 체크
                if (nextR < 0 || nextC < 0 || nextR >= n || nextC >= n) {
                    continue;
                }

                // 이미 방문한 도시 체크
                if (visited[nextR][nextC]) {
                    continue;
                }

                int heightDifference = Math.abs(origin - grid[nextR][nextC]);

                // u 이상 d 이하의 높이 차이 조건 확인
                if (heightDifference >= u && heightDifference <= d) {
                    count++;
                    visited[nextR][nextC] = true;
                    queue.offer(new int[]{nextR, nextC});
                }
            }
            */
        }
        return count;
    }
}